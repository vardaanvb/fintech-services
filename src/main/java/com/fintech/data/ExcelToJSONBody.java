package com.fintech.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.fintech.config.JSONToExcelMapper;

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
	public HashMap<String, Object> paymentDataFromExcel(String testcaseName, String sheetName) {

		
		String amountValue  = ex.getData(testcaseName, JSONToExcelMapper.Payments.AMOUNT.getJsonKey(),  sheetName);
		String currencyValue  = ex.getData(testcaseName, JSONToExcelMapper.Payments.AMOUNT.getJsonKey(),  sheetName);
		String paymentMethodValue  = ex.getData(testcaseName, JSONToExcelMapper.Payments.AMOUNT.getJsonKey(),  sheetName);

		HashMap<String, Object> jsonMap = new HashMap<>();

		try {
			jsonMap.put(JSONToExcelMapper.Payments.AMOUNT.getJsonKey(), amountValue);
			jsonMap.put(JSONToExcelMapper.Payments.CURRENCY.getJsonKey(), currencyValue);
			jsonMap.put(JSONToExcelMapper.Payments.PAYMENT_METHOD.getJsonKey(), paymentMethodValue);
					
		} catch (Exception e) {
			System.out.println("Error: An unexpected error occurred while fetching data from Excel.");
			System.out.println("Exception Message: " + e.getMessage());
		}
		return jsonMap;
	}
}
