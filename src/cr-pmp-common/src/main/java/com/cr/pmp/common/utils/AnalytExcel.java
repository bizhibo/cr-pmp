package com.cr.pmp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AnalytExcel {


	/**
	 * @描述 : 读取Excel的Sheet
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-5下午5:34:18
	 * 
	 * @param excl
	 * @param pageAt
	 * @return
	 */
	public static Sheet getSheet(File excl, int pageAt) throws RuntimeException {
		try {
			Workbook workbook = null;
			InputStream inputStream = new FileInputStream(excl);
			if (!inputStream.markSupported()) {
				inputStream = new PushbackInputStream(inputStream, 10);
			}
			if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {// 判断是否为2003
				workbook = new HSSFWorkbook(inputStream);
			}
			if (POIXMLDocument.hasOOXMLHeader(inputStream)) {// 判断是否为2007
				workbook = new XSSFWorkbook(OPCPackage.open(inputStream));
			}
			return workbook.getSheetAt(pageAt);
		} catch (FileNotFoundException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (InvalidFormatException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @描述 : 读取Excel表格内的值
	 * @创建者：cr-pmp
	 * @创建时间： 2014-5-5下午5:35:34
	 * 
	 * @param cell
	 * @return
	 */
	public static String getValue(Cell cell) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			default:
				return String.valueOf(cell.getStringCellValue());
			}
		} else {
			return null;
		}
	}
}
