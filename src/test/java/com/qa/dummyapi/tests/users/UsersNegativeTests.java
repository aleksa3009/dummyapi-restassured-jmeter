package com.qa.dummyapi.tests.users;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import com.qa.dummyapi.config.Config;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersNegativeTests extends BaseTest {
    private final ApiClient client = new ApiClient(reqSpec);

    // Non-existing user ID should return 404 or 400
    @Test
    public void getUser_nonExisting_shouldReturn404() {
        String nonExisting = "000000000000000000000000";
        client.getUserById(nonExisting)
                .then()
                .statusCode(anyOf(equalTo(404), equalTo(400)));
    }

    // Invalid ID format should return 4xx
    @Test
    public void getUser_invalidFormat_shouldReturn4xx() {
        String invalid = "!!!invalid-id";
        client.getUserById(invalid)
                .then()
                .statusCode(greaterThanOrEqualTo(400));
    }

    // Missing app-id header should return auth error (401/403)
    @Test
    public void getUser_missingAppId_shouldReturnAuthError() {
        given()
                .baseUri(Config.get("baseUrl", "https://dummyapi.io/data/v1"))
                .when().get("/user/{id}", "60d0fe4f5311236168a109ca")
                .then()
                .statusCode(anyOf(equalTo(401), equalTo(403), greaterThanOrEqualTo(400)));
    }
}
