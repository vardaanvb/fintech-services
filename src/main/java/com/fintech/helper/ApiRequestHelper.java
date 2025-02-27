package com.fintech.helper;

import java.io.IOException;

import com.fintech.auth.AuthFactory;
import com.fintech.auth.AuthProvider;
import com.fintech.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRequestHelper {





	

	public static Response makeApiRequest(String baseURL, String authType, String httpMethod, String endpoint, Object requestBody) {
		// Get authentication provider dynamically
		AuthProvider authProvider = AuthFactory.getAuthProvider(authType);
		String authToken = authProvider.getAuthToken();
		
		
		// Create the request specification
		RequestSpecification requestSpec = RestAssured.given().header("Authorization", authToken).baseUri(baseURL)
				.when();

		// Handle different HTTP methods dynamically
		switch (httpMethod.toUpperCase()) {
		case "POST":
			return requestSpec.body(requestBody).post(endpoint);
		case "GET":
			return requestSpec.get(endpoint);
		case "PUT":
			return requestSpec.body(requestBody).put(endpoint);
		case "DELETE":
			return requestSpec.delete(endpoint);
		default:
			throw new IllegalArgumentException("Unsupported HTTP method: " + httpMethod);
		}
	}


	
	public static Response makeKYCRequest(String authToken, String thirdPartyBaseUrl, String endpoint, String queryParams) {

		String fullUrl = thirdPartyBaseUrl + endpoint + "?" + queryParams;

		try {

			return RestAssured.given()
					 .header("Authorization", "Bearer " + authToken)
					 .baseUri(thirdPartyBaseUrl).when().get(fullUrl);

		} catch (Exception e) {

			System.out.println("An error occurred while making the third-party API request.");
			throw new RuntimeException("Error occurred while interacting with the third-party API.", e);
		}
	}
	
	public static Response verifyDigilockerUser(String digiLockerURL, String digiLockerAuthToken, String userId) {
        return RestAssured.given()
                .header("Authorization", digiLockerAuthToken)
                .queryParam("userId", userId)
                .when()
                .get(digiLockerURL);
    }

}
