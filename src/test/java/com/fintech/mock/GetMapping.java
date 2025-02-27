package com.fintech.mock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetMapping {
	
	@BeforeMethod
	public void setUp(){
		getTransactionsStub();
	}
	
	public void getTransactionsStub(){
		
		stubFor(get(urlEqualTo("/v1/transactions"))

				.willReturn(aResponse().withStatus(200)
						.withBodyFile("transactions.json")
						.withHeader("Content-Type", "application/json")
						));
		
	}
	
	@Test
	public void test() {
		
		
		RestAssured.baseURI="http://localhost:8081";
		
		Response response = RestAssured.given().log().all()
		.when()
		.get("/v1/transactions");
		
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		String contentType = response.getContentType();
		System.out.println(contentType);
		response.prettyPrint();
	}

}
