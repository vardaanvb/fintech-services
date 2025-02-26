package com.web.fintech;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// given (Give input) when (Submit API) then (Response Validation) 
		
		RestAssured.baseURI = "https:rahulsheetyacademy.com";
		given().log().all().queryParam("key", "value")
		.header("Content-Type", "application/json")
		.body("")
		.when().post("___resource___")
		.then().log().all().assertThat(
				).statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "___Header value)");
		
	}

}
