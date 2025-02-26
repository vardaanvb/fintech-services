package com.fintech.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	private static final String CONFIG_FILE = "./config.properties";
	private static ConfigManager instance;
	private Properties properties;

	// Private constructor to prevent instantiation from outside
	private ConfigManager() {
		properties = new Properties();
		loadProperties();
	}

	// Public static method to get the singleton instance
	public static ConfigManager getInstance() {
		if (instance == null) {

			instance = new ConfigManager();
		}

		return instance;

	}

	// Method to load properties from the config file

	private void loadProperties() {
		try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
			properties.load(input);
			System.out.println("Configuration properties loaded successfully");
		} catch (IOException ex) {
			System.err.println("Error loading configuration properties: " + ex.getMessage());
			throw new RuntimeException("Failed to load configuration properties", ex);
		}
	}

	public String getEnvironment() {
		return properties.getProperty("env");
	}

	public String getBaseUrl() {
		String env = getEnvironment();
		return getProperty("baseUrl." + env);
	}

	// Method to get a property value by key
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}