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

public class WireMockTestNG {
    
    private WireMockServer wireMockServer;

    @BeforeMethod
    public void setUp() {
        // Start WireMock server on port 8081
        wireMockServer = new WireMockServer(8081);
        wireMockServer.start();
        configureFor("localhost", 8081);
        createTransactionsStub();
    }


    
    public void createTransactionsStub() {
        stubFor(get(urlEqualTo("/v1/transactions"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody(readFileAsString("Resources/__files/transactions.json"))  // ✅ Load JSON manually
                .withHeader("Content-Type", "application/json")
            ));
    }

    // Helper method to read file contents
    private String readFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }


    @Test
    public void testWireMockStub() {
        RestAssured.baseURI = "http://localhost:8081";

        Response response = RestAssured.given().log().all()
                .when()
                .get("/v1/transactions");

        int statusCode = response.statusCode();
        System.out.println("Response Code: " + statusCode);
        String contentType = response.getContentType();
        System.out.println("Content-Type: " + contentType);
        response.prettyPrint();

        // Assertion (optional)
        assert statusCode == 200 : "Expected 200 but got " + statusCode;
    }

    @AfterMethod
    public void tearDown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
