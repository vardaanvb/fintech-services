//package com.fintech.mock;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//
//import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
//
//public class WireMockBaseTest {
//
//    protected static final int WIREMOCK_PORT = 8089;
//    protected static WireMockServer wireMockServer;
//
//    @BeforeClass
//    public void setUpWireMockServer() {
//        wireMockServer = new WireMockServer(wireMockConfig().port(WIREMOCK_PORT));
//        wireMockServer.start();
//        WireMock.configureFor("localhost", WIREMOCK_PORT);
//    }
//
//    @AfterClass
//    public void tearDownWireMockServer() {
//        if (wireMockServer != null) {
//            wireMockServer.stop();
//        }
//    }
//}