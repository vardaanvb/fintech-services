package com.fintech.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class TestDataReader {

    // Method to read JSON data from a file using Jackson
    public static <T> T readJsonData(String filePath, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON file and map it to the specified class
            return objectMapper.readValue(new File(filePath), valueType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}