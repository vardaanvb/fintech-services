package com.fintech.common;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.fintech.utils.ConfigManager;
import com.github.tomakehurst.wiremock.WireMockServer;

public class BaseTest {

	protected static final String INT_BASE_URL = ConfigManager.getInstance().getBaseUrl();
	protected static final String DIGILOCKER_URL = ConfigManager.getInstance().getProperty("digiLockerBaseURL");

	protected static final String GOVT_PRE_AUTH_URL = ConfigManager.getInstance().getProperty("govtAuthURL");
	protected static final String INT_PRE_AUTH_URL = ConfigManager.getInstance().getProperty("intAuthURL");
	
	protected static final String DIGILOCKER_AUTH_TOKEN = ConfigManager.getInstance().getProperty("digiLockerAuthToken");
	
	
	protected WireMockServer wireMockServer;
	protected static final int WIREMOCK_PORT = 8081;
	BaseTestHelper bth = new BaseTestHelper();
	@BeforeMethod
	public void setUp() {
		try {
			wireMockServer = new WireMockServer(WIREMOCK_PORT);
			wireMockServer.start();
			configureFor("localhost", WIREMOCK_PORT);
			bth.createTransactionsStub();
			System.out.println("WireMock server started successfully.");
		} catch (Exception e) {
			System.err.println("Error starting WireMock server: " + e.getMessage());
			throw new RuntimeException("Failed to start WireMock server", e);
		}
	}

	@AfterMethod
	public void tearDown() {
		if (wireMockServer != null) {
			try {
				wireMockServer.stop();
				System.out.println("WireMock server stopped successfully.");
			} catch (Exception e) {
				System.err.println("Error stopping WireMock server: " + e.getMessage());
			}
		}
	}
}
