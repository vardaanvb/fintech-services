package com.fintech.helper;

import com.fintech.data.TestDataReader;
import com.fintech.endpoints.ApplicationEndpoints;
import com.fintech.helper.ApiRequestHelper;
import com.fintech.helper.ValidationUtils;
import com.fintech.models.PaymentRequestMain;
import com.fintech.models.PaymentResponseMain;

import io.restassured.response.Response;

public class ThirdPartyKYCHelper {
	
	
	public static void KYCVerficationAndConfirmPayment(Response response) {
		
		  String kycStatus = response.jsonPath().getString("status");  
		 if ("completed".equalsIgnoreCase(kycStatus)) {
		      
	            PaymentRequestMain paymentRequest = TestDataReader.readJsonData("src/test/resources/testdata/payment_data.json", PaymentRequestMain.class);
	            Response confirmPaymentResponse = ApiRequestHelper.makeApiRequest("bearer", "POST", ApplicationEndpoints.CONFIRM_PAYMENT, paymentRequest);

	            PaymentResponseMain paymentResponse = confirmPaymentResponse.as(PaymentResponseMain.class);
	            ValidationUtils.validateStatusCode(confirmPaymentResponse, 200);
	            ValidationUtils.validateResponseBody(paymentResponse.getStatus(), "Success");
	        } else {
	        
	            System.out.println("KYC Status: " + kycStatus + ". Payment cannot be processed.");
	            assert false : "Payment cannot proceed because KYC is not completed.";
	        }
		
	}

}
