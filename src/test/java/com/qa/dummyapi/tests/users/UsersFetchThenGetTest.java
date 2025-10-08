package com.qa.dummyapi.tests.users;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import com.qa.dummyapi.util.LoggingUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class UsersFetchThenGetTest extends BaseTest {
    private final ApiClient client = new ApiClient(reqSpec);

    @Rule
    public TestName testName = new TestName();

    @Test
    public void fetchList_then_getById_happyPath() throws FileNotFoundException {
        // 1) Fetch a small collection of users
        var listResp = client.getUsers(1);
        listResp.then().statusCode(200);

        // Extract first user ID dynamically
        String id = listResp.jsonPath().getString("data[0].id");
        assertNotNull("Could not extract a user id from collection response", id);

        // 2) Create per-test log stream and RequestSpecification with logging
        File file = new File("reports/api-reports/" + testName.getMethodName() + ".log");
        PrintStream logStream = new PrintStream(file);
        try {
            var specWithLog = LoggingUtils.specWithLogging(reqSpec, logStream);

            // 3) Fetch single user by ID with logging
            client.getUserById(specWithLog, id)
                    .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .body("id", equalTo(id))
                    .body("$", hasKey("firstName")); // essential field check
        } finally {
            logStream.close();
        }
    }
}
