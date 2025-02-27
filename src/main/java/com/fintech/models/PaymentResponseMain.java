package com.fintech.models;

public class PaymentResponseMain {
	
	

	
	private String paymentID;
    private double amount;
    private String currency;
    private String paymentMethod;
    private String status;
    private String message;
    private PaymentResponseDetails details;
    
    public PaymentResponseDetails getDetails() {
		return details;
	}
	public void setDetails(PaymentResponseDetails details) {
		this.details = details;
	}
	
	
	
	
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
