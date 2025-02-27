package com.fintech.mock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class CreateMapping {
	
	
	public void createStub() {
		
		stubFor(get(urlEqualTo("/api/services"))
				.willReturn(aResponse().withStatus(200)
				.withBody("This is a sample response")
				.withHeader("Content-Type", "application/json")
				));
			
		
	}
	
	
	@Test
	public void verifyStub() {
		createStub();
		
		RestAssured.baseURI="http://localhost:8081";
		
		Response response = RestAssured.given().log().all()
		.when()
		.get("/api/services");
		
		int statusCode = response.statusCode();
		System.out.println(statusCode);
		String contentType = response.getContentType();
		System.out.println(contentType);
		response.prettyPrint();
	}
	
	

}
