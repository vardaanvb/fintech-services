package com.fintech.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WireMockHelper {
	
	
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
	 
	 public String readFileAsString(String filePath) {
	        try {
	            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
	            throw new RuntimeException("Failed to read file: " + filePath, e);
	        }
	    }

}
