package com.qa.dummyapi.tests.posts;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import com.qa.dummyapi.util.LoggingUtils;
import com.qa.dummyapi.config.Config;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.File;
import java.io.PrintStream;

public class PostsUpdateWithLoggingTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Rule
    public TestName testName = new TestName();

    @Test
    public void updatePost_withLogging() throws Exception {
        boolean enabled = Boolean.parseBoolean(Config.get("enableSideEffectTests", "false"));
        Assume.assumeTrue(enabled);

        String postId = "60d21b8667d0d8992e610c85";
        String payload = "{ \"text\": \"Updated via logging showcase\", \"likes\": 5 }";

        File file = new File("reports/api-reports/" + testName.getMethodName() + ".log");
        PrintStream logStream = new PrintStream(file);
        try {
            var specWithLog = LoggingUtils.specWithLogging(reqSpec, logStream);
            // client.updatePost(specWithLog, postId, payload)
            //      .then()
            //      .statusCode(anyOf(equalTo(200), equalTo(204)));
        } finally {
            logStream.close();
        }
    }
}
