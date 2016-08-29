package com.cr.pmp.common.view;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.NestedIOException;
import org.springframework.web.servlet.view.AbstractTemplateView;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.util.NestedServletException;

import com.cr.pmp.common.utils.ToolsUrl;

public class VelocityView extends AbstractTemplateView {

	private Map<String, Object> toolAttributes;

	private Map<String, ToolsUrl> urlAttributes;

	private String encoding;

	private boolean cacheTemplate = false;

	private VelocityEngine velocityEngine;

	private Template template;

	/**
	 * @描述 : 设置vm模版中用到的url工具类
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午10:33:19
	 *
	 * @param urlAttributes
	 */
	public void setUrlAttributes(Map<String, ToolsUrl> urlAttributes) {
		this.urlAttributes = urlAttributes;
	}

	/**
	 * @描述 : 设置vm模版上需要使用的工具类
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午10:05:35
	 *
	 * @param toolAttributes
	 */
	public void setToolAttributes(Map<String, Object> toolAttributes) {
		this.toolAttributes = toolAttributes;
	}

	/**
	 * @描述 : 设置vm模版字符编码默认是iso-8859-1
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午9:57:43
	 *
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @描述 : 获取编码方式
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午9:58:16
	 *
	 * @return
	 */
	protected String getEncoding() {
		return this.encoding;
	}

	/**
	 * @描述 : 设置模版是否缓存
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午9:58:39
	 *
	 * @param cacheTemplate
	 */
	public void setCacheTemplate(boolean cacheTemplate) {
		this.cacheTemplate = cacheTemplate;
	}

	/**
	 * @描述 : 获取模版是否已经缓存
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午9:59:03
	 *
	 * @return
	 */
	protected boolean isCacheTemplate() {
		return this.cacheTemplate;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	protected VelocityEngine getVelocityEngine() {
		return this.velocityEngine;
	}

	/**
	 * 初始化全局的context
	 */
	@Override
	protected void initApplicationContext() throws BeansException {
		super.initApplicationContext();

		if (getVelocityEngine() == null) {
			// No explicit VelocityEngine: try to autodetect one.
			setVelocityEngine(autodetectVelocityEngine());
		}
	}

	/**
	 * @描述 : 自动创建一个engine
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午10:00:55
	 *
	 * @return
	 * @throws BeansException
	 */
	protected VelocityEngine autodetectVelocityEngine() throws BeansException {
		try {
			VelocityConfig velocityConfig = BeanFactoryUtils
					.beanOfTypeIncludingAncestors(getApplicationContext(),
							VelocityConfig.class, true, false);
			return velocityConfig.getVelocityEngine();
		} catch (NoSuchBeanDefinitionException ex) {
			throw new ApplicationContextException(
					"Must define a single VelocityConfig bean in this web application context "
							+ "(may be inherited): VelocityConfigurer is the usual implementation. "
							+ "This bean may be given any name.", ex);
		}
	}

	/**
	 * 检测本地的配置
	 */
	@Override
	public boolean checkResource(Locale locale) throws Exception {
		try {
			// Check that we can get the template, even if we might subsequently
			// get it again.
			this.template = getTemplate(getUrl());
			return true;
		} catch (ResourceNotFoundException ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("No Velocity view found for URL: " + getUrl());
			}
			return false;
		} catch (Exception ex) {
			throw new NestedIOException(
					"Could not load Velocity template for URL [" + getUrl()
							+ "]", ex);
		}
	}

	/**
	 * Process the model map by merging it with the Velocity template. Output is
	 * directed to the servlet response.
	 * <p>
	 * This method can be overridden if custom behavior is needed.
	 */
	@Override
	protected void renderMergedTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		exposeHelpers(model, request);

		Context velocityContext = createVelocityContext(model, request,
				response);
		exposeHelpers(velocityContext, request, response);
		exposeToolAttributes(velocityContext, request);

		doRender(velocityContext, response);
	}

	/**
	 * Expose helpers unique to each rendering operation. This is necessary so
	 * that different rendering operations can't overwrite each other's formats
	 * etc.
	 * <p>
	 * Called by <code>renderMergedTemplateModel</code>. The default
	 * implementation is empty. This method can be overridden to add custom
	 * helpers to the model.
	 * 
	 * @param model
	 *            the model that will be passed to the template for merging
	 * @param request
	 *            current HTTP request
	 * @throws Exception
	 *             if there's a fatal error while we're adding model attributes
	 * @see #renderMergedTemplateModel
	 */
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
	}

	/**
	 * @描述 : 创建一个VelocityContext
	 * @创建者：liushengsong@eztcn.com
	 * @创建时间： 2015年9月8日上午10:02:32
	 *
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected Context createVelocityContext(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return createVelocityContext(model);
	}

	/**
	 * Create a Velocity Context instance for the given model, to be passed to
	 * the template for merging.
	 * <p>
	 * Default implementation creates an instance of Velocity's VelocityContext
	 * implementation class.
	 * 
	 * @param model
	 *            the model Map, containing the model attributes to be exposed
	 *            to the view
	 * @return the Velocity Context
	 * @throws Exception
	 *             if there's a fatal error while creating the context
	 * @see org.apache.velocity.VelocityContext
	 */
	protected Context createVelocityContext(Map<String, Object> model)
			throws Exception {
		return new VelocityContext(model);
	}

	/**
	 * Expose helpers unique to each rendering operation. This is necessary so
	 * that different rendering operations can't overwrite each other's formats
	 * etc.
	 * <p>
	 * Called by <code>renderMergedTemplateModel</code>. Default implementation
	 * delegates to <code>exposeHelpers(velocityContext, request)</code>. This
	 * method can be overridden to add special tools to the context, needing the
	 * servlet response to initialize (see Velocity Tools, for example LinkTool
	 * and ViewTool/ChainedContext).
	 * 
	 * @param velocityContext
	 *            Velocity context that will be passed to the template
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @throws Exception
	 *             if there's a fatal error while we're adding model attributes
	 * @see #exposeHelpers(org.apache.velocity.context.Context,
	 *      HttpServletRequest)
	 */
	protected void exposeHelpers(Context velocityContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		exposeHelpers(velocityContext, request);
	}

	/**
	 * Expose helpers unique to each rendering operation. This is necessary so
	 * that different rendering operations can't overwrite each other's formats
	 * etc.
	 * <p>
	 * Default implementation is empty. This method can be overridden to add
	 * custom helpers to the Velocity context.
	 * 
	 * @param velocityContext
	 *            Velocity context that will be passed to the template
	 * @param request
	 *            current HTTP request
	 * @throws Exception
	 *             if there's a fatal error while we're adding model attributes
	 * @see #exposeHelpers(Map, HttpServletRequest)
	 */
	protected void exposeHelpers(Context velocityContext,
			HttpServletRequest request) throws Exception {
	}

	/**
	 * Expose the tool attributes, according to corresponding bean property
	 * settings.
	 * <p>
	 * Do not override this method unless for further tools driven by bean
	 * properties. Override one of the <code>exposeHelpers</code> methods to add
	 * custom helpers.
	 * 
	 * @param velocityContext
	 *            Velocity context that will be passed to the template
	 * @param request
	 *            current HTTP request
	 * @throws Exception
	 *             if there's a fatal error while we're adding model attributes
	 * @see #setDateToolAttribute
	 * @see #setNumberToolAttribute
	 * @see #exposeHelpers(Map, HttpServletRequest)
	 * @see #exposeHelpers(org.apache.velocity.context.Context,
	 *      HttpServletRequest, HttpServletResponse)
	 */
	protected void exposeToolAttributes(Context velocityContext,
			HttpServletRequest request) throws Exception {
		// Expose generic attributes.
		if (this.toolAttributes != null) {
			for (Map.Entry<String, Object> entry : this.toolAttributes
					.entrySet()) {
				String attributeName = entry.getKey();
				Object tool = entry.getValue();
				try {
					velocityContext.put(attributeName, tool);
				} catch (Exception ex) {
					throw new NestedServletException(
							"Could not instantiate Velocity tool '"
									+ attributeName + "'", ex);
				}
			}
		}

		if (this.urlAttributes != null) {
			for (Map.Entry<String, ToolsUrl> entry : this.urlAttributes
					.entrySet()) {
				String attributeName = entry.getKey();
				ToolsUrl tool = entry.getValue();
				ToolsUrl clone = entry.getValue();
				tool.setToolsUrl(clone);
				try {
					velocityContext.put(attributeName, tool);
				} catch (Exception ex) {
					throw new NestedServletException(
							"Could not instantiate Velocity URL '"
									+ attributeName + "'", ex);
				}
			}
		}
	}

	/**
	 * Render the Velocity view to the given response, using the given Velocity
	 * context which contains the complete template model to use.
	 * <p>
	 * The default implementation renders the template specified by the "url"
	 * bean property, retrieved via <code>getTemplate</code>. It delegates to
	 * the <code>mergeTemplate</code> method to merge the template instance with
	 * the given Velocity context.
	 * <p>
	 * Can be overridden to customize the behavior, for example to render
	 * multiple templates into a single view.
	 * 
	 * @param context
	 *            the Velocity context to use for rendering
	 * @param response
	 *            servlet response (use this to get the OutputStream or Writer)
	 * @throws Exception
	 *             if thrown by Velocity
	 * @see #setUrl
	 * @see #getTemplate()
	 * @see #mergeTemplate
	 */
	protected void doRender(Context context, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Rendering Velocity template [" + getUrl()
					+ "] in VelocityView '" + getBeanName() + "'");
		}
		mergeTemplate(getTemplate(), context, response);
	}

	/**
	 * Retrieve the Velocity template to be rendered by this view.
	 * <p>
	 * By default, the template specified by the "url" bean property will be
	 * retrieved: either returning a cached template instance or loading a fresh
	 * instance (according to the "cacheTemplate" bean property)
	 * 
	 * @return the Velocity template to render
	 * @throws Exception
	 *             if thrown by Velocity
	 * @see #setUrl
	 * @see #setCacheTemplate
	 * @see #getTemplate(String)
	 */
	protected Template getTemplate() throws Exception {
		// We already hold a reference to the template, but we might want to
		// load it
		// if not caching. Velocity itself caches templates, so our ability to
		// cache templates in this class is a minor optimization only.
		if (isCacheTemplate() && this.template != null) {
			return this.template;
		} else {
			return getTemplate(getUrl());
		}
	}

	/**
	 * Retrieve the Velocity template specified by the given name, using the
	 * encoding specified by the "encoding" bean property.
	 * <p>
	 * Can be called by subclasses to retrieve a specific template, for example
	 * to render multiple templates into a single view.
	 * 
	 * @param name
	 *            the file name of the desired template
	 * @return the Velocity template
	 * @throws Exception
	 *             if thrown by Velocity
	 * @see org.apache.velocity.app.VelocityEngine#getTemplate
	 */
	protected Template getTemplate(String name) throws Exception {
		return (getEncoding() != null ? getVelocityEngine().getTemplate(name,
				getEncoding()) : getVelocityEngine().getTemplate(name));
	}

	/**
	 * Merge the template with the context. Can be overridden to customize the
	 * behavior.
	 * 
	 * @param template
	 *            the template to merge
	 * @param context
	 *            the Velocity context to use for rendering
	 * @param response
	 *            servlet response (use this to get the OutputStream or Writer)
	 * @throws Exception
	 *             if thrown by Velocity
	 * @see org.apache.velocity.Template#merge
	 */
	protected void mergeTemplate(Template template, Context context,
			HttpServletResponse response) throws Exception {

		try {
			template.merge(context, response.getWriter());
		} catch (MethodInvocationException ex) {
			Throwable cause = ex.getWrappedThrowable();
			throw new NestedServletException(
					"Method invocation failed during rendering of Velocity view with name '"
							+ getBeanName() + "': " + ex.getMessage()
							+ "; reference [" + ex.getReferenceName()
							+ "], method '" + ex.getMethodName() + "'",
					cause == null ? ex : cause);
		}
	}

}
