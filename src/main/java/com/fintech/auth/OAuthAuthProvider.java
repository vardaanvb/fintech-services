package com.fintech.auth;

import com.fintech.config.APIParams;
import com.fintech.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAuthAuthProvider implements AuthProvider {

    

	@Override
    public String getAuthToken() {
    	
 
		String oAuthBaseURL = ConfigManager.getInstance().getProperty("OAuthBaseURL");
  
        Response response = RestAssured.given().pathParams(APIParams.oAuthQueryParams())
                .post(oAuthBaseURL);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to retrieve OAuth token");
        }

        JsonPath jp = response.jsonPath();
        return "Bearer " + jp.getString("access_token");
    }
  

}
