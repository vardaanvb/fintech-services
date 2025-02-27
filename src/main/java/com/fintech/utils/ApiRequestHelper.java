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
	private static final String GOVT_AUTH_URL = "https://sample-govt-api.com/oauth/token";
	private static final String DIGILOCKER_URL = "https://digilocker.gov/api/checkUser";

	public static String getOAuthToken() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"client_id\": \"your_client_id\", \"client_secret\": \"your_client_secret\", \"grant_type\": \"client_credentials\"}")
                .post(GOVT_AUTH_URL);

        return response.jsonPath().getString("access_token");
    }

	

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
		   String authToken = getOAuthToken();
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
	
	public static Response verifyDigilockerUser(String userId) {
        return RestAssured.given()
                .header("Authorization", "Bearer your_static_token")
                .queryParam("userId", userId)
                .when()
                .get(DIGILOCKER_URL);
    }

}
