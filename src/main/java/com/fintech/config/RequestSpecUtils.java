package com.fintech.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestSpecUtils {

	public static RequestSpecification makePaymentRequestSpecs(String baseUri) {
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();

		
		RequestSpecification reqSpec = specBuilder.setBaseUri(baseUri).addQueryParam("key", "dummy-key")
				.setContentType(ContentType.JSON).build();

		specBuilder.setBaseUri(baseUri);

	
		

		return reqSpec;
	}

}
