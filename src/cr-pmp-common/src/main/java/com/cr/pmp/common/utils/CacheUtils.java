package com.cr.pmp.common.utils;



/**
 * @描述 : 本地缓存工具类
 * @创建者：cr-pmp
 * @创建时间： 2014-5-8下午4:33:17
 * 
 */
public class CacheUtils {

	/**
	 * @描述 : 缓存数据
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-20下午1:32:16
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		try {
			if (value != null) {
				CachFactory.getInstance().createCache(key, value);
				LogUtils.info("###### set success key : " + key + " ######");
			}
		} catch (Exception e) {
			LogUtils.error("###### set error ######", e);
		}
	}

	/**
	 * @描述 : 获取缓存数据
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-20下午1:32:47
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(String key) {
		try {
			if (key != null) {
				Object value = CachFactory.getInstance().getCache(key);
				return (T) value;
			} else {
				return null;
			}
		} catch (Exception e) {
			LogUtils.error("###### get error ######", e);
			return null;
		}
	}
}
