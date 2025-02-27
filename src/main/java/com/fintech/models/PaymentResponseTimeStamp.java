package com.fintech.models;

public class PaymentResponseTimeStamp {
	
	private String event;
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	private String timestamp;

}
