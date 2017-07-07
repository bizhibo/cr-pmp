package com.cr.pmp.common.view;

import java.util.Map;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import com.cr.pmp.common.utils.ToolsUrl;

/**
 * @描述 : 重写velocity视图的resolver 添加了spring直接注入工具类
 * @创建者：cr-pmp
 * @创建时间： 2015年9月8日上午9:08:31
 *
 */
public class VelocityViewResolver extends AbstractTemplateViewResolver {

	private Map<String, Object> toolAttributes;

	private Map<String, ToolsUrl> urlAttributes;

	public VelocityViewResolver() {
		setViewClass(requiredViewClass());
	}

	/**
	 * Requires {@link VelocityView}.
	 */
	@Override
	protected Class<?> requiredViewClass() {
		return VelocityView.class;
	}

	/**
	 * @描述 : set 工具类
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月8日上午9:12:01
	 *
	 * @param toolAttributes
	 */
	public void setToolAttributes(Map<String, Object> toolAttributes) {
		this.toolAttributes = toolAttributes;
	}

	/**
	 * @描述 : set URL工具类
	 * @创建者：cr-pmp
	 * @创建时间： 2015年9月8日上午10:44:29
	 *
	 * @param urlAttributes
	 */
	public void setUrlAttributes(Map<String, ToolsUrl> urlAttributes) {
		this.urlAttributes = urlAttributes;
	}

	/**
	 * 初始化全局的context
	 */
	@Override
	protected void initApplicationContext() {
		super.initApplicationContext();
	}

	/**
	 * 配置视图
	 */
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		VelocityView view = (VelocityView) super.buildView(viewName);
		view.setToolAttributes(toolAttributes);
		view.setUrlAttributes(urlAttributes);
		return view;
	}
}
