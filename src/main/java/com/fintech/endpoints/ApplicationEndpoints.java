package com.fintech.endpoints;

public class ApplicationEndpoints {
	
	// Payment Processing
	public static final String CREATE_PAYMENT = "/payments";
	public static final String CONFIRM_PAYMENT = "/payments/{paymentId}/confirm";
	public static final String GET_PAYMENT_STATUS = "/payments/{paymentId}";

	// FundsTransfer History
	public static final String CREATE_FUNDS_TRANSFER = "/fundstransfer";
	public static final String GET_TRANSACTION_DETAILS = "/transactions/{transactionId}";
	
	
	
	
	
}
