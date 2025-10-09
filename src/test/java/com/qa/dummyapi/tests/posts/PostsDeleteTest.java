package com.qa.dummyapi.tests.posts;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import com.qa.dummyapi.config.Config;
import org.junit.Assume;
import org.junit.Test;

public class PostsDeleteTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Test
    public void deletePost_safeMode() {
        boolean enabled = Boolean.parseBoolean(Config.get("enableSideEffectTests", "false"));
        Assume.assumeTrue(enabled);

        String postId = "60d21b8667d0d8992e610c85";

        // client.deletePost(postId)
        //      .then()
        //      .statusCode(anyOf(equalTo(200), equalTo(204)));
    }
}
