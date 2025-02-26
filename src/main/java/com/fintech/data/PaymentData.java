package com.fintech.data;

public class PaymentData {
    // Private fields representing payment data
    private String paymentID;
    private double amount;
    private String currency;
    private String paymentMethod;
    private String status;

    // Default constructor (required for Jackson deserialization)
    public PaymentData() {
    }

    // Parameterized constructor (optional, for convenience)
    public PaymentData(String paymentID, double amount, String currency, String paymentMethod, String status) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    // Getter and Setter methods for paymentID
    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    // Getter and Setter methods for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and Setter methods for currency
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // Getter and Setter methods for paymentMethod
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Getter and Setter methods for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString() method for easy debugging
    @Override
    public String toString() {
        return "PaymentData{" +
                "paymentID='" + paymentID + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}