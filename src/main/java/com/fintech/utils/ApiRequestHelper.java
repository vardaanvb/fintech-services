package com.fintech.utils;

import java.io.IOException;

import com.fintech.auth.AuthFactory;
import com.fintech.auth.AuthProvider;
import com.fintech.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRequestHelper {

	private static final String BASE_URL = ConfigManager.getInstance().getBaseUrl();

	public static Response makeApiRequest(String authType, String httpMethod, String endpoint, Object requestBody) {
		// Get authentication provider dynamically
		AuthProvider authProvider = AuthFactory.getAuthProvider(authType);
		String authToken = authProvider.getAuthToken();

		// Create the request specification
		RequestSpecification requestSpec = RestAssured.given().header("Authorization", authToken).baseUri(BASE_URL)
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

	public static Response makeThirdPartyApiRequest(String thirdPartyBaseUrl, String endpoint, String queryParams) {
		String fullUrl = thirdPartyBaseUrl + endpoint + "?" + queryParams;

		try {

			return RestAssured.given().baseUri(thirdPartyBaseUrl).when().get(fullUrl);

		} catch (Exception e) {

			System.out.println("An error occurred while making the third-party API request.");
			throw new RuntimeException("Error occurred while interacting with the third-party API.", e);
		}
	}

}
