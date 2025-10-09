package com.qa.dummyapi.tests.posts;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import com.qa.dummyapi.config.Config;
import org.junit.Assume;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class PostsUpdateTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Test
    public void updatePost_safeMode() {
        boolean enabled = Boolean.parseBoolean(Config.get("enableSideEffectTests", "false"));
        Assume.assumeTrue(enabled);

        String postId = "60d21b8667d0d8992e610c85";
        String payload = "{ \"text\": \"Updated via showcase\", \"likes\": 10 }";

        // client.updatePost(postId, payload)
        //      .then()
        //      .statusCode(anyOf(equalTo(200), equalTo(204)));
    }
}
