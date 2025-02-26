package com.fintech.models;

public class PaymentRequest {
    // Fields
    private String paymentID;
    private double amount;
    private String currency;
    private String paymentMethod;
    private String sourceAccount;
    private String destinationAccount;

    // Private constructor (only accessible via Builder)
    private PaymentRequest() {
    }

    // Getters (no setters to make the object immutable)
    public String getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    // Builder Class
    public static class Builder {
        private final PaymentRequest paymentRequest;

        public Builder() {
            this.paymentRequest = new PaymentRequest();
        }

        // Setter methods for each field (returning 'this' for method chaining)
        public Builder setPaymentID(String paymentID) {
            paymentRequest.paymentID = paymentID;
            return this;
        }

        public Builder setAmount(double amount) {
            paymentRequest.amount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            paymentRequest.currency = currency;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            paymentRequest.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setSourceAccount(String sourceAccount) {
            paymentRequest.sourceAccount = sourceAccount;
            return this;
        }

        public Builder setDestinationAccount(String destinationAccount) {
            paymentRequest.destinationAccount = destinationAccount;
            return this;
        }

        // Build method to create the final object
        public PaymentRequest build() {
            // Validate required fields (optional)
            if (paymentRequest.paymentID == null || paymentRequest.amount <= 0) {
                throw new IllegalStateException("Required fields (paymentID, amount) are not set.");
            }
            return paymentRequest;
        }
    }
}