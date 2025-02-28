package com.fintech.config;

import java.util.HashMap;

import com.fintech.utils.ConfigManager;

public class APIParams {

	public static HashMap<String, String> oAuthQueryParams() {
		String oAuthBaseURL = ConfigManager.getInstance().getProperty("OAuthBaseURL");
		String OauthClientID = ConfigManager.getInstance().getProperty("OauthClientID");
		String clientSecret = ConfigManager.getInstance().getProperty("OauthClientSecret");
		String grantType = ConfigManager.getInstance().getProperty("OauthGrantType");
		String scope = ConfigManager.getInstance().getProperty("OauthScope");

		HashMap<String, String> OAuthParamsMap = new HashMap<String, String>();
		
		OAuthParamsMap.put("client_id", OauthClientID);
		OAuthParamsMap.put("client_secret", clientSecret);
		OAuthParamsMap.put("grant_type", grantType);
		OAuthParamsMap.put("scope", scope);
		
		return OAuthParamsMap;
		
	}
	
	public static HashMap<String, String> thirdPartyParams() {

		
		HashMap<String, String> thirdPartyParamsMap = new HashMap<String, String>();
		thirdPartyParamsMap.put("content-type", "application/json");
		thirdPartyParamsMap.put("platform", "web");
			
		return thirdPartyParamsMap;
	}

}
