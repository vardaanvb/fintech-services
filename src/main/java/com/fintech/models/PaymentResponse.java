package com.fintech.models;

public class PaymentResponse {
    // Fields
    private String paymentID;
    private double amount;
    private String currency;
    private String paymentMethod;
    private String status;
    private String message;

    // Private constructor (only accessible via Builder)
    private PaymentResponse() {
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

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Builder Class
    public static class Builder {
        private final PaymentResponse paymentResponse;

        public Builder() {
            this.paymentResponse = new PaymentResponse();
        }

        // Setter methods for each field (returning 'this' for method chaining)
        public Builder setPaymentID(String paymentID) {
            paymentResponse.paymentID = paymentID;
            return this;
        }

        public Builder setAmount(double amount) {
            paymentResponse.amount = amount;
            return this;
        }

        public Builder setCurrency(String currency) {
            paymentResponse.currency = currency;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            paymentResponse.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setStatus(String status) {
            paymentResponse.status = status;
            return this;
        }

        public Builder setMessage(String message) {
            paymentResponse.message = message;
            return this;
        }

        // Build method to create the final object
        public PaymentResponse build() {
            // Validate required fields (optional)
            if (paymentResponse.paymentID == null || paymentResponse.status == null) {
                throw new IllegalStateException("Required fields (paymentID, status) are not set.");
            }
            return paymentResponse;
        }
    }
}