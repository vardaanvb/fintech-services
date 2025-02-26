package com.fintech.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ValidationUtils {
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    public static void validateResponseBody(String actualValue, String expectedValue) {
        Assert.assertEquals(actualValue, expectedValue);
    }
}