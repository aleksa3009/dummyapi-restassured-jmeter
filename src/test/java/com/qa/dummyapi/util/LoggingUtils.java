package com.qa.dummyapi.util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.io.PrintStream;

public class LoggingUtils {

    // Creates a RequestSpecification with request/response logging
    public static RequestSpecification specWithLogging(RequestSpecification base, PrintStream logStream) {
        return new RequestSpecBuilder()
                .addRequestSpecification(base)
                .addFilter(new RequestLoggingFilter(logStream))
                .addFilter(new ResponseLoggingFilter(logStream))
                .build();
    }
}
