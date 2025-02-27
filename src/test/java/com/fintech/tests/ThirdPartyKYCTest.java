package com.fintech.tests;

import com.fintech.endpoints.ApplicationEndpoints;
import com.fintech.endpoints.ThirdPartyEndPoints;
import com.fintech.models.PaymentRequest;
import com.fintech.models.PaymentResponse;

import com.fintech.utils.ApiRequestHelper;
import com.fintech.utils.ValidationUtils;
import com.fintech.utils.ApiRetryAnalyzer;
import com.fintech.config.ConfigManager;
import com.fintech.data.TestDataReader;
import io.restassured.response.Response;
import org.testng.annotations.Test;



public class ThirdPartyKYCTest {

    private static final String sheetName = ConfigManager.getInstance().getProperty("paymentSheetName");

    @Test(retryAnalyzer = ApiRetryAnalyzer.class)
    public void testKYCAndPaymentFlow() {
        String userId = ConfigManager.getInstance().getProperty("userId");  
        String thirdPartyBaseUrl = ConfigManager.getInstance().getProperty("thirdPartyBaseUrl");  
        String kycEndpoint = ThirdPartyEndPoints.KYC_END_POINT;
        String kycQueryParams = "userId=" + userId;

       
        Response kycResponse = ApiRequestHelper.makeThirdPartyApiRequest(thirdPartyBaseUrl, kycEndpoint, kycQueryParams);

     
        String kycStatus = kycResponse.jsonPath().getString("status");  // assuming response has status field
        if ("completed".equalsIgnoreCase(kycStatus)) {
      
            PaymentRequest paymentRequest = TestDataReader.readJsonData("src/test/resources/testdata/payment_data.json", PaymentRequest.class);
            Response response = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

            PaymentResponse paymentResponse = response.as(PaymentResponse.class);
            ValidationUtils.validateStatusCode(response, 200);
            ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
        } else {
        
            System.out.println("KYC Status: " + kycStatus + ". Payment cannot be processed.");
            assert false : "Payment cannot proceed because KYC is not completed.";
        }
    }

  
}
