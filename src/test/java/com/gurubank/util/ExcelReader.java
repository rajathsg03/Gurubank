package com.gurubank.util;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader {
	public File file =null;
	public FileInputStream fis=null ;
	public XSSFWorkbook wb =null;
	public XSSFSheet sheet =null;
	public XSSFRow row   =null;
	public XSSFCell cell = null;
	
	 public ExcelReader()
	 {
	file = new File (utill.excelpath);
	 try {
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		fis.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	 
}
	 
	public int getRowCount(String sheetName) {
		 int rowcount = 0;
		 int index = wb.getSheetIndex(sheetName);
		 if (index == -1) {
			 return rowcount;
		 }
		 else {
			 sheet = wb.getSheetAt(index);
			 rowcount = sheet.getLastRowNum()+1;
			 sheet.getRow(0).getLastCellNum();
			 
		 }
		return rowcount;
	 }
	 
	public int getColCount(String sheetName) {
		int colcount = 0;
		colcount = sheet.getRow(0).getLastCellNum();
			
		return colcount;
	 }
	
	 public String getCellData (String sheetName, String ColName, int rowNum) {
		 if (rowNum<=0)
			 return "";
		 
		 int colnum = -1;
		 int index = wb.getSheetIndex(sheetName);
		 if (index == -1) {
			 return "";
		 }
		sheet = wb.getSheetAt(index);
		row = sheet.getRow(0);
	for (int i = 0 ; i< row.getLastCellNum(); i++ ) {
		if (row.getCell(i).getStringCellValue().trim().equals(ColName.trim()))
			colnum = i;
	}
	if (colnum == -1)
		return "";
	sheet = wb.getSheetAt(index);
	row = sheet.getRow(rowNum);
	if (row == null)
		return "";
	cell = row.getCell(colnum);	 
	if (cell == null)
		return "";
	
	return cell.getStringCellValue(); 
		 
	 }
	 
	 public String getCellData (String sheetName, int colNum, int rowNum) {
		 if (colNum<0)
			 return "";
		 int index = wb.getSheetIndex(sheetName);
		 if (index == -1) {
			 return "";
		 }
		sheet = wb.getSheetAt(index);
		row = sheet.getRow(rowNum);
	if (row == null)
		return "";
	cell = row.getCell(colNum);	 
	if (cell == null)
		return "";
	return cell.getStringCellValue();	 
	 }


}
	 
