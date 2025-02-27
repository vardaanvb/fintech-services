package com.fintech.config;

public class JSONToExcelMapper {

    // Enum for column mappings
    public enum Payments {
        AMOUNT("amount"),
        CURRENCY("currency"),
        PAYMENT_METHOD("paymentMethod");

        private final String jsonKey;

        Payments(String jsonKey) {
            this.jsonKey = jsonKey;
        }

        public String getJsonKey() {
            return jsonKey;
        }
    }

  
}
