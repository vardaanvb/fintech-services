package com.fintech.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {



	public ArrayList<String> getData(String testcaseName, String sheetName) {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis;
		XSSFWorkbook workbook = null;
		try {
			//C:\Users\vb\Desktop\POC\E2E_Web\FintechServices\src\test\java\com\fintech\resources\TestData.xlsx
			fis = new FileInputStream("./Resources/TestData.xlsx");

			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			System.err.println("Error: The specified Excel file was not found. Please check the file path.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(
					"Error: Unable to read the Excel file. There may be an issue with file permissions or corruption.");
			e.printStackTrace();
		}

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						coloumn = k;

					}

					k++;
				}
			//	System.out.println(coloumn);

		
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {

					

						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}

				}

			}
		}
		return a;

	}
	
	public static void main(String[] args) {
		ExcelReader ex = new ExcelReader();
		ArrayList data = ex.getData("Payment", "Sheet1");
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		
	}

}
