package com.fintech.utils;

public class JSONToExcelMapper {

    // Enum for column mappings
    public enum FundsTransfer {
        AMOUNT("amount"),
        CURRENCY("currency"),
        FROM_ACCOUNT("FromAccount"), 
        TO_ACCOUNT("ToAccount");

        private final String jsonKey;

        FundsTransfer(String jsonKey) {
            this.jsonKey = jsonKey;
        }

        public String getJsonKey() {
            return jsonKey;
        }
    }

    
}
