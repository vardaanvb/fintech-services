package com.fintech.models;

public class PaymentRequest {
    private String paymentID;
    private double amount;
    private String currency;
    private String paymentMethod;
    private String sourceAccount;
    private String destinationAccount;

    // Default constructor (required for Jackson)
    public PaymentRequest() {}

    // Parameterized constructor (optional, for convenience)
    public PaymentRequest(String paymentID, double amount, String currency, String paymentMethod, String sourceAccount, String destinationAccount) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    // Getters and Setters
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

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}