package com.fintech.tests;

import com.fintech.endpoints.ApplicationEndpoints;
import com.fintech.endpoints.ThirdPartyEndPoints;
import com.fintech.helper.ApiRequestHelper;
import com.fintech.helper.ApiRetryAnalyzer;
import com.fintech.helper.ThirdPartyKYCHelper;
import com.fintech.helper.ValidationUtils;
import com.fintech.models.PaymentRequestMain;
import com.fintech.models.PaymentResponseMain;
import com.fintech.common.BaseTest;
import com.fintech.config.ConfigManager;
import com.fintech.data.TestDataReader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ThirdPartyKYCTest extends BaseTest {

    private static final String sheetName = ConfigManager.getInstance().getProperty("paymentSheetName");
    private static final String DIGILOCKER_AUTH_TOKEN=ConfigManager.getInstance().getProperty("digiLockerAuthToken");
    String userId = ConfigManager.getInstance().getProperty("userId");  

    @Test(retryAnalyzer = ApiRetryAnalyzer.class)
    public void testKYCAndPaymentFlow() {
        
        String thirdPartyBaseUrl = ConfigManager.getInstance().getProperty("thirdPartyBaseUrl");  
        String kycEndpoint = ThirdPartyEndPoints.KYC_END_POINT;
        String kycQueryParams = "userId=" + userId;

        Response kycResponse = ApiRequestHelper.makePreAuthKYCRequest(govtAuthToken, thirdPartyBaseUrl, kycEndpoint, kycQueryParams);
        
        ThirdPartyKYCHelper.KYCVerficationAndConfirmPayment(kycResponse);
      
    }

    @Test
    public void testDigilockerUserVerification() {
       
        Response digilockerResponse = ApiRequestHelper.verifyDigilockerUser(DIGILOCKER_URL, DIGILOCKER_AUTH_TOKEN, userId);
        String isRegistered = digilockerResponse.jsonPath().getString("registered");
        
        if ("true".equalsIgnoreCase(isRegistered)) {
            System.out.println("User is registered on Digilocker.");
        } else {
            System.out.println("User is NOT registered on Digilocker.");
        }
    }
}
