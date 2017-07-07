package com.cr.pmp.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述 : 模拟缓存工厂类
 * @创建者：cr-pmp
 * @创建时间： 2014-5-20下午1:33:32
 * 
 */
public class CachFactory {
	/** 缓存数据map **/
	private Map<String, Object> cacheMap = new HashMap<String, Object>();

	/** 单例模式须将Constructor私有化 **/
	private CachFactory() {
	}

	/**
	 * @描述 : 使用“懒加载”的单例模式
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-9下午1:14:07
	 * @return
	 */
	public static CachFactory getInstance() throws Exception {
		return CacheHolder.cachFactory;
	}

	/**
	 * @描述 : 创建缓存
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-9下午1:14:44
	 * @param key
	 * @param value
	 * @return
	 */
	public Map<String, Object> createCache(String key, Object value)
			throws Exception {
		cacheMap.put(key, value);
		return cacheMap;
	}

	/**
	 * @描述 : 获取缓存中的内容
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-9下午1:17:26
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key) throws Exception {
		return cacheMap.get(key);
	}

	/**
	 * @描述 : 利用内部静态类保证线程安全
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-9下午1:21:07
	 * 
	 */
	private static class CacheHolder {
		private static final CachFactory cachFactory = new CachFactory();
	}
}
