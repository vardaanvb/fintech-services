package com.fintech.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.NumberToTextConverter;

public class ExcelDataReader {

   

    public String getDataWithTestAndColumnName(String filePath, String rowName, String columnName, String sheetName) {
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        String cellValue = null;

        try {
            // Load the Excel file
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.err.println("Error: Sheet '" + sheetName + "' not found.");
                return null;
            }

            // Find column index based on column name
            Row headerRow = sheet.getRow(0); // Assuming first row contains column headers
            if (headerRow == null) {
                System.err.println("Error: No header row found in the sheet.");
                return null;
            }

            int columnIndex = -1;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex == -1) {
                System.err.println("Error: Column '" + columnName + "' not found.");
                return null;
            }

            // Find row index based on row name
            int rowIndex = -1;
            for (Row row : sheet) {
                Cell firstCell = row.getCell(0); // Assuming first column contains row names
                if (firstCell != null && firstCell.getCellType() == CellType.STRING
                        && firstCell.getStringCellValue().equalsIgnoreCase(rowName)) {
                    rowIndex = row.getRowNum();
                    break;
                }
            }

            if (rowIndex == -1) {
                System.err.println("Error: Row '" + rowName + "' not found.");
                return null;
            }

            // Retrieve the data from the found row and column
            Row dataRow = sheet.getRow(rowIndex);
            if (dataRow != null) {
                Cell targetCell = dataRow.getCell(columnIndex);
                if (targetCell != null) {
                    if (targetCell.getCellType() == CellType.STRING) {
                        cellValue = targetCell.getStringCellValue();
                    } else if (targetCell.getCellType() == CellType.NUMERIC) {
                        cellValue = NumberToTextConverter.toText(targetCell.getNumericCellValue());
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Excel file not found at '" + filePath + "'.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: Unable to read the Excel file.");
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                System.err.println("Error: Failed to close file streams.");
            }
        }

        return cellValue;
    }
    
    




    
        public Object[][] getData(String filePath, String sheetName) throws IOException {
        	

            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found in " + filePath);
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            Object[][] data = new Object[rowCount - 1][1]; // Exclude header row

            for (int i = 1; i < rowCount; i++) { // Start from row 1 to skip header
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    data[i - 1][0] = row.getCell(0).toString(); // Read UserID as String
                }
            }

            workbook.close();
            return data;
        
        }
    


    public static void main(String[] args) {
    // To Test Run
    }
}
