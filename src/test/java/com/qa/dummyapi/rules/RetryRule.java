package com.qa.dummyapi.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RetryRule implements TestRule {
    private final int retryCount;

    public RetryRule(int retryCount) {
        this.retryCount = retryCount;
    }

    // Retries flaky tests caused by transient network issues
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable caught = null;
                for (int i = 0; i <= retryCount; i++) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable t) {
                        caught = t;
                        if (i == retryCount) throw caught;
                        System.err.println("Retrying " + description.getDisplayName() + " (" + (i + 1) + "/" + retryCount + ")");
                        Thread.sleep(500);
                    }
                }
            }
        };
    }
}