package com.fintech.payload;

public class Payload {
	
	public static String makePayment() {
		
		
		String payload = "{\r\n"
				+ "  \"amount\": 100.50,\r\n"
				+ "  \"currency\": \"USD\",\r\n"
				+ "  \"paymentMethod\": \"CREDIT_CARD\",\r\n"
				+ "  \"orderDetails\": [\r\n"
				+ "    \"Item 1\",\r\n"
				+ "    \"Item 2\",\r\n"
				+ "    \"Item 3\"\r\n"
				+ "  ],\r\n"
				+ "  \"payerDetails\": {\r\n"
				+ "    \"name\": \"John Doe\",\r\n"
				+ "    \"email\": \"john.doe@example.com\",\r\n"
				+ "    \"phone\": \"+1234567890\"\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "";
		
		return payload;
	}

}
