package com.fintech.data;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapToJSON {

    private static HashMapToJSON instance;
    ExcelReader ex = new ExcelReader();
    // Private constructor to prevent instantiation from outside
    private HashMapToJSON() { }

    // Public static method to provide the single instance
    public static HashMapToJSON getInstance() {
        if (instance == null) {
            synchronized (HashMapToJSON.class) {
                if (instance == null) {
                    instance = new HashMapToJSON();
                }
            }
        }
        return instance;
    }

    // Method to read payment data and return JSON HashMap
    public HashMap<String, Object> paymentDataFromExcel(String testcaseName, String sheetName) {
        
        ArrayList<String> data = ex.getData(testcaseName, sheetName);

        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("amount", data.get(0));
        jsonMap.put("current", data.get(1));
        jsonMap.put("paymentMethod", data.get(2));

        return jsonMap;
    }
}
