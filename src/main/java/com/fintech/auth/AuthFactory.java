package com.fintech.auth;

public class AuthFactory {
    public static AuthProvider getAuthProvider(String authType) {
        if ("oauth".equalsIgnoreCase(authType)) {
            return new OAuthAuthProvider();
        } else {
            return new BearerAuthProvider();
        }
    }
}
