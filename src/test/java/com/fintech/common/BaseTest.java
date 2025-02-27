package com.fintech.common;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fintech.config.ConfigManager;
import com.fintech.helper.BaseTestHelper;
import com.fintech.mock.WireMockHelper;
import com.github.tomakehurst.wiremock.WireMockServer;

public class BaseTest {
	
	private static final String BASE_URL = ConfigManager.getInstance().getBaseUrl();
	private static final String GOVT_AUTH_URL = ConfigManager.getInstance().getProperty("govtAuthURL");
	
	protected static final String DIGILOCKER_URL = ConfigManager.getInstance().getProperty("digiLockerBaseURL");
	public String govtAuthToken;

    protected WireMockServer wireMockServer;
    protected static final int WIREMOCK_PORT = 8081;
    WireMockHelper helper = new WireMockHelper();

    @BeforeMethod
    public void setUp() {
    	
    	govtAuthToken = BaseTestHelper.getOAuthToken(GOVT_AUTH_URL);
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
