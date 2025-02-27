package com.fintech.utils;

import com.fintech.auth.AuthFactory;
import com.fintech.auth.AuthProvider;
import com.fintech.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRequestHelper {

    private static final String BASE_URL = ConfigManager.getInstance().getBaseUrl();

    /**
     * Generic method to handle different API requests dynamically.
     *
     * @param authType   Type of authentication ("bearer" or "oauth")
     * @param httpMethod The HTTP method (POST, GET, PUT, DELETE)
     * @param endpoint   API endpoint
     * @param requestBody Request payload (can be null for GET requests)
     * @return API Response
     */
    public static Response makeApiRequest(String authType, String httpMethod, String endpoint, Object requestBody) {
        // Get authentication provider dynamically
        AuthProvider authProvider = AuthFactory.getAuthProvider(authType);
        String authToken = authProvider.getAuthToken();

        // Create the request specification
        RequestSpecification requestSpec = RestAssured.given().queryParam("key", "dummy-key")
				.queryParam("Content-Type", "application/json")
                .header("Authorization", authToken)
                .baseUri(BASE_URL)
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
}
