package com.fintech.models;

import java.util.List;

public class PaymentRequestMain {
	

	
	private double amount;
	private String currency;
	private String paymentMethod;
	private List<String> orderDetails;
	private PaymentRequestPayerDetails payerDetails;
	
	
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
	public List<String> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<String> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public PaymentRequestPayerDetails getPayerDetails() {
		return payerDetails;
	}
	public void setPayerDetails(PaymentRequestPayerDetails payerDetails) {
		this.payerDetails = payerDetails;
	}
	


}
