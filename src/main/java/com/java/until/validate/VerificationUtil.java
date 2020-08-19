package com.java.until.validate;

import com.java.until.StringUtils;
import com.java.until.cache.CacheUntil;
import com.java.until.cache.RedisCacheEmun;

/**
 * @author ZhangWei
 * @Description 验证工具类
 * @Date: 2020-03-10 10:10
 **/
public class VerificationUtil {

    /**
     * @Author zw
     * @Description 验证验证码
     * @Date 09:54 2020-03-10
     * @Param phone
     * @Param code
     * @return boolean 验证成功返回true并删除缓存验证码否则返回false
     **/
    public static boolean verificationCode (String phone, String code) {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            return false;
        }

        String cacheCode = CacheUntil.get(RedisCacheEmun.USER_CACHE, phone, String.class);
        if (code.equals(cacheCode)) {

            // 验证成功,删除缓存验证码
            CacheUntil.del(RedisCacheEmun.USER_CACHE.getRedisTemplate(), phone);
            return true;
        }
        return false;
    }

    /**
     * @Author zw
     * @Description 验证验证码
     * @Date 09:54 2020-03-10
     * @Param phone
     * @Param code
     * @return boolean 验证成功返回true否则返回false
     **/
    public static boolean verificationCode2 (String phone, String code) {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            return false;
        }

        String cacheCode = CacheUntil.get(RedisCacheEmun.USER_CACHE, phone, String.class);
        if (code.equals(cacheCode)) {
            return true;
        }
        return false;
    }
}
