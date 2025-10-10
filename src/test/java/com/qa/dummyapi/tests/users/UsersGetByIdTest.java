package com.qa.dummyapi.tests.users;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import com.qa.dummyapi.util.LoggingUtils;
import com.qa.dummyapi.util.SchemaValidationUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.hamcrest.Matchers.*;

public class UsersGetByIdTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Rule
    public TestName testName = new TestName();

    @Test
    public void getUserById_happyPath_fixedId() throws FileNotFoundException {
        String exampleId = "60d0fe4f5311236168a109ca"; // fixed valid ID

        // create per-test log
        File file = new File("reports/api-reports/" + testName.getMethodName() + ".log");
        PrintStream logStream = new PrintStream(file);
        try {
            var specWithLog = LoggingUtils.specWithLogging(reqSpec, logStream);

            // execute request
            Response res = client.getUserById(specWithLog, exampleId);

            // basic response assertions
            res.then()
                    .statusCode(200)
                    .body("id", equalTo(exampleId))
                    .body("firstName", notNullValue())
                    .body("lastName", notNullValue());

            // JSON schema validation
            SchemaValidationUtils.validate(res, "schemas/user-schema.json");

        } finally {
            logStream.close();
        }
    }
}