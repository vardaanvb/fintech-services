package com.fintech.auth;

import com.fintech.config.ConfigManager;

public class BearerAuthProvider implements AuthProvider {

    @Override
    public String getAuthToken() {
        return "Bearer " + ConfigManager.getInstance().getProperty("authToken");
    }
}
