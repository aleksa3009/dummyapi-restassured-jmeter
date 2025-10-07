package com.qa.dummyapi.tests.users;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class UsersGetByIdTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    // Happy-path: GET /user/{id}
    @Test
    public void getUserById_happyPath() {
        String exampleId = "60d0fe4f5311236168a10a1b";
        client.getUserById(exampleId)
                .then()
                .statusCode(200)
                .body("id", equalTo(exampleId))
                .body("firstName", notNullValue())
                .body("lastName", notNullValue());
    }
}
