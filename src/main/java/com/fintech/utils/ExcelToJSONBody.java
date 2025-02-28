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

		 
		String amountValue  = ex.getData(filePath, testcaseName, JSONToExcelMapper.FundsTransfer.AMOUNT.getJsonKey(),  sheetName);
		String currencyValue  = ex.getData(filePath, testcaseName, JSONToExcelMapper.FundsTransfer.CURRENCY.getJsonKey(),  sheetName);
		String fromAccount  = ex.getData(filePath, testcaseName, JSONToExcelMapper.FundsTransfer.FROM_ACCOUNT.getJsonKey(),  sheetName);
		String toAccount  = ex.getData(filePath, testcaseName, JSONToExcelMapper.FundsTransfer.TO_ACCOUNT.getJsonKey(),  sheetName);

		HashMap<String, Object> jsonMap = new HashMap<>();

		try {
			jsonMap.put(JSONToExcelMapper.FundsTransfer.AMOUNT.getJsonKey(), amountValue);
			jsonMap.put(JSONToExcelMapper.FundsTransfer.CURRENCY.getJsonKey(), currencyValue);
			jsonMap.put(JSONToExcelMapper.FundsTransfer.FROM_ACCOUNT.getJsonKey(), fromAccount);
			jsonMap.put(JSONToExcelMapper.FundsTransfer.TO_ACCOUNT.getJsonKey(), toAccount);
					
		} catch (Exception e) {
			System.out.println("Error: An unexpected error occurred while fetching data from Excel.");
			System.out.println("Exception Message: " + e.getMessage());
		}
		return jsonMap;
	}
}
