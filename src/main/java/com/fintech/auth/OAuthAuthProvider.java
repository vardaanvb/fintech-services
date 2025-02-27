package com.fintech.auth;

import com.fintech.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAuthAuthProvider implements AuthProvider {

    @Override
    public String getAuthToken() {
        String oAuthBaseURL = ConfigManager.getInstance().getProperty("OAuthBaseURL");
        String clientId = ConfigManager.getInstance().getProperty("OauthClientID");
        String clientSecret = ConfigManager.getInstance().getProperty("OauthClientSecret");
        String grantType = ConfigManager.getInstance().getProperty("OauthGrantType");
        String scope = ConfigManager.getInstance().getProperty("OauthScope");

        Response response = RestAssured.given()
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .formParam("grant_type", grantType)
                .formParam("scope", scope)
                .post(oAuthBaseURL);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to retrieve OAuth token");
        }

        JsonPath jp = response.jsonPath();
        return "Bearer " + jp.getString("access_token");
    }
}
