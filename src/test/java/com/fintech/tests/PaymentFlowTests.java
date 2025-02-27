package com.fintech.tests;

import com.fintech.config.ConfigManager;

import com.fintech.endpoints.ApplicationEndpoints;
import com.fintech.models.PaymentRequestMain;
import com.fintech.models.PaymentResponseMain;

import com.fintech.resources.Payload;
import com.fintech.utils.ApiRequestHelper;
import com.fintech.utils.ValidationUtils;
import com.fintech.data.ExcelToJSONBody;
import com.fintech.data.TestDataReader; 

import io.restassured.response.Response;

import java.util.HashMap;

import org.testng.ITestResult;

import org.testng.annotations.Test;

public class PaymentFlowTests {

	private static final String sheetName = ConfigManager.getInstance().getProperty("paymentSheetName");

	@Test
	public void testPaymentFlowWithJsonBody() {
		// Path to the JSON file
		// Perform the API request
		Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT,
				Payload.makePayment());

		// Deserialize the response into PaymentResponseMain
		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);

		// Validate the response
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

	@Test
	public void testPaymentFlowWithBearerToken() {
		PaymentRequestMain paymentRequest = TestDataReader.readJsonData("src/test/resources/testdata/payment_data.json",
				PaymentRequestMain.class);
		Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT,
				paymentRequest);

		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

	@Test
	public void testPaymentFlowWithBearerTokenExcel(ITestResult result) {

		HashMap<String, Object> jsonData = ExcelToJSONBody.getInstance()
				.paymentDataFromExcel(result.getMethod().getMethodName(), sheetName);

		Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, jsonData);

		// Deserialize the response into PaymentResponseMain
		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);

		// Validate the response
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

	@Test
	public void testPaymentFlowWithOAuth() {
		PaymentRequestMain paymentRequest = TestDataReader.readJsonData("src/test/resources/testdata/payment_data.json",
				PaymentRequestMain.class);
		Response response = ApiRequestHelper.makeApiRequest("oauth", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}
}