package com.fintech.data;

public class TransactionData {
    // Private fields representing transaction data
    private String transactionID;
    private double amount;
    private String currency;
    private String transactionType;
    private String status;

    // Default constructor (required for Jackson deserialization)
    public TransactionData() {
    }

    // Parameterized constructor (optional, for convenience)
    public TransactionData(String transactionID, double amount, String currency, String transactionType, String status) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.currency = currency;
        this.transactionType = transactionType;
        this.status = status;
    }

    // Getter and Setter methods for transactionID
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
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

    // Getter and Setter methods for transactionType
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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
        return "TransactionData{" +
                "transactionID='" + transactionID + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}