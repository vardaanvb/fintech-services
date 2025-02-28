package com.fintech.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import com.fintech.common.BaseTest;

public class WireMockTest extends BaseTest {

    @Test
    public void VerifyTransactionsService() {
        RestAssured.baseURI = "http://localhost:" + WIREMOCK_PORT;

        try {
            Response response = RestAssured.given().log().all()
                .when()
                .get("/v1/transactions/1");

            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);
            String contentType = response.getContentType();
            System.out.println("Content-Type: " + contentType);
            response.prettyPrint();

            assert statusCode == 200 : "Expected 200 but got " + statusCode;
        } catch (Exception e) {
            System.err.println("Error in API request: " + e.getMessage());
            throw new RuntimeException("API request failed", e);
        }
    }
}
