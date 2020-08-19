package com.java.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.service.SysRoleService;
import com.java.moudle.system.service.SysUserService;
import com.java.until.SysUtil;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;

@Configuration
public class WebFilterInterceptor extends WebMvcConfigurationSupport {

	private static List<String> userList = new ArrayList<>();
	static {
		userList.add("/login/**");//后台管理登录
		userList.add("/region/**");//后台管理登录
		userList.add("/statis/**");//大屏统计
		userList.add("/sys/dict/**");//后台管理登录
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**");
	}
    
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}
    
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //升级最新版本需加=============================================================
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        //WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
        //WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
        //DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        //WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        //WriteMapNullValue：是否输出值为null的字段,默认为false
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setDefaultCharset(Charset.forName("UTF-8"));
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    	/**
         * 	平台验证
         */
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            
            	String method = request.getMethod();
				if(method.equalsIgnoreCase("OPTIONS")){
					response.getOutputStream().write("Success".getBytes("utf-8"));
				}
				//从header里获取参数client+username+token
				Map<String, Object> headerMap = SysUtil.getSecurityKey(request);
				if(headerMap == null) {
					onAuthFail(response);
					return false;
				}
				//SysUtil.getSecurityKey(request);
				// 客户端请求的缓存key
				String securitykey = (String) headerMap.get("securitykey");
				// 验证缓存中无数据，需要重新获取缓存信息
				String jsonStr = CacheUntil.get(RedisCacheEmun.USER_CACHE, securitykey, String.class);
				if (null == jsonStr) {
					onAuthFail(response);
					return false;
				}
				//获取最新的登录人的access_token
				String token = JSONObject.parseObject(jsonStr).getString("accessToken");
				SysUser user = CacheUntil.get(RedisCacheEmun.USER_CACHE, token, SysUser.class);
				
				if(user == null) {
					//从缓存中获取token (key的值待定)
					String refreshToken = "refresh_auth:" + JSONObject.parseObject(jsonStr).getString("refreshToken");
					String authParamStr = CacheUntil.get(RedisCacheEmun.USER_CACHE, refreshToken, String.class);
					//通过用户id获取登录者信息
					String principalStr = JSONObject.parseObject(authParamStr).getString("principal");
					String userId = JSONObject.parseObject(principalStr).getString("id");

					user = InstanceFactory.getInstance(SysUserService.class).get(userId);
					//得到用户的角色信息
					SysRole role = InstanceFactory.getInstance(SysRoleService.class).getRoleInfoByUserId(userId);
					if(role == null) {
						onAuthFail(response);
						return false;
					}
					user.setRole(role);
					CacheUntil.put(RedisCacheEmun.USER_CACHE, token, user);
				}
				
            	return true;
            }
        }).addPathPatterns("/**")
        .excludePathPatterns(userList);
    }
    /**
	 * <li>描述:身份认证错误默认返回1003状态码</li>
	 * <li>方法名称:onAuthFail</li>
	 * <li>参数:@param response
	 * <li>参数:@throws Exception</li>
	 * <li>返回类型:void</li>
	 * <li>最后更新作者:gaoqs</li>
	 */
	private void onAuthFail(ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		response.setContentType("application/json;charset=UTF-8");
		//JsonResult ret = new JsonResult("身份认证错误", ProcessStatus.AUTH_ERROR);
		//ret.setResponseStatus(ResponseStatus.HTTP_UNAUTHORIZED);
		httpResponse.getWriter().write("{\"retCode\":1003,\"retMsg\":\"登录已失效，请重新登录\"}");
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
}
