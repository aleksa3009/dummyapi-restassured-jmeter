package com.qa.dummyapi.util;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidationUtils {

    /**
     * Validates a RestAssured Response against a JSON schema located in the classpath.
     * Example: validate(response, "schemas/user-schema.json");
     **/
    public static void validate(Response response, String classpathSchema) {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(classpathSchema));
    }
}
