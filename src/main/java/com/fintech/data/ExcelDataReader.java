package com.fintech.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.NumberToTextConverter;

public class ExcelDataReader {

    private static final String FILE_PATH = "./Resources/TestData.xlsx";

    public String getData(String rowName, String columnName, String sheetName) {
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        String cellValue = null;

        try {
            // Load the Excel file
            fis = new FileInputStream(FILE_PATH);
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
            System.err.println("Error: Excel file not found at '" + FILE_PATH + "'.");
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

    public static void main(String[] args) {
        ExcelDataReader ex = new ExcelDataReader();
        String data = ex.getData("Payment", "Amount", "Sheet1"); // Example usage
        System.out.println("Retrieved Value: " + data);
    }
}
