package com.qa.dummyapi.tests.users;

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
public class UsersCsvDrivenTest extends BaseTest {

    private final String userId;
    private final int expectedStatus;
    private final ApiClient client = new ApiClient(reqSpec);

    public UsersCsvDrivenTest(String userId, int expectedStatus) {
        this.userId = userId;
        this.expectedStatus = expectedStatus;
    }

    // Load CSV and provide parameters
    @Parameterized.Parameters(name = "{index}: id={0} -> status={1}")
    public static Collection<Object[]> data() throws IOException {
        List<Object[]> rows = new ArrayList<>();
        try (InputStream is = UsersCsvDrivenTest.class.getClassLoader().getResourceAsStream("testdata/users.csv");
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
    public void csvDriven_getUser_assertStatus() {
        client.getUserById(userId)
                .then()
                .statusCode(expectedStatus);
    }
}
