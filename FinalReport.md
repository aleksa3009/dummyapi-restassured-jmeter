# Final Report – DummyAPI REST Assured + JMeter

**Project:** API functional tests + JMeter smoke for DummyAPI (https://dummyapi.io)  
**Tester / Author:** Aleksa Aleksić  
**Duration:** 07-10-2025 to 12-10-2025  
**Base URL:** https://dummyapi.io/data/v1

**Repository tag / release:** v1.0-dummyapi-rest-jmeter

---

### Important note (showcase / portfolio)

This is a **showcase project** created to demonstrate how a professional junior-level API automation project should be designed, implemented and documented. The repository contains complete examples (framework skeleton, API client, tests, JSON schemas, reporting, and a JMeter smoke plan). The tests are illustrative of professional practice — they show structure, approach and tooling.

Because this is a demo / showcase setup, some tests may not pass when run against the live demo service in your environment. One concrete reason: for some endpoints the tester (project owner) no longer can obtain the special API app-id required by the demo (or the demo account/login flow to retrieve a long-lived test key is unavailable). That limitation is intentional in this deliverable and is called out so reviewers understand this is a portfolio artifact demonstrating skill and structure rather than a CI-green production suite.

---

## 1. Introduction

This report summarizes the DummyAPI REST Assured + JMeter project: a compact, well-documented API testing portfolio piece that demonstrates REST Assured functional testing (Users & Posts resources), JSON schema validation, data-driven tests, per-test logging, Allure-ready artifacts, and a single JMeter smoke plan for basic performance verification.

The project emphasizes:

- Clear, single-purpose tests and helpers (ApiClient, BaseTest, Config)
- Deterministic data-driven examples (CSV) and fixtures
- Lightweight JSON schema validation for responses
- Reproducible reporting (Allure-compatible results) and a JMeter smoke test
- Scripts to run tests and package artifacts for portfolio delivery

This is a showcase project — it illustrates best practices, not an SLA-backed production test system.

---

## 2. Scope & Objectives

**Scope**

- Functional API tests for primary resources: Users and Posts (happy-path and basic negative cases)
- At least one data-driven test for Users and Posts (CSV)
- JSON schema validation for response structure (minimal schemas)
- One JMeter smoke test (short burst on selected GET endpoints)
- Reporting artifacts (Surefire, Allure-results) and run scripts

**Objectives**

1. Show ability to implement automated API tests using REST Assured and JUnit.
2. Demonstrate data-driven testing and JSON schema checks.
3. Provide a short JMeter smoke test and collect JTL + HTML report.
4. Produce clear documentation and a portfolio-ready package.

---

## 3. Test Environment & Tools

- **Java:** 11+ (Maven project)
- **Test framework:** REST Assured + JUnit 4
- **JSON mapping / validation:** Jackson, rest-assured json-schema-validator
- **Performance:** Apache JMeter (one JMX smoke plan)
- **Reporting / artifacts:** Maven Surefire reports, Allure-compatible target/allure-results/ (scripted generation), JMeter HTML report
- **Scripts & helpers:** Bash scripts in `/scripts` to run API tests and JMeter plan
- **Version control / CI:** Git, GitHub (Actions workflow skeleton prepared)

---

## 4. Robust Repository Structure & Key Artifacts

```bash
~/dummyapi-restassured-jmeter/
├── api-tests/
│   └── src/test/java/com/aleksa/dummyapi/
│       ├── base/               # BaseTest.java (RestAssured setup)
│       ├── client/             # ApiClient.java (wrapper for endpoints)
│       ├── config/             # Config.java
│       ├── tests/
│       │   ├── users/          # Users test classes (happy/negative/csv-driven)
│       │   └── posts/          # Posts test classes (happy/negative/csv-driven)
│       ├── utils/              # LoggingUtils.java, SchemaValidationUtils.java
│       └── rules/              # RetryRule.java (optional)
├── performance-tests/
│   ├── load_smoke.jmx
│   └── testdata/jmeter-users.csv
├── src/test/resources/
│   ├── config/config.properties.sample
│   └── schemas/
│       ├── user-schema.json
│       └── post-schema.json
├── testdata/
│   ├── users.csv
│   └── posts.csv
├── scripts/
│   ├── run_api_tests.sh
│   ├── run_jmeter.sh
│   └── package_portfolio.sh
├── reports/
│   └── api-reports/            # per-test HTTP logs
├── target/
│   └── surefire-reports/       # maven test outputs (if tests run)
└── README.md
```

---

## 5. Test Case Coverage & (Showcase) Metrics

| Module / Feature | Tests Implemented (Examples) | Test Types | PASS | FAIL |
|------------------|------------------------------|-------------|------|------|
| Users | Get user by id, Get users list, negative invalid id, CSV-driven status tests | Functional + Negative + Data-driven + Schema validation | / | / |
| Posts | Get post by id, Get posts collection, negative cases, CSV-driven tests | Functional + Negative + Data-driven + Schema validation | / | / |
| API utilities | ApiClient request wrappers, Request/Response logging, Retry rule | Test infra | / | / |
| Performance | JMeter smoke: GET /post?limit=10, GET /user/{id} | Smoke load | / | / |
| Reporting | Surefire, Allure-compatible target/allure-results, per-test logs | Artifacts | / | / |
| **Total (implemented)** | — | — | / | / |

---

## 6. Test Execution & Artifacts (How to Reproduce Locally)

**Precondition:** To run passing tests, ensure a valid `app-id` is supplied (via `-DappId=YOUR_APP_ID` or `config/config.properties`).

### Run API tests (Maven)
```bash
mvn clean test -DbaseUrl=https://dummyapi.io/data/v1 -DappId=YOUR_APP_ID
```

### Run JMeter smoke test (CLI)
```bash
./scripts/run_jmeter.sh performance-tests/load_smoke.jmx https://dummyapi.io/data/v1 YOUR_APP_ID 30 60
```
Outputs:
- `performance-tests/reports/load_smoke.jtl`
- `performance-tests/reports/load_smoke_html/index.html`

### Generate Allure report (if CLI installed)
```bash
allure generate target/allure-results -o reporting/allure-report --clean
```

**Artifacts produced:**
- `target/surefire-reports/` — raw JUnit/test result files
- `target/allure-results/` — allure result folders
- `reports/api-reports/*.log` — per-test HTTP logs
- `performance-tests/reports/load_smoke_html/` — JMeter dashboard

---

## 7. Defect Analysis & Known Limitations

- **High Severity:** None recorded in showcase runs
- **Medium/Low Severity:** None significant; only potential 403/404 results due to missing valid `app-id`
- **Root Cause:** Demo API requires `app-id` key that is not publicly available; hence, tests fail when unauthorized

**Mitigation:**
- Tests are deterministic and safe (read-only by default).
- `enableSideEffectTests` flag protects create/delete operations.
- Clear documentation ensures reproducibility when credentials are available.

---

## 8. Exploratory & Additional Insights

1. Implemented per-test HTTP logging for request/response visibility.
2. CSV-driven tests demonstrate parameterization and reusability.
3. Schema validation utilities ensure lightweight contract testing.
4. Bash scripts make local and CI execution reproducible.

---

## 9. Lessons Learned & Recommendations

1. Keep secrets (app-id) out of version control; pass via CI secrets.
2. Prefer read-only demo endpoints to avoid instability.
3. Use fixture data for deterministic, reproducible runs.
4. Attach artifacts (Allure/JMeter) to releases for transparency.
5. Automate report generation (optional) for CI pipelines.

---

## 10. Conclusion & Next Steps

This repository and suite serve as a **portfolio showcase** of API automation (REST Assured) and JMeter smoke integration.  
All artifacts, documentation, and structure follow professional QA practices.

**Next steps for reviewers:**
- Obtain a valid DummyAPI `app-id` to execute the suite fully.
- Optionally mock endpoints with WireMock for reproducibility.
- Publish Allure reports to GitHub Pages (optional).

---

## Appendix A — Files Changed / Created (Selected)

- pom.xml — dependencies for rest-assured, json-schema-validator, surefire, allure-junit4
- src/test/java/com/qa/dummyapi/config/Config.java
- src/test/java/com/qa/dummyapi/base/BaseTest.java
- src/test/java/com/qa/dummyapi/client/ApiClient.java
- src/test/java/com/qa/dummyapi/util/LoggingUtils.java
- src/test/java/com/qa/dummyapi/utils/SchemaValidationUtils.java
- src/test/java/com/qa/dummyapi/tests/users/UsersCsvDrivenTest.java
- src/test/java/com/qa/dummyapi/tests/users/UsersFetchThenGetTest.java
- src/test/java/com/qa/dummyapi/tests/users/UsersNegativeTests.java
- src/test/java/com/qa/dummyapi/tests/posts/PostsCsvDrivenTest.java
- performance-tests/load_smoke.jmx, performance-tests/testdata/jmeter-users.csv
- scripts/run_api_tests.sh, scripts/run_jmeter.sh, scripts/package_portfolio.sh
- src/test/resources/schemas/user-schema.json, post-schema.json
- TestCases.md, FinalReport.md

---

## Appendix B — Quick Run Commands

```bash
# Run API tests (supply your appId)
mvn clean test -DbaseUrl=https://dummyapi.io/data/v1 -DappId=YOUR_APP_ID

# Run JMeter smoke plan
./scripts/run_jmeter.sh performance-tests/load_smoke.jmx https://dummyapi.io/data/v1 YOUR_APP_ID 30 60

# Generate static Allure report (if Allure CLI installed)
allure generate target/allure-results -o reporting/allure-report --clean

# Package portfolio zip
./scripts/package_portfolio.sh
```

---

## Closing Statement

This Final Report and the associated repository show how a junior automation engineer would design and deliver a compact, professional API automation portfolio item: clear structure, data-driven examples, schema validation, per-test logging, and a basic performance smoke test—plus scripts and documentation to reproduce or review results.

Because the live demo API requires a special `app-id` that is not embedded in the repository for security and privacy reasons, some runs performed by a reviewer without that key will fail. That is intentional: the repository demonstrates how to do it correctly and provides the artifacts and instructions so it can be executed reproducibly once the appropriate key or a stubbed environment is provided.