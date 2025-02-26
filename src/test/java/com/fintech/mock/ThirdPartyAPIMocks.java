package com.fintech.mock;

import com.github.tomakehurst.wiremock.client.WireMock;

public class ThirdPartyAPIMocks {
    
    public static void setupKYCMock(String aadhaarNumber, String scenario) {
        if (scenario == null || scenario.trim().isEmpty()) {
            throw new IllegalArgumentException("Scenario cannot be null or empty");
        }

        switch (scenario.toLowerCase()) {
            case "success":
                validateAadhaar(aadhaarNumber);
                WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/kyc"))
                        .withQueryParam("aadhaar", WireMock.equalTo(aadhaarNumber))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"status\": \"COMPLETE\"}")));
                break;
            
            case "server_error":
                WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/kyc"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(500)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"error\": \"Internal Server Error\"}")));
                break;
            
            case "timeout":
                WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/kyc"))
                        .willReturn(WireMock.aResponse()
                                .withFixedDelay(3000)
                                .withStatus(504)
                                .withBody("{\"error\": \"Gateway Timeout\"}")));
                break;

            default:
                throw new IllegalArgumentException("Unsupported scenario: " + scenario);
        }
    }

    private static void validateAadhaar(String aadhaarNumber) {
        if (aadhaarNumber == null || aadhaarNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Aadhaar number must be provided for success scenario");
        }
       
    }
}