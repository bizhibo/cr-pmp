package com.cr.pmp.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @描述 : URL工具类用于页面的域名切换分页等
 * @创建者：cr-pmp
 * @创建时间： 2014-6-3下午4:59:26
 * 
 */
public class ToolsUrl implements Cloneable {
	private String protocol = "http";
	private String host;
	private int port = -1;
	private String path;
	private String contextPath;
	private boolean reset;
	private boolean filter = true;
	private Map<String, Object> query = new LinkedHashMap<String, Object>();
	private ToolsUrl toolsUrl;
	private ToolsUrlIntercept intercept;
	private String charsetName = "utf-8";

	/***
	 * @描述 : 获取url地址
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-3下午5:02:54
	 * 
	 * @return
	 */
	public String render() {
		ToolsUrl url = null;
		try {
			if (this.intercept != null) {
				url = new ToolsUrl();
				url.query = new LinkedHashMap<String, Object>();
				url.query.putAll(this.query);
				setToolsUrlValue(url, this);
			}
			String str = doIt();
			return str;
		} finally {
			if (this.intercept != null && url != null) {
				setToolsUrlValue(this, url);
				this.query.putAll(url.query);
			}
		}
	}

	/**
	 * @描述 : 初始化url类
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-3下午5:00:47
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String doIt() {
		String str = null;
		try {
			if (this.intercept != null) {
				this.intercept.doIntercept(this);
			}
			String path = prefixPath(this.contextPath, this.path);
			URL url = new URL(this.protocol, this.host, this.port, path);
			if (url.getDefaultPort() == url.getPort()) {
				url = new URL(this.protocol, this.host, -1, path);
			}
			str = url.toString();
		} catch (Exception e) {
			str = "/";
		}
		StringBuilder builder = new StringBuilder(str);
		if (!this.query.isEmpty()) {
			for (String key : this.query.keySet()) {
				Object obj = this.query.get(key);
				if (obj instanceof List) {
					List list = (List) obj;
					for (Iterator iterator = list.iterator(); iterator
							.hasNext();) {
						Object o = iterator.next();
						setValue(builder, key, o);
					}
				} else {
					if (obj instanceof Map) {
						Map map = (Map) obj;
						for (Iterator iterator = map.keySet().iterator(); iterator
								.hasNext();) {
							Object o = iterator.next();
							setValue(builder, o == null ? "" : o.toString(),
									map.get(o));
						}
					} else if (obj != null && obj.getClass().isArray()) {
						Object[] arrays = (Object[]) (Object[]) obj;
						for (Object o : arrays) {
							setValue(builder, key, o);
						}
					} else {
						setValue(builder, key, obj);
					}
				}
			}
			return builder.replace(str.length(), str.length() + 1, "?")
					.toString();
		}
		return str;
	}

	/**
	 * @描述 : 获取路径
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-3下午5:03:10
	 *
	 * @param contextPath
	 * @param path
	 * @return
	 */
	public String prefixPath(String contextPath, String path) {
		String returnPath = null;
		if (path == null || contextPath == null) {
			if (path == null && contextPath == null) {
				returnPath = "/";
			} else {
				if (contextPath == null) {
					returnPath = path;
				} else {
					returnPath = contextPath;
				}
			}
		} else {
			if (contextPath.endsWith("/") && path.startsWith("/")) {
				returnPath = contextPath + path.substring(1);
			} else {
				returnPath = contextPath + path;
			}
		}
		return returnPath;
	}

	/**
	 * @描述 : 拼装url参数
	 * @创建者：cr-pmp
	 * @创建时间： 2014-6-3下午5:03:23
	 *
	 * @param builder
	 * @param key
	 * @param o
	 */
	public void setValue(StringBuilder builder, String key, Object o) {
		String value = o == null ? "" : o.toString();
		if (value.length() > 0) {
			String str1 = encodeUrl(value);
			builder.append("&").append(key).append("=").append(str1);
		} else if (!this.filter) {
			builder.append("&").append(key).append("=");
		}
	}

	@SuppressWarnings("deprecation")
	public String encodeUrl(String value) {
		String str1;
		if (StringUtils.isNotBlank(this.charsetName))
			try {
				str1 = URLEncoder.encode(value, this.charsetName);
			} catch (UnsupportedEncodingException e) {
				str1 = value;
			}
		else {
			str1 = URLEncoder.encode(value);
		}
		return str1;
	}

	public String toString() {
		String s = render();
		if (!this.reset) {
			this.reset = true;
			reset();
		}
		this.reset = false;
		return s;
	}

	public void reset() {
		try {
			this.reset = true;
			this.query.clear();
			this.query.putAll(this.toolsUrl.query);
			this.toolsUrl.setToolsUrlValue(this, this.toolsUrl);
		} catch (Exception e) {
			LogUtils.error("copyProperties error!", e);
		}
	}

	public void setUrl(String url) throws MalformedURLException {
		URL a = new URL(url);
		this.protocol = a.getProtocol();
		this.host = a.getHost();
		this.port = a.getPort();
		this.contextPath = a.getPath();
		String queryString = a.getQuery();
		if (!StringUtils.isEmpty(queryString))
			this.query.putAll(getQueryMap(queryString));
	}

	private Map<String, Object> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (String param : params) {
			String[] strings = param.split("=");
			String name = strings[0];
			String value = null;
			if (strings.length > 1) {
				value = strings[1];
			}
			map.put(name, value);
		}
		return map;
	}

	public ToolsUrl clone() {
		ToolsUrl clone = new ToolsUrl();
		setToolsUrlValue(clone, this);
		clone.query = new LinkedHashMap<String, Object>();
		clone.query.putAll(this.query);
		return clone;
	}

	private void setToolsUrlValue(ToolsUrl dest, ToolsUrl src) {
		dest.protocol = src.protocol;
		dest.host = src.host;
		dest.port = src.port;
		dest.contextPath = src.contextPath;
		dest.path = src.path;
		dest.intercept = src.intercept;
	}

	public void cleanQueryMap() {
		if ((this.query != null) && (!this.query.isEmpty()))
			this.query.clear();
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getCharsetName() {
		return this.charsetName;
	}

	public boolean isFilter() {
		return this.filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public void setToolsUrl(ToolsUrl toolsUrl) {
		this.toolsUrl = toolsUrl;
	}

	public String getContextPath() {
		return this.contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public void setIntercept(ToolsUrlIntercept intercept) {
		this.intercept = intercept;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	public Map<String, Object> getQuery() {
		return this.query;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ToolsUrl addQueryData(String name, Object value) {
		this.query.put(name, value);
		return this;
	}

	public ToolsUrl getTarget(String target) {
		this.path = target;
		return this;
	}

}
