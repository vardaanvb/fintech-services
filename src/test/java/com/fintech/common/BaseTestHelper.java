package com.fintech.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.fintech.auth.OAuthAuthProvider;
import com.github.tomakehurst.wiremock.WireMockServer;
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

public class BaseTestHelper {

	protected static String getGovtPreOAuthToken(String govtAuthURL) {
		Response response = RestAssured.given().contentType("application/json").body(
				"{\"client_id\": \"govtclientId\", \"client_secret\": \"govtclient_secret\", \"grant_type\": \"govtgrant_type\"}")
				.post(govtAuthURL);

		return response.jsonPath().getString("access_token");
	}

	protected static String getInternalPreOAuthToken(String govtAuthURL) {
		
		OAuthAuthProvider auth = new OAuthAuthProvider();
		Response response = RestAssured.given().contentType("application/json").body(
				"{\"client_id\": \"intclientId\", \"client_secret\": \"intclient_secret\", \"grant_type\": \"intgrant_type\", \"scope\": \"trust\"}")
				.post(govtAuthURL);

		return response.jsonPath().getString("access_token");
	}

	protected void createTransactionsStub() {

		try {
			String responseBody;
			
				responseBody = readFileAsString("Resources/__files/transactions.json");
		

			stubFor(get(urlEqualTo("/v1/transactions/1")).willReturn(
					aResponse().withStatus(200).withBody(responseBody).withHeader("Content-Type", "application/json")));
			System.out.println("Stub created successfully.");
		} catch (Exception e) {
			System.err.println("Error creating WireMock stub: " + e.getMessage());
			throw new RuntimeException("Failed to create stub", e);
		}
	}

	private  String readFileAsString(String filePath) {
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
			throw new RuntimeException("Failed to read file: " + filePath, e);
		}
	}
}
