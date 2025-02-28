package com.fintech.auth;

public interface AuthProvider {
    
	String getAuthToken();

//	String getAuthToken(String oAuthBaseURL, String clientId, String clientSecret, String grantType, String scope);
//
//	String getAuthTokenWithoutScope(String oAuthBaseURL, String clientId, String clientSecret, String grantType);
}
