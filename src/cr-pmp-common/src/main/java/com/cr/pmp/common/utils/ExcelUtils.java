package com.cr.pmp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @描述 : 根据模版导出excel工具
 * @创建者：cr-pmp
 * @创建时间： 2014-6-6上午9:49:08
 * 
 */
public class ExcelUtils {
	private HSSFWorkbook workbook;

	/**
	 * @描述 : 初始化Excel
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-5下午5:33:49
	 * 
	 * @param fileName
	 * @param workbook
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public ExcelUtils(String tempPath) throws FileNotFoundException,
			IOException {
		File tempFile = new File(tempPath);
		this.workbook = new HSSFWorkbook(new POIFSFileSystem(
				new FileInputStream(tempFile)));
	}

	/**
	 * @描述 : 导出Excel文件
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-5下午5:36:51
	 * 
	 * @param out
	 */
	public void exportXLS(OutputStream outputStream) throws RuntimeException {
		try {
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			CloseUtils.close(outputStream);
		}
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

}
