package com.qa.dummyapi.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {
    private final RequestSpecification spec;

    public ApiClient(RequestSpecification spec) {
        this.spec = spec;
    }

    // USERS

    // GET /user/{id} (default spec)
    public Response getUserById(String id) {
        return given().spec(spec)
                .when().get("/user/{id}", id);
    }

    // GET /user/{id} (override spec, e.g. per-test logging)
    public Response getUserById(RequestSpecification overrideSpec, String id) {
        return given().spec(overrideSpec)
                .when().get("/user/{id}", id);
    }

    // GET /user?limit={limit} (default spec)
    public Response getUsers(int limit) {
        return given().spec(spec)
                .when().get("/user?limit={limit}", limit);
    }

    // GET /user?limit={limit} (override spec, e.g. per-test logging)
    public Response getUsers(RequestSpecification overrideSpec, int limit) {
        return given().spec(overrideSpec)
                .when().get("/user?limit={limit}", limit);
    }

    // POSTS

    // GET /post/{id} (default spec)
    public Response getPostById(String id) {
        return given().spec(spec)
                .when().get("/post/{id}", id);
    }

    // GET /post?limit={limit} (default spec)
    public Response getPosts(int limit) {
        return given().spec(spec)
                .when().get("/post?limit={limit}", limit);
    }
}
