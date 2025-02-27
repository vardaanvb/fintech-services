package com.fintech.models;

import java.util.List;

public class PaymentResponseDetails {
	
	
	private List<PaymentResponseTimeStamp> timestamps;
	
	public List<PaymentResponseTimeStamp> getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(List<PaymentResponseTimeStamp> timestamps) {
		this.timestamps = timestamps;
	}
	

}
