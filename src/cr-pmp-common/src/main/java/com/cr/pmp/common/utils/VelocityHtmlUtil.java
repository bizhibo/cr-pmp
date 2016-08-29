package com.cr.pmp.common.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;

import com.cr.pmp.common.result.Result;

/**
 * @描述 : vm的html工具类
 * @创建者：liushengsong
 * @创建时间： 2014-6-19下午1:58:05
 * 
 */
public class VelocityHtmlUtil {
	/** 编码方式 **/
	private static final String ENC = "utf-8";
	private static final String REQUEST_NAME = "request";
	/** 配置文件 **/
	private static final Properties PROPERTIES = new Properties();
	/** vm加载工具类 **/
	private Map<String, Object> velocityTools;
	/** vm加载url **/
	private Map<String, ToolsUrl> velocityUrl;

	/**
	 * @描述 : 获取vm模版的html格式字符串
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11上午9:40:44
	 * 
	 * @param result
	 * @param vmPath
	 * @return
	 */
	public String loadHtml(Result result, String vmPath,
			HttpServletRequest request) {
		StringWriter htmlWriter = new StringWriter();
		this.initContext(result, vmPath, htmlWriter, request);
		return htmlWriter.toString();
	}

	/**
	 * @描述 : 输出vm模版的html格式文件
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11上午9:40:29
	 * 
	 * @param result
	 * @param vmPath
	 * @param filePath
	 * @return
	 * @throws RuntimeException
	 */
	public void outHtmlFile(Result result, String vmPath, String filePath,
			HttpServletRequest request) throws RuntimeException {
		FileOutputStream outputStream = null;
		BufferedWriter writer = null;
		try {
			outputStream = new FileOutputStream(filePath);
			writer = new BufferedWriter(new OutputStreamWriter(outputStream,
					ENC));
			String s = this.loadHtml(result, vmPath, request);
			writer.write(s);
		} catch (FileNotFoundException e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			CloseUtil.close(writer);
			CloseUtil.close(outputStream);
		}
	}

	/**
	 * @描述 : 初始化Context
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11上午9:39:19
	 * 
	 * @param result
	 * @param vmPath
	 * @param writer
	 */
	private void initContext(Result result, String vmPath, Writer writer,
			HttpServletRequest request) {
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		Template template = Velocity.getTemplate(realPath + vmPath);
		VelocityContext context = new VelocityContext();
		context.put(REQUEST_NAME, request);
		mergeContext(context);
		if (result != null) {
			Map<String, Object> model = result.getModel();
			Set<String> keySet = model.keySet();
			if (keySet != null && keySet.size() > 0) {
				for (String key : keySet) {
					context.put(key, model.get(key));
				}
			}
		}
		template.merge(context, writer);
	}

	/**
	 * @描述 : 装填Context
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-23下午2:03:44
	 * 
	 * @param context
	 */
	private void mergeContext(Context context) {
		mergeUrl(context, this.velocityUrl);
		merge(context, this.velocityTools);
	}

	/**
	 * @描述 : 加载工具类
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11上午9:44:27
	 * 
	 * @param context
	 * @param map
	 */
	private void merge(Context context, Map<String, Object> map) {
		if (map != null) {
			for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
				context.put(stringObjectEntry.getKey(),
						stringObjectEntry.getValue());
			}
		}
	}

	/**
	 * @描述 : 加载url工具类
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-11上午9:45:37
	 * 
	 * @param context
	 * @param map
	 */
	private void mergeUrl(Context context, Map<String, ToolsUrl> map) {
		if (map != null) {
			for (Map.Entry<String, ToolsUrl> entry : map.entrySet()) {
				String key = entry.getKey();
				ToolsUrl org = (ToolsUrl) entry.getValue();
				ToolsUrl value = org.clone();
				value.setToolsUrl(org);
				context.put(key, value);
			}
		}
	}

	public void setVelocityUrl(Map<String, ToolsUrl> velocityUrl) {
		this.velocityUrl = velocityUrl;
	}

	public void setVelocityTools(Map<String, Object> velocityTools) {
		this.velocityTools = velocityTools;
	}

	/**
	 * 初始化velocity配置
	 */
	static {
		PROPERTIES.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "");
		PROPERTIES.setProperty(RuntimeConstants.ENCODING_DEFAULT, ENC);
		PROPERTIES.setProperty(RuntimeConstants.INPUT_ENCODING, ENC);
		PROPERTIES.setProperty(RuntimeConstants.OUTPUT_ENCODING, ENC);
		Velocity.init(PROPERTIES);
	}
}
