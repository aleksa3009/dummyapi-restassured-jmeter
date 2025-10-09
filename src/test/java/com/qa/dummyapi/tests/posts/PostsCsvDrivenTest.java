package com.qa.dummyapi.tests.posts;

import com.qa.dummyapi.base.BaseTest;
import com.qa.dummyapi.client.ApiClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class PostsCsvDrivenTest extends BaseTest {

    private final String postId;
    private final int expectedStatus;
    private final ApiClient client = new ApiClient(reqSpec);

    public PostsCsvDrivenTest(String postId, int expectedStatus) {
        this.postId = postId;
        this.expectedStatus = expectedStatus;
    }

    @Parameterized.Parameters(name = "{index}: id={0} -> {1}")
    public static Collection<Object[]> data() throws IOException {
        List<Object[]> rows = new ArrayList<>();
        // Load CSV from resources
        try (InputStream is = PostsCsvDrivenTest.class.getClassLoader().getResourceAsStream("testdata/posts.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split(",");
                if (parts.length < 2) continue;
                rows.add(new Object[]{parts[0].trim(), Integer.parseInt(parts[1].trim())});
            }
        }
        return rows;
    }

    @Test
    public void csvDriven_getPost_assertStatus() {
        // Verify GET /post/{id} returns expected status from CSV
        client.getPostById(postId)
                .then()
                .statusCode(expectedStatus);
    }
}
