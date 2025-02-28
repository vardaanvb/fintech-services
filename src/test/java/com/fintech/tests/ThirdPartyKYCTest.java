package com.fintech.tests;

import com.fintech.helper.ApiRequestHelper;
import com.fintech.helper.DataProviderHelper;
import com.fintech.helper.ThirdPartyHelper;
import com.fintech.retry.ApiRetryAnalyzer;
import com.fintech.utils.ConfigManager;
import com.fintech.common.BaseTest;
import com.fintech.config.APIParams;
import com.fintech.config.Filepaths;
import com.fintech.config.ThirdPartyEndPoints;

import io.restassured.response.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ThirdPartyKYCTest extends BaseTest {

	@Test(retryAnalyzer = ApiRetryAnalyzer.class)
	public void KYCAndPaymentFlow() {
		String userId = ConfigManager.getInstance().getProperty("userId");
		String thirdPartyBaseUrl = ConfigManager.getInstance().getProperty("thirdPartyBaseUrl");
		String kycEndpoint = ThirdPartyEndPoints.KYC_END_POINT;
		String kycQueryParams = "userId=" + userId;

		Response kycResponse = ApiRequestHelper.makeGetRequestWithAuthTokenPresent(GOVT_PRE_AUTH_URL,
				APIParams.thirdPartyParams(), thirdPartyBaseUrl, kycEndpoint);

		ThirdPartyHelper.KYCVerficationAndConfirmPayment(INT_BASE_URL, kycResponse, Filepaths.PAYMENT_REQUEST_JSON);

	}
	
	@Test(dataProvider = "userIDProvider", dataProviderClass = DataProviderHelper.class, retryAnalyzer = ApiRetryAnalyzer.class)
	
	public void DigilockerUserVerification(String userId) {

		
		Response digilockerResponse = ApiRequestHelper.verifyDigilockerUser(DIGILOCKER_URL, DIGILOCKER_AUTH_TOKEN,
				userId);

		ThirdPartyHelper.verifyDigiLockerRegistrationResponse(digilockerResponse);
	}
}
