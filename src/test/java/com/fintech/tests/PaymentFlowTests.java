package com.fintech.tests;

import com.fintech.config.ConfigManager;
import com.fintech.endpoints.Endpoints;
import com.fintech.models.PaymentRequest;
import com.fintech.models.PaymentResponse;
import com.fintech.models.PaymentResponseMain;
import com.fintech.models.PaymentResponseTimeStamp;
import com.fintech.utils.ValidationUtils;
import com.fintech.data.TestDataReader; // Import TestDataReader from test folder
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import org.testng.annotations.Test;

public class PaymentFlowTests {

	@Test
	public void testPaymentFlowWithBearerToken() {
		// Path to the JSON file
		String filePath = "src/test/resources/testdata/payment_data.json";

		// Load the PaymentRequest object from the JSON file
		PaymentRequest paymentRequest = TestDataReader.readJsonData(filePath, PaymentRequest.class);

		// Perform the API request
		RestAssured.baseURI = ConfigManager.getInstance().getBaseUrl();
		Response response = RestAssured.given()
				.header("Authorization", ConfigManager.getInstance().getProperty("authToken")).body(paymentRequest)
				.post(ConfigManager.getInstance().getProperty("baseURL") + Endpoints.CONFIRM_PAYMENT);

		// Deserialize the response into PaymentResponse
		PaymentResponse paymentResponse = response.as(PaymentResponse.class);

		// Validate the response
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	}

	@Test
	public void testPaymentFlowWithOAuth() {
		// Path to the JSON file
		String filePath = "src/test/resources/testdata/payment_data.json";
		String oAuthBaseURL = ConfigManager.getInstance().getProperty(filePath);
		String oAuthClientID = ConfigManager.getInstance().getProperty(filePath);
		String oAuthClientSecretL = ConfigManager.getInstance().getProperty(filePath);
		String oAuthGrantType = ConfigManager.getInstance().getProperty(filePath);
		String oAuthScope = ConfigManager.getInstance().getProperty(filePath);

		// Load the PaymentRequest object from the JSON file
		PaymentRequest paymentRequest = TestDataReader.readJsonData(filePath, PaymentRequest.class);

		// Perform the API request

		Response oAuthResponse = RestAssured.given().formParam("client_id", oAuthClientID)
				.formParam("client_secret", oAuthClientSecretL).formParam("grant_type", oAuthGrantType)
				.formParam("scope", oAuthScope).when().post(oAuthBaseURL);
		String responseString = oAuthResponse.toString();

		JsonPath jp = new JsonPath(responseString);
		String accessToken = jp.getString("access_Token");

		RestAssured.baseURI = ConfigManager.getInstance().getBaseUrl();
		Response response = RestAssured.given().header("Authorization", accessToken).body(paymentRequest)
				.post(ConfigManager.getInstance().getProperty("baseURL") + Endpoints.CONFIRM_PAYMENT);

		// Deserialize the response into PaymentResponse
		PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);

		// Validate the response
		ValidationUtils.validateStatusCode(response, 200);
		ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
		ValidationUtils.validateResponseBody(paymentResponse.getMessage(), "Payment completed successfully");

		List<PaymentResponseTimeStamp> event = paymentResponse.getDetails().getTimestamps();
		ValidationUtils.validateResponseBody(paymentResponse.getDetails().getTimestamps().get(0).getEvent(),
				"Initiated");
		ValidationUtils.validateResponseBody(paymentResponse.getDetails().getTimestamps().get(1).getEvent(),
				"Pending");
		ValidationUtils.validateResponseBody(paymentResponse.getDetails().getTimestamps().get(2).getEvent(),
				"Completed");
		
		for (int i = 0; i < event.size(); i++) {
		// iterate over the list to validate data. 
		}
	}
}