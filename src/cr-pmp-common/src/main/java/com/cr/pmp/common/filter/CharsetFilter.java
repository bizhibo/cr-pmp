package com.cr.pmp.common.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cr.pmp.common.utils.LogUtils;

/**
 * @描述 : 字符编码过滤器
 * @创建者：liushengsong
 * @创建时间： 2014-6-11下午2:52:23
 * 
 */
public class CharsetFilter implements Filter {
	/** 默认使用utf-8 **/
	public static final String DEFAULT_CHARSET_VALUE = "utf-8";
	private Pattern inputCharsetPattern;
	private String defaultCharset;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.inputCharsetPattern = Pattern.compile("_charset_=([\\w-]+)", 2);
		if (this.defaultCharset == null) {
			this.defaultCharset = DEFAULT_CHARSET_VALUE;
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		try {
			String queryString = request.getQueryString();
			if (queryString != null) {
				Matcher matcher = this.inputCharsetPattern.matcher(queryString);

				if (matcher.find()) {
					MatchResult matchResult = matcher.toMatchResult();
					String charset = matchResult.group(1);
					request.setCharacterEncoding(charset);
					request.getParameterNames();
				}
			} else {
				request.setCharacterEncoding(this.defaultCharset);
			}
		} catch (UnsupportedEncodingException e) {
			try {
				request.setCharacterEncoding(this.defaultCharset);
			} catch (UnsupportedEncodingException ee) {
				LogUtils.error("Failed to set INPUT charset to "
						+ this.defaultCharset, e);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
