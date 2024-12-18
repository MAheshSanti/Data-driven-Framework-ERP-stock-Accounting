package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {

	XSSFWorkbook wb;
	//constructor for reading excel file

	public ExcelFileUtil(String Excelpath) throws Throwable
	{
		FileInputStream fi= new FileInputStream(Excelpath);
		wb = new XSSFWorkbook(fi);
	}

	// method for counting no of row in a sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}

	// methods foe reading data for cell

	public String getCellData(String sheetname,int row,int col)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()==CellType.NUMERIC)
		{

			int celldat = (int)	wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
			data= String.valueOf(celldat);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}

	// method for writing data into workbook
	public void setCellData(String sheetname,int row,int column,String status,String writeExcel) throws Throwable
	{
		XSSFSheet ws = wb.getSheet(sheetname);
		XSSFRow rownum = ws.getRow(row);
		XSSFCell cell=rownum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font= wb.createFont();
			font.setBold(true);
			font.setColor(IndexedColors.GREEN.getIndex());
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font= wb.createFont();
			font.setBold(true);
			font.setColor(IndexedColors.RED.getIndex());
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font= wb.createFont();
			font.setBold(true);
			font.setColor(IndexedColors.BLUE.getIndex());
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
	}
}
