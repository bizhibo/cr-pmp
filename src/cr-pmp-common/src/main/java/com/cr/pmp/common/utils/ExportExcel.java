package com.cr.pmp.common.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @描述 : 导出excel
 * @创建者：liushengsong
 * @创建时间： 2014-6-6上午9:49:08
 * 
 */
public class ExportExcel {
	private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DOUBLEFORMAT = " #,##0.00 ";
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private String fileName;

	/**
	 * @描述 : 初始化Excel
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:33:49
	 * 
	 * @param fileName
	 * @param workbook
	 */
	public ExportExcel(String fileName) {
		this.fileName = fileName;
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet();
	}

	/**
	 * @描述 : 导出Excel文件
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:36:51
	 * 
	 * @param out
	 */
	public void exportXLS() throws RuntimeException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LogUtils.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			CloseUtils.close(out);
		}
	}

	/**
	 * @描述 : 增加一行
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:37:15
	 * 
	 * @param index
	 */
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}

	/**
	 * @描述 : 设置单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:37:23
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, String value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	/**
	 * @描述 : 设置带样式的单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:38:05
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, Date value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellValue(value);
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATEFORMAT)); // 设置cell样式为定制的日期格式
		cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
	}

	/**
	 * @描述 : 设置单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:38:41
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, int value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	/**
	 * @描述 : 设置单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:40:04
	 * 
	 * @param index
	 * @param value
	 */
	public void setCell(int index, double value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DOUBLEFORMAT)); // 设置cell样式为定制的浮点数格式
		cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
	}
}
