package com.fintech.models;

public class TransactionRequest {
    // Fields
    private String transactionID;
    private double amount;
    private String currency;
    private String transactionType;
    private String sourceAccount;
    private String destinationAccount;

    // Private constructor (only accessible via Builder)
    private TransactionRequest() {
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

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    // Builder Class
    public static class Builder {
        private final TransactionRequest transactionRequest;

        public Builder() {
            this.transactionRequest = new TransactionRequest();
        }

        // Setter methods for each field (returning 'this' for method chaining)
        public Builder setTransactionID(String transactionID) {
            transactionRequest.transactionID = transactionID;
            return this;
        }

        public Builder setAmount(double amount) {
            transactionRequest.amount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            transactionRequest.currency = currency;
            return this;
        }

        public Builder setTransactionType(String transactionType) {
            transactionRequest.transactionType = transactionType;
            return this;
        }

        public Builder setSourceAccount(String sourceAccount) {
            transactionRequest.sourceAccount = sourceAccount;
            return this;
        }

        public Builder setDestinationAccount(String destinationAccount) {
            transactionRequest.destinationAccount = destinationAccount;
            return this;
        }

        // Build method to create the final object
        public TransactionRequest build() {
            // Validate required fields (optional)
            if (transactionRequest.transactionID == null || transactionRequest.amount <= 0) {
                throw new IllegalStateException("Required fields (transactionID, amount) are not set.");
            }
            return transactionRequest;
        }
    }
}