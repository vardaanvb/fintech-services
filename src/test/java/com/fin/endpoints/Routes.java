package com.fin.endpoints;

public class Routes {

	public static final String  base_url = "https://api.fintech.com";
	
	// Dashboard Module
	
	public class Endpoints {
	    // Payment Processing
	    public static final String CREATE_PAYMENT = "/payments";
	    public static final String CONFIRM_PAYMENT = "/payments/{paymentId}/confirm";
	    public static final String GET_PAYMENT_STATUS = "/payments/{paymentId}";

	    // Loan Application
	    public static final String APPLY_FOR_LOAN = "/loans/apply";
	    public static final String GET_LOAN_STATUS = "/loans/{loanId}/status";

	    // Account Management
	    public static final String CREATE_ACCOUNT = "/accounts";
	    public static final String GET_ACCOUNT_DETAILS = "/accounts/{accountId}";
	    public static final String UPDATE_ACCOUNT_BALANCE = "/accounts/{accountId}/balance";

	    // Transaction History
	    public static final String GET_TRANSACTIONS = "/transactions";
	    public static final String GET_TRANSACTION_DETAILS = "/transactions/{transactionId}";

	    // Fraud Detection
	    public static final String REPORT_FRAUD = "/fraud/report";
	    public static final String GET_FRAUD_ALERTS = "/fraud/alerts";
	    public static final String RESOLVE_FRAUD_ALERT = "/fraud/alerts/{alertId}/resolve";
	}	
}
