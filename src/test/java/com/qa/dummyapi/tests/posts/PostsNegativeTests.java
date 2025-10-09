package com.qa.dummyapi.tests.posts;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import com.qa.dummyapi.config.Config;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsNegativeTests extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Test
    public void getPost_nonExisting_shouldReturn404() {
        // Non-existing postId should return 404 or 400
        client.getPostById("000000000000000000000000")
                .then()
                .statusCode(anyOf(equalTo(404), equalTo(400)));
    }

    @Test
    public void getPost_invalidFormat_shouldReturn4xx() {
        // Invalid format postId should return 4xx
        client.getPostById("!!bad-id!!")
                .then()
                .statusCode(greaterThanOrEqualTo(400));
    }

    @Test
    public void getPosts_badQueryParam_shouldReturn4xxOrIgnore() {
        // Negative or non-number limit
        given()
                .baseUri(Config.get("baseUrl", "https://dummyapi.io/data/v1"))
                .header("app-id", Config.get("appId", "REPLACE_WITH_YOUR_APP_ID"))
                .when().get("/post?limit=-1")
                .then()
                .statusCode(greaterThanOrEqualTo(400));
    }

    @Test
    public void getPost_missingAppId_shouldReturnAuthError() {
        // Missing app-id header should return 401/403 or 4xx
        given()
                .baseUri(Config.get("baseUrl", "https://dummyapi.io/data/v1"))
                .when().get("/post/{id}", "60d21b8667d0d8992e610c85")
                .then()
                .statusCode(anyOf(equalTo(401), equalTo(403), greaterThanOrEqualTo(400)));
    }
}
