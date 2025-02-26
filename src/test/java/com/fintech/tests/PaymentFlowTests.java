package com.fintech.tests;

import com.fintech.config.ConfigManager;
import com.fintech.endpoints.Endpoints;
import com.fintech.models.PaymentRequest;
import com.fintech.models.PaymentResponse;
import com.fintech.utils.ValidationUtils;
import com.fintech.data.TestDataReader; // Import TestDataReader from test folder
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PaymentFlowTests {

    @Test
    public void testPaymentFlowWithJson() {
        // Path to the JSON file
        String filePath = "src/test/resources/testdata/payment_data.json";

        // Load the PaymentRequest object from the JSON file
        PaymentRequest paymentRequest = TestDataReader.readJsonData(filePath, PaymentRequest.class);

        // Perform the API request
        Response response = RestAssured.given()
                .header("Authorization", ConfigManager.getInstance().getProperty("authToken"))
                .body(paymentRequest)
                .post(ConfigManager.getInstance().getProperty("baseURL") + Endpoints.CONFIRM_PAYMENT);

        // Deserialize the response into PaymentResponse
        PaymentResponse paymentResponse = response.as(PaymentResponse.class);

        // Validate the response
        ValidationUtils.validateStatusCode(response, 200);
        ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
    }
}