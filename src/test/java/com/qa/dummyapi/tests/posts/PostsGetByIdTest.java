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

public class PostsGetByIdTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Rule
    public TestName testName = new TestName();

    @Test
    public void getPostById_happyPath_fixedId() throws FileNotFoundException {
        // Fixed postId for sanity check
        String exampleId = "60d21b8667d0d8992e610c85";

        // Per-test logging setup
        File file = new File("reports/api-reports/" + testName.getMethodName() + ".log");
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + parentDir.getAbsolutePath());
        }
        PrintStream logStream = new PrintStream(file);

        try {
            var specWithLog = LoggingUtils.specWithLogging(reqSpec, logStream);

            client.getPostById(specWithLog, exampleId)
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(exampleId))
                    .body("$", hasKey("text"));
        } finally {
            logStream.close();
        }
    }
}
