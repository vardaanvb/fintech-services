package com.fintech.models;

public class TransactionResponse {
    // Fields
    private String transactionID;
    private double amount;
    private String currency;
    private String transactionType;
    private String status;
    private String message;

    // Private constructor (only accessible via Builder)
    private TransactionResponse() {
    }

    // Getters (no setters to make the object immutable)
    public String getTransactionID() {
        return transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Builder Class
    public static class Builder {
        private final TransactionResponse transactionResponse;

        public Builder() {
            this.transactionResponse = new TransactionResponse();
        }

        // Setter methods for each field (returning 'this' for method chaining)
        public Builder setTransactionID(String transactionID) {
            transactionResponse.transactionID = transactionID;
            return this;
        }

        public Builder setAmount(double amount) {
            transactionResponse.amount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            transactionResponse.currency = currency;
            return this;
        }

        public Builder setTransactionType(String transactionType) {
            transactionResponse.transactionType = transactionType;
            return this;
        }

        public Builder setStatus(String status) {
            transactionResponse.status = status;
            return this;
        }

        public Builder setMessage(String message) {
            transactionResponse.message = message;
            return this;
        }

        // Build method to create the final object
        public TransactionResponse build() {
            // Validate required fields (optional)
            if (transactionResponse.transactionID == null || transactionResponse.status == null) {
                throw new IllegalStateException("Required fields (transactionID, status) are not set.");
            }
            return transactionResponse;
        }
    }
}