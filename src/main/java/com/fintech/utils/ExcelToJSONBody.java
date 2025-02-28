package com.fintech.utils;

import java.util.HashMap;

public class ExcelToJSONBody {

    private final ExcelDataReader excelDataReader;

    // Constructor to allow multiple instances as needed
    public ExcelToJSONBody() {
        this.excelDataReader = new ExcelDataReader();
    }

    // Method to read payment data and return JSON HashMap
    public HashMap<String, Object> fundsTransferDataFromExcel(String filePath, String testcaseName, String sheetName) {
        HashMap<String, Object> jsonMap = new HashMap<>();

        try {
            String amountValue = excelDataReader.getDataWithTestAndColumnName(filePath, testcaseName, 
                ExcelColumnsJSONKeyMapper.FundsTransfer.AMOUNT.getJsonKey(), sheetName);
            String currencyValue = excelDataReader.getDataWithTestAndColumnName(filePath, testcaseName, 
                ExcelColumnsJSONKeyMapper.FundsTransfer.CURRENCY.getJsonKey(), sheetName);
            String fromAccount = excelDataReader.getDataWithTestAndColumnName(filePath, testcaseName, 
                ExcelColumnsJSONKeyMapper.FundsTransfer.FROM_ACCOUNT.getJsonKey(), sheetName);
            String toAccount = excelDataReader.getDataWithTestAndColumnName(filePath, testcaseName, 
                ExcelColumnsJSONKeyMapper.FundsTransfer.TO_ACCOUNT.getJsonKey(), sheetName);

            jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.AMOUNT.getJsonKey(), amountValue);
            jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.CURRENCY.getJsonKey(), currencyValue);
            jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.FROM_ACCOUNT.getJsonKey(), fromAccount);
            jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.TO_ACCOUNT.getJsonKey(), toAccount);

        } catch (Exception e) {
            System.err.println("Error: Failed to fetch data from Excel. " + e.getMessage());
        }
        
        return jsonMap;
    }
}
