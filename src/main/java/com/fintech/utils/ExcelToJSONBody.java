package com.fintech.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class ExcelToJSONBody {

	private static ExcelToJSONBody instance;
	ExcelDataReader ex = new 	ExcelDataReader();

	// Private constructor to prevent instantiation from outside
	private ExcelToJSONBody() {
	}

	// Public static method to provide the single instance
	public static ExcelToJSONBody getInstance() {
		if (instance == null) {
			synchronized (ExcelToJSONBody.class) {
				if (instance == null) {
					instance = new ExcelToJSONBody();
				}
			}
		}
		return instance;
	}

	// Method to read payment data and return JSON HashMap
	public HashMap<String, Object> fundsTransferDataFromExcel(String filePath, String testcaseName, String sheetName) {

		 
		String amountValue  = ex.getDataWithTestAndColumnName(filePath, testcaseName, ExcelColumnsJSONKeyMapper.FundsTransfer.AMOUNT.getJsonKey(),  sheetName);
		String currencyValue  = ex.getDataWithTestAndColumnName(filePath, testcaseName, ExcelColumnsJSONKeyMapper.FundsTransfer.CURRENCY.getJsonKey(),  sheetName);
		String fromAccount  = ex.getDataWithTestAndColumnName(filePath, testcaseName, ExcelColumnsJSONKeyMapper.FundsTransfer.FROM_ACCOUNT.getJsonKey(),  sheetName);
		String toAccount  = ex.getDataWithTestAndColumnName(filePath, testcaseName, ExcelColumnsJSONKeyMapper.FundsTransfer.TO_ACCOUNT.getJsonKey(),  sheetName);

		HashMap<String, Object> jsonMap = new HashMap<>();

		try {
			jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.AMOUNT.getJsonKey(), amountValue);
			jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.CURRENCY.getJsonKey(), currencyValue);
			jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.FROM_ACCOUNT.getJsonKey(), fromAccount);
			jsonMap.put(ExcelColumnsJSONKeyMapper.FundsTransfer.TO_ACCOUNT.getJsonKey(), toAccount);
					
		} catch (Exception e) {
			System.out.println("Error: An unexpected error occurred while fetching data from Excel.");
			System.out.println("Exception Message: " + e.getMessage());
		}
		return jsonMap;
	}
}
