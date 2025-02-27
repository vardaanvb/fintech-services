package com.fintech.tests;

import com.fintech.common.BaseTest;
import com.fintech.config.ConfigManager;
import com.fintech.config.Filepaths;
import com.fintech.endpoints.ApplicationEndpoints;
import com.fintech.helper.ApiRequestHelper;
import com.fintech.helper.ValidationUtils;
import com.fintech.models.PaymentRequestMain;
import com.fintech.models.PaymentResponseMain;
import com.fintech.models.PaymentResponseTimeStamp;
import com.fintech.payload.Payload;
import com.fintech.utils.ExcelToJSONBody;
import com.fintech.utils.JsonFileParser;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import org.testng.ITestResult;

import org.testng.annotations.Test;

public class PaymentFlowTests extends BaseTest {

	String sheetName = ConfigManager.getInstance().getProperty("paymentSheetName");

	@Test
	public void testPaymentFlowWithJsonBody() {

		Response response = ApiRequestHelper.makeApiRequest(BASE_URL, "bearer", "POST",
				ApplicationEndpoints.CONFIRM_PAYMENT, Payload.makePayment());

		// Deserialize the response into PaymentResponseMain
		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);

		// Validate the response
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

	@Test
	public void testPaymentFlowWithBearerToken() {
		PaymentRequestMain paymentRequest = JsonFileParser.readJsonData(Filepaths.PAYMENT_REQUEST_JSON,
				PaymentRequestMain.class);
		Response response = ApiRequestHelper.makeApiRequest(BASE_URL, "bearer", "POST",
				ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

//	@Test
//	public void testPaymentFlowWithBearerTokenExcel(ITestResult result) {
//
//		Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT,
//				jsonData);
//
//		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
//
//		// Validate the response
//		ValidationUtils.validateStatusCode(response, 200);
//		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
//	}

	@Test
	public void FundsTransferFlowWithExcelAndOAuth(ITestResult result) {

		HashMap<String, Object> jsonData = ExcelToJSONBody.getInstance()
				.fundsTransferDataFromExcel(result.getMethod().getMethodName(), sheetName);
		Response response = ApiRequestHelper.makeApiRequest(BASE_URL, "oauth", "POST",
				ApplicationEndpoints.CREATE_FUNDS_TRANSFER, jsonData);

		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");

		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
		ValidationUtils.validateResponseBody(paymentResponse.getMessage(), "Payment completed successfully");

		List<PaymentResponseTimeStamp> eventList = paymentResponse.getDetails().getTimestamps();
		ValidationUtils.validateResponseBody(eventList.get(0).getEvent(), "Initiated");
		ValidationUtils.validateResponseBody(eventList.get(1).getEvent(), "Pending");
		ValidationUtils.validateResponseBody(eventList.get(2).getEvent(), "Completed");

		for (int i = 0; i < eventList.size(); i++) {
			// iterate over the list to validate data.
		}
	}
}