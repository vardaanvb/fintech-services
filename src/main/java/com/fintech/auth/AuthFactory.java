package com.fintech.auth;

public class AuthFactory {
    public static AuthProvider getAuthProvider(String authType) {
    	
        if ("oauth".equalsIgnoreCase(authType)) {
            return new OAuthAuthProvider();
        } else if("jwt".equalsIgnoreCase(authType)){
        	
        	return null;
        	
        }else {
            return new BearerAuthProvider();
        }
    }
}
