package com.qa.dummyapi.tests.posts;

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

public class PostsFetchThenGetTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Rule
    public TestName testName = new TestName();

    @Test
    public void fetchCollection_then_getById_happyPath() throws FileNotFoundException {
        // Fetch one post to get a valid postId
        var listResp = client.getPosts(1);
        listResp.then().statusCode(200);

        String id = listResp.jsonPath().getString("data[0].id");
        assertNotNull("Could not extract a post id", id);

        // Per-test logging setup
        File file = new File("reports/api-reports/" + testName.getMethodName() + ".log");
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + parentDir.getAbsolutePath());
        }
        PrintStream logStream = new PrintStream(file);

        try {
            var specWithLog = LoggingUtils.specWithLogging(reqSpec, logStream);

            client.getPostById(specWithLog, id)
                    .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .body("id", equalTo(id))
                    .body("$", hasKey("text"));
        } finally {
            logStream.close();
        }
    }
}
