package com.fintech.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseTestHelper {
	
	public static String getOAuthToken(String govtAuthURL) {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"client_id\": \"your_client_id\", \"client_secret\": \"your_client_secret\", \"grant_type\": \"client_credentials\"}")
                .post(govtAuthURL);

        return response.jsonPath().getString("access_token");
    }

}
