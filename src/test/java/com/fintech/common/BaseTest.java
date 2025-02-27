package com.fintech.common;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fintech.config.ConfigManager;
import com.fintech.tests.WireMockTest2;
import com.github.tomakehurst.wiremock.WireMockServer;

public class BaseTest {

	protected static final String BASE_URL = ConfigManager.getInstance().getBaseUrl();

	protected static final String DIGILOCKER_URL = ConfigManager.getInstance().getProperty("digiLockerBaseURL");

	private static final String GOVT_PRE_AUTH_URL = ConfigManager.getInstance().getProperty("govtAuthURL");
	private static final String INT_PRE_AUTH_URL = ConfigManager.getInstance().getProperty("intAuthURL");

	public String govtPreAuthToken;
	public String intPreAuthToken;
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
	//	govtPreAuthToken = BaseTestHelper.getGovtPreOAuthToken(GOVT_PRE_AUTH_URL);
	//	intPreAuthToken = BaseTestHelper.getInternalPreOAuthToken(INT_PRE_AUTH_URL);
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
