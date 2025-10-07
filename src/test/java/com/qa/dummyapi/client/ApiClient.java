package com.qa.dummyapi.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {
    private final RequestSpecification spec;

    public ApiClient(RequestSpecification spec) {
        this.spec = spec;
    }

    // GET /user/{id}
    public Response getUserById(String id) {
        return given().spec(spec)
                .when().get("/user/{id}", id);
    }

    // GET /post/{id}
    public Response getPostById(String id) {
        return given().spec(spec)
                .when().get("/post/{id}", id);
    }

    // GET /post?limit={limit}
    public Response getPosts(int limit) {
        return given().spec(spec)
                .when().get("/post?limit={limit}", limit);
    }
}
