package com.fintech.tests;

import com.fintech.endpoints.ApplicationEndpoints;
import com.fintech.models.PaymentRequestMain;
import com.fintech.models.PaymentResponseMain;
import com.fintech.models.PaymentResponseMain;
import com.fintech.models.PaymentResponseTimeStamp;
import com.fintech.resources.Payload;
import com.fintech.utils.ApiRequestHelper;
import com.fintech.utils.ValidationUtils;
import com.fintech.config.ConfigManager;
import com.fintech.data.ExcelToJSONBody;
import com.fintech.data.TestDataReader;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class PaymentFlowTests2 {

    private static final String sheetName = ConfigManager.getInstance().getProperty("paymentSheetName");

    /**
     * Test payment flow with JSON body using Bearer Token.
     */
    @Test
    public void testPaymentFlowWithJsonBody() {
        PaymentRequestMain paymentRequest = TestDataReader.readJsonData("src/test/resources/testdata/payment_data.json", PaymentRequestMain.class);
        Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, Payload.makePayment());

        PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
        ValidationUtils.validateStatusCode(response, 200);
        ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
    }

    /**
     * Test payment flow with Bearer Token authentication.
     */
    @Test
    public void testPaymentFlowWithBearerToken() {
        String filePath = "src/test/resources/testdata/payment_data.json";
        PaymentRequestMain paymentRequest = TestDataReader.readJsonData(filePath, PaymentRequestMain.class);

        Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

        PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
        ValidationUtils.validateStatusCode(response, 200);
        ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
    }

    /**
     * Test payment flow with Bearer Token authentication using data from Excel.
     */
    @Test
    public void testPaymentFlowWithBearerTokenExcel(ITestResult result) {
        HashMap<String, Object> jsonData = ExcelToJSONBody.getInstance().paymentDataFromExcel(result.getMethod().getMethodName(), sheetName);

        Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, jsonData);

        PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
        ValidationUtils.validateStatusCode(response, 200);
        ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
    }

    /**
     * Test payment flow with OAuth authentication.
     */
    @Test
    public void testPaymentFlowWithOAuth() {
        String filePath = "src/test/resources/testdata/payment_data.json";
        PaymentRequestMain paymentRequest = TestDataReader.readJsonData(filePath, PaymentRequestMain.class);

        // Fetch OAuth token dynamically
        String oAuthBaseURL = ConfigManager.getInstance().getProperty("OAuthBaseURL");
        String oAuthClientID = ConfigManager.getInstance().getProperty("OauthClientID");
        String oAuthClientSecret = ConfigManager.getInstance().getProperty("OauthClientSecret");
        String oAuthGrantType = ConfigManager.getInstance().getProperty("OauthGrantType");
        String oAuthScope = ConfigManager.getInstance().getProperty("OauthScope");

        Response oAuthResponse = ApiRequestHelper.makeApiRequest("oauth", "POST", oAuthBaseURL, null);

        String accessToken = oAuthResponse.jsonPath().getString("access_token");

        // Now, perform the actual payment flow with the obtained OAuth token
        Response response = ApiRequestHelper.makeApiRequest("oauth", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

        PaymentResponseMain paymentResponse = response.as(PaymentResponseMain.class);
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
