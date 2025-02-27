package com.fintech.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonFileParser {

	// Method to read JSON data from a file using Jackson
	public static <T> T readJsonData(String filePath, Class<T> valueType) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Read JSON file and map it to the specified class
			return objectMapper.readValue(new File(filePath), valueType);
		}

		catch (FileNotFoundException e) {
			System.err.println("Error: Excel file not found at '" + filePath + "'.");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("Error: Unable to read the Excel file.");
			e.printStackTrace();
			return null;
		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}