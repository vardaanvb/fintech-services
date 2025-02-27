package com.fintech.tests;


import com.fintech.endpoints.ThirdPartyEndPoints;
import com.fintech.helper.ApiRequestHelper;
import com.fintech.helper.ThirdPartyHelper;
import com.fintech.retry.ApiRetryAnalyzer;
import com.fintech.common.BaseTest;
import com.fintech.config.ConfigManager;
import com.fintech.config.Filepaths;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ThirdPartyKYCTest extends BaseTest {
	private static final String DIGILOCKER_AUTH_TOKEN = ConfigManager.getInstance().getProperty("digiLockerAuthToken");
	
	String userId = ConfigManager.getInstance().getProperty("userId");

	@Test(retryAnalyzer = ApiRetryAnalyzer.class)
	public void testKYCAndPaymentFlow() {

		String thirdPartyBaseUrl = ConfigManager.getInstance().getProperty("thirdPartyBaseUrl");
		String kycEndpoint = ThirdPartyEndPoints.KYC_END_POINT;
		String kycQueryParams = "userId=" + userId;

		Response kycResponse = ApiRequestHelper.makeKYCRequest(govtPreAuthToken, thirdPartyBaseUrl, kycEndpoint,
				kycQueryParams);

		ThirdPartyHelper.KYCVerficationAndConfirmPayment(BASE_URL, kycResponse, Filepaths.PAYMENT_REQUEST_JSON);

	}

	@Test
	public void testDigilockerUserVerification() {

		Response digilockerResponse = ApiRequestHelper.verifyDigilockerUser(DIGILOCKER_URL, DIGILOCKER_AUTH_TOKEN,
				userId);

		ThirdPartyHelper.verifyDigiLockerRegistrationResponse(digilockerResponse);
	}
}
