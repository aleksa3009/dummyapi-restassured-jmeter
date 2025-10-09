package com.qa.dummyapi.tests.posts;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class PostsCollectionTest extends BaseTest {

    private final ApiClient client = new ApiClient(reqSpec);

    @Test
    public void posts_collection_limit_smoke() {
        // Verify collection returns at most `limit` items
        int limit = 10;

        client.getPosts(limit)
                .then()
                .statusCode(200)
                .body("data", notNullValue())
                .body("data.size()", lessThanOrEqualTo(limit));
    }
}
