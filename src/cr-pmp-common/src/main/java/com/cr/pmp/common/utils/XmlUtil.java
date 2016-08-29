package com.cr.pmp.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

/**
 * @描述 : xml工具类
 * @创建者：liushengsong
 * @创建时间： 2014-6-10上午9:16:24
 */
public class XmlUtil {
	private static final XStream XSTREAM = new XStream();
	private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @描述 : 获取对象的xml格式字符串
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-10上午9:16:47
	 * 
	 * @param entity
	 * @return
	 */
	public static String toXML(Object entity) {
		StringWriter sw = new StringWriter();
		XSTREAM.toXML(entity, sw);
		return sw.toString();
	}

	/**
	 * @描述 : 输出一个xml内容格式的文件
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-10上午9:25:12
	 * 
	 * @param entity
	 * @param file
	 * @throws RuntimeException
	 */
	public static void toXML(Object entity, File file) throws RuntimeException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));// 设置写入的文件编码,解决中文问题
			XSTREAM.toXML(entity, writer);
		} catch (FileNotFoundException e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			CloseUtil.close(writer);
		}
	}

	/**
	 * @描述 : 获取xml字符串的对象
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-10上午9:16:17
	 * 
	 * @param str
	 * @return
	 * @return
	 */
	public static <T> T fromXML(String str) {
		return (T) XSTREAM.fromXML(str);
	}

	/**
	 * @描述 : 获取xml格式的文件的对象
	 * @创建者：liushengsong
	 * @创建时间： 2014-6-10上午11:22:42
	 * 
	 * @param file
	 * @return
	 */
	public static <T> T fromXML(File file) throws RuntimeException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			return (T) XSTREAM.fromXML(reader);
		} catch (FileNotFoundException e) {
			LogUtil.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			CloseUtil.close(reader);
		}
	}

	/**
	 * 初始化xstream
	 */
	static {
		/** 设置注解自动检测 **/
		XSTREAM.autodetectAnnotations(true);
		/** 设置时间格式化与时区 **/
		XSTREAM.registerConverter(new DateConverter(DATEFORMAT, null, Calendar
				.getInstance().getTimeZone()));
	}
}
