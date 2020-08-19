package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import com.java.config.InstanceFactory;
import com.java.moudle.system.service.InitService;

@SpringBootApplication
@EnableDiscoveryClient
@NacosPropertySources({
        @NacosPropertySource(dataId = "inspectcenter_dict", groupId = "DEFAULT_GROUP", autoRefreshed = true),
        @NacosPropertySource(dataId = "inspectcenter_oracle", groupId = "DEFAULT_GROUP", autoRefreshed = true),
        @NacosPropertySource(dataId = "redis_colony", groupId = "DEFAULT_GROUP", autoRefreshed = true)
})
public class Start {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Start.class);
		ApplicationContext context = application.run();
		InstanceFactory.setApplicationContext(context);
		InstanceFactory.getInstance(InitService.class).InitDict();
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		//设置连接超时
		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(10000);
		return new RestTemplate(requestFactory);
	}
}
