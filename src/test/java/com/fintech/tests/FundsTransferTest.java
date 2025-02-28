package com.fintech.tests;

import com.fintech.common.BaseTest;
import com.fintech.config.ApplicationEndpoints;
import com.fintech.config.Filepaths;
import com.fintech.helper.ApiRequestHelper;
import com.fintech.helper.ValidationUtils;
import com.fintech.models.PaymentRequestMain;
import com.fintech.models.PaymentResponseMain;
import com.fintech.models.PaymentResponseTimeStamp;
import com.fintech.payload.Payload;
import com.fintech.utils.ConfigManager;
import com.fintech.utils.ExcelToJSONBody;
import com.fintech.utils.JsonFileParser;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import org.testng.ITestResult;

import org.testng.annotations.Test;

public class FundsTransferTest extends BaseTest {

	String sheetName = ConfigManager.getInstance().getProperty("fundsTransferSheetName");
	

	

	@Test
	public void FundsTransferFlowWithExcelAndOAuth(ITestResult result) {
		

		
		
		HashMap<String, Object> jsonData = ExcelToJSONBody.getInstance()
				.fundsTransferDataFromExcel(Filepaths.REQUEST_EXCEL, result.getMethod().getMethodName(), sheetName);
		
		Response response = ApiRequestHelper.makeApiRequest(INT_BASE_URL, "oauth", "POST",
				ApplicationEndpoints.CREATE_FUNDS_TRANSFER, jsonData);

		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");



//		List<PaymentResponseTimeStamp> eventList = paymentResponse.getDetails().getTimestamps();
//		ValidationUtils.validateResponseBody(eventList.get(0).getEvent(), "Initiated");
//		ValidationUtils.validateResponseBody(eventList.get(1).getEvent(), "Pending");
//		ValidationUtils.validateResponseBody(eventList.get(2).getEvent(), "Completed");

//		for (int i = 0; i < eventList.size(); i++) {
//			// iterate over the list to validate data.
//		}
	}
}