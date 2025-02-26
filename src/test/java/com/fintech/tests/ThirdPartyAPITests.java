package com.fintech.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import com.fintech.mock.ThirdPartyAPIMocks;
import com.fintech.mock.WireMockBaseTest;
import com.fintech.utils.RetryAnalyzer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ThirdPartyAPITests extends WireMockBaseTest {
    private static final String VALID_AADHAAR = "1234-5678-9012";
    private static final String INVALID_AADHAAR = "0000-0000-0000";

    @Test
    public void testSuccessfulKYC() {
    	ThirdPartyAPIMocks.setupKYCMock(VALID_AADHAAR, "success");
        
        given()
            .queryParam("aadhaar", VALID_AADHAAR)
        .when()
            .get("/kyc")
        .then()
            .statusCode(200)
            .body("status", equalTo("COMPLETE"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testServerError() {
    	ThirdPartyAPIMocks.setupKYCMock(VALID_AADHAAR, "server_error");
        
        given()
            .queryParam("aadhaar", VALID_AADHAAR)
        .when()
            .get("/kyc")
        .then()
            .statusCode(500);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testTimeoutScenario() {
        ThirdPartyAPIMocks.setupKYCMock(VALID_AADHAAR, "timeout");
        
        given()
            .queryParam("aadhaar", VALID_AADHAAR)
        .when()
            .get("/kyc")
        .then()
            .statusCode(200);
    }
}