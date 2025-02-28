package com.fintech.auth;

import com.fintech.utils.ConfigManager;

public class BearerAuthProvider implements AuthProvider {

    @Override
    public String getAuthToken() {
        return "Bearer " + ConfigManager.getInstance().getProperty("authToken");
    }
}
