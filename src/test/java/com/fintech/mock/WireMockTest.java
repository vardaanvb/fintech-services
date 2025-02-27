package com.fintech.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WireMockTest {
    
    private WireMockServer wireMockServer;
    protected static final int WIREMOCK_PORT = 8081;

    @BeforeMethod
    public void setUp() {
        try {
            // Start WireMock server on port 8081
            wireMockServer = new WireMockServer(WIREMOCK_PORT);
            wireMockServer.start();
            configureFor("localhost", WIREMOCK_PORT);
            createTransactionsStub();
            System.out.println("WireMock server started successfully.");
        } catch (Exception e) {
            System.err.println("Error starting WireMock server: " + e.getMessage());
            throw new RuntimeException("Failed to start WireMock server", e);
        }
    }

    public void createTransactionsStub() {
        try {
            String responseBody = readFileAsString("Resources/__files/transactions.json");

            stubFor(get(urlEqualTo("/v1/transactions/1"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withBody(responseBody) 
                    .withHeader("Content-Type", "application/json")
                ));
            System.out.println("Stub created successfully.");
        } catch (Exception e) {
            System.err.println("Error creating WireMock stub: " + e.getMessage());
            throw new RuntimeException("Failed to create stub", e);
        }
    }

    // Helper method to read file contents with error handling
    private String readFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    @Test
    public void testWireMockStub() {
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

    @AfterMethod
    public void tearDown() {
        if (wireMockServer != null) {
            try {
                wireMockServer.stop();
                System.out.println("WireMock server stopped successfully.");
            } catch (Exception e) {
                System.err.println("Error stopping WireMock server: " + e.getMessage());
            }
        }
    }
}
