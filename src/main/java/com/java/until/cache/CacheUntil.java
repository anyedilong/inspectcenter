package com.java.until.cache;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.java.until.BloomFilter;
import com.java.until.JsonUntil;
import com.java.until.StringUtils;

import redis.clients.jedis.Jedis;

public class CacheUntil {

	//字典
	public static final String DICT_ITEM = "dict_inspectcenter_code";

	//公共区划字典
	public static final String DICT_AREA_ITEM = "dict_publicarea_code";
	
	/**
	 * 
	 * @Description redis存储数据
	 * @param redisCacheEmun
	 * @param key
	 * @param value
	 * @param liveTime (过期时间 秒)
	 * @author sen
	 * @Date 2016年11月17日 下午1:39:44
	 */
	public static void put(RedisCacheEmun redisCacheEmun, Object key, Object value, long liveTime) {
		System.out.println(liveTime);
		put(redisCacheEmun.getRedisTemplate(), key, value, liveTime);
	}

	/**
	 *
	 * @Description redis存储数据
	 * @param redisCacheEmun
	 * @param key
	 * @param value
	 * @author sen
	 * @Date 2016年11月17日 下午1:39:44
	 */
	public static void put(RedisCacheEmun redisCacheEmun, Object key, Object value) {
		System.out.println(redisCacheEmun.getLiveTime());
		put(redisCacheEmun.getRedisTemplate(), key, value, redisCacheEmun.getLiveTime());
	}

	/**
	 * 
	 * @Description 获取redis缓存
	 * @param redisCacheEmun
	 * @param key
	 * @return
	 * @author sen
	 * @Date 2016年11月17日 下午1:42:00
	 */
	public static <T> T get(RedisCacheEmun redisCacheEmun, Object key, Class<T> clazz) {
		return get(redisCacheEmun.getRedisTemplate(), key, clazz, redisCacheEmun.getLiveTime());
	}

	/**
	 * @Description 获取redis缓存 集合
	 * @param redisCacheEmun
	 * @param key
	 * @param clazz
	 * @return
	 * @author sen
	 * @Date 2017年1月17日 下午1:33:30
	 */
	public static <T> List<T> getArray(RedisCacheEmun redisCacheEmun, Object key, Class<T> clazz) {
		return getArray(redisCacheEmun.getRedisTemplate(), key, clazz, redisCacheEmun.getLiveTime());
	}

	public static void put(RedisTemplate<String, Object> redisTemplate, Object key, Object value, long liveTime) {

		if (null == value) {
			return;
		}
		if (value instanceof String) {
			if (StringUtils.isNull(value.toString())) {
				return;
			}
		}

		final String keyf = key.toString();
		if (StringUtils.isNull(keyf)) {
			return;
		}
		final Object valuef = value;
		final long liveTimef = liveTime;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();

				// 将数据转为json
				String json = JSON.toJSONString(valuef, JsonUntil.getSerializeconfigcamelcase());

				byte[] valueb = StringUtils.getBytes(json);
				connection.set(keyb, valueb);

				if (liveTimef > 0) {
					connection.expire(keyb, liveTimef);
				}
				
				BloomFilter blfilter = new BloomFilter();
				
				long[] murmurHashOffset = blfilter.murmurHashOffset(keyf);
				
				return 1L;
			}
		});
	}

	public static <T> T get(RedisTemplate<String, Object> redisTemplate, Object key, final Class<T> clazz,
			long liveTime) {
		final String keyf = key.toString();
		System.out.println(keyf + "剩余失效" + redisTemplate.getExpire(key.toString()));
		final long liveTimef = liveTime;
		if (StringUtils.isNull(keyf)) {
			return null;
		}

		Object object;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] value = connection.get(keyb);

				if (liveTimef > 0) {
					connection.expire(keyb, liveTimef);
				}
				if (value == null) {
					return null;
				}
				String json = StringUtils.toString(value);
				// 将json----》object
				Object obj = null;
				try {
					obj = JSON.parseObject(json, clazz, JsonUntil.getParserconfigcamelcase());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return obj;
			}

		});

		return (T) object;
	}

	public static <T> List<T> getArray(RedisTemplate<String, Object> redisTemplate, Object key, final Class<T> clazz,
			long liveTime) {
		final String keyf = key.toString();
		System.out.println(keyf + "剩余失效" + redisTemplate.getExpire(key.toString()));
		final long liveTimef = liveTime;
		if (StringUtils.isNull(keyf)) {
			return null;
		}

		Object object;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] value = connection.get(keyb);

				if (liveTimef > 0) {
					connection.expire(keyb, liveTimef);
				}
				if (value == null) {
					return null;
				}
				String json = StringUtils.toString(value);
				// 将json----》object
				List<T> objArray = null;
				try {
					objArray = JSON.parseArray(json, clazz);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return objArray;
			}

		});

		return (List<T>) object;
	}

	public static void delete(RedisCacheEmun redisCacheEmun, Object key) {
		delete(redisCacheEmun.getRedisTemplate(), key);
	}

	public static void delete(RedisTemplate<String, Object> redisTemplate, Object key) {
		final String keyf = StringUtils.toString(key);
		if (StringUtils.isNull(keyf)) {
			return;
		}
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					// connection.multi();
					byte[] keyb = keyf.getBytes();
					connection.del(keyb);
					// connection.exec();
				} catch (Exception e) {
					connection.discard();
				}
				return null;
			}
		});
	}

	public static <T> List<T> getArray(String parentName){
		Map<String, List<T>> map = CacheUntil.get(RedisCacheEmun.DICT_CACHE, CacheUntil.DICT_ITEM, Map.class);
		return map.get(parentName);
	}
	
	// code获取字典值
	public static String getValue(String parentName, String code) {
		String value = code;
		Map<String, List<JSONObject>> map = CacheUntil.get(RedisCacheEmun.DICT_CACHE, CacheUntil.DICT_ITEM, Map.class);
		List<JSONObject> list = map.get(parentName);
		for (JSONObject json : list) {
			if (code.equals(json.get("code").toString())) {
				value = json.get("name").toString();
				break;
			}
		}
		return value;
	}
	
	public static void del(RedisTemplate<String, Object> redisTemplate, Object key) {
		final String keyStr = key.toString();
		if (StringUtils.isNull(keyStr)) {
			return;
		}
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyByte = keyStr.getBytes();
				connection.del(keyByte);
				return 1L;
			}
		});
	}

}
