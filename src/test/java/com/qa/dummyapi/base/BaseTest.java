package com.qa.dummyapi.base;

import com.qa.dummyapi.config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class BaseTest {
    protected static RequestSpecification reqSpec;

    @BeforeClass
    public static void globalSetup() {
        // Read baseUrl and appId from config, system property, or environment variable
        String baseUrl = Config.get("baseUrl", "https://dummyapi.io/data/v1");
        String appId  = Config.get("appId", "REPLACE_WITH_YOUR_APP_ID");

        // Remove trailing slash if present
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        RestAssured.baseURI = baseUrl;

        // Build a common request specification
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("app-id", appId)
                .setContentType("application/json")
                .build();
    }
}
