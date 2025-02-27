package com.fintech.helper;



import org.testng.Assert;

import com.fintech.endpoints.ApplicationEndpoints;
import com.fintech.models.PaymentRequestMain;
import com.fintech.models.PaymentResponseMain;
import com.fintech.utils.JsonFileParser;

import io.restassured.response.Response;

public class ThirdPartyHelper {

	public static void KYCVerficationAndConfirmPayment(String baseURL, Response response, String paymentRequestJsonFilePath) {

		String kycStatus = response.jsonPath().getString("status");
		if ("completed".equalsIgnoreCase(kycStatus)) {

			PaymentRequestMain paymentRequest = JsonFileParser.readJsonData(paymentRequestJsonFilePath,
					PaymentRequestMain.class);
			Response confirmPaymentResponse = ApiRequestHelper.makeApiRequest(baseURL, "bearer", "POST",
					ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

			PaymentResponseMain paymentResponse = confirmPaymentResponse.as(PaymentResponseMain.class);
			ValidationUtils.validateStatusCode(confirmPaymentResponse, 200);
			ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
		} else {

			System.out.println("KYC Status: " + kycStatus + ". Payment cannot be processed.");
			Assert.fail("Payment cannot proceed because KYC is not completed.");

		}
	}

	public static void verifyDigiLockerRegistrationResponse(Response response) {

		String isRegistered = response.jsonPath().getString("registered");
		if ("true".equalsIgnoreCase(isRegistered)) {
			System.out.println("User is registered on Digilocker.");
		} else {
			System.out.println("User is NOT registered on Digilocker.");
			Assert.fail("User is NOT registered on Digilocker");

		}
	}

}
