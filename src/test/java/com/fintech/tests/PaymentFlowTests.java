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

public class PaymentFlowTests extends BaseTest {

	String sheetName;
	

	@Test
	public void testPaymentFlowWithJsonBody() {

		Response response = ApiRequestHelper.makeApiRequest(INT_BASE_URL, "bearer", "POST",
				ApplicationEndpoints.CONFIRM_PAYMENT, Payload.makePayment());

		// Deserialize the response into PaymentResponseMain
		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);

		// Validate the response
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

	@Test
	public void testPaymentFlowWithPOJOBearerToken() {
		PaymentRequestMain paymentRequest = JsonFileParser.readJsonData(Filepaths.PAYMENT_REQUEST_JSON,
				PaymentRequestMain.class);
		Response response = ApiRequestHelper.makeApiRequest(INT_BASE_URL, "bearer", "POST",
				ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

}