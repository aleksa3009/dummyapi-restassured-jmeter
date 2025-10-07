# Test Plan — DummyAPI REST Assured + JMeter

**Document version:** 1.0  
**Author:** Aleksa Aleksić   
**Date:** 07-10-2025  
**Repository tag / release:** v1.0-dummyapi-rest-jmeter

---

## 1. Purpose

This test plan defines a focused, **junior-level** automation project for functional API testing (REST Assured) and a single performance smoke test (JMeter) against DummyAPI (https://dummyapi.io). The goal is to produce a clean, demonstrable portfolio item that highlights core automation skills without introducing unnecessary complexity.


## 2. Scope

### In scope
- Automated **functional API tests** for primary resources: **Users** and **Posts** (happy-path and basic negative cases).
- Simple **data-driven** examples (JSON/CSV) showing parameterized test cases.
- A single **JMeter smoke/load test** demonstrating short-duration concurrent requests to critical GET endpoints.
- Basic reports and artifacts: HTML/log report for API tests, JTL and simple HTML report for JMeter, a short Final Report for portfolio.
- README with setup and run instructions for local execution.

### Out of scope
- Full contract testing or comprehensive JSON Schema coverage for every endpoint.
- Extensive performance testing (stress, soak, long-running tests) and SLA enforcement in CI.
- Advanced custom listeners, complex retry logic, or multi-environment profile orchestration.
- Mandatory video demo or published dashboards.


## 3. Objectives
- Demonstrate ability to design and implement automated API tests with REST Assured and JUnit.
- Show basic data-driven testing and response validation (status codes, key response fields).
- Show understanding of performance testing through a short JMeter smoke test.
- Produce clear, reproducible documentation and reports suitable for a portfolio entry.


## 4. Success Criteria (Exit Criteria)
- All planned functional tests for Users and Posts implemented and run locally.
- JMeter smoke test executed and results saved (JTL + simplified report).
- README contains step-by-step instructions to run API tests and JMeter plan.
- FinalReport.md summarises coverage, results (pass/fail), and lessons learned.


## 5. Assumptions & Constraints
- Using Java + Maven for API tests and JMeter CLI for performance test.
- API access keys (if required) will be provided as a configuration value or environment variable; secrets are not stored in the repo.
- Work is time-boxed to a 6-day execution schedule and intended for a junior portfolio.


## 6. Tools & Technologies
- Java 11+ and Maven
- REST Assured + JUnit 4
- JMeter (latest stable) — one JMX file for smoke load
- Git + GitHub (repo) — optional GitHub Actions for running API tests
- Optional: Allure or Extent for nicer API test HTML reports (kept as optional)


## 7. Test Items / Endpoints (examples)
- `GET /user/{id}` — retrieve user
- `GET /user/{invalid-id}` — negative case
- `GET /post/{id}` — retrieve post
- `GET /post?limit=...` — collection endpoint smoke
- `POST /user` or `POST /post` — *only if API supports creating test data without side effects* (otherwise, these are omitted)


## 8. Test Strategy & Approach

### 8.1 Functional tests (REST Assured)
- Framework: Maven project with a small test helper (`ApiClient` / `BaseTest`) that centralizes baseUri and common request configuration.
- Test organization: `src/test/java` packages for `users` and `posts` tests.
- Validations:
    - Status codes (200, 201, 404, 400 where applicable)
    - Presence of required fields (id, name, title, ownerId)
    - Basic value assertions for a few fields to demonstrate intent
    - Lightweight contract checks (optional JSON structure checks) rather than large schema files
- Data-driven: demonstrate at least one parameterized test (CSV or JSON-driven) for multiple valid/invalid inputs.
- Logging: minimal request/response logging to console or file for debugging only.

### 8.2 Performance smoke (JMeter)
- Single `.jmx` file `performance-tests/load_smoke.jmx` that:
    - Targets a small set of well-known GET endpoints (collection + single resource)
    - Simulates a short burst load (e.g., 30–50 virtual users for 1–2 minutes)
    - Contains simple assertions (response code, max response time threshold)
    - Saves results to `reports/jmeter-reports/load_smoke.jtl` and optional HTML snapshot
- Rationale: validate the project owner understands basic load testing concepts without overcomplicating for junior portfolio.


## 9. Test Data
- `testdata/` includes small JSON payloads and/or a CSV used for parameterized tests.
- Sensitive data (API keys) must be provided through `config/config.properties` or environment variables and not checked into VCS.


## 10. Repository Structure (projected)
```
jmeter-restassured-api-testing/
├── api-tests/
│   └── src/test/java/
│       ├── com.project.tests.users/  # UserTests.java
│       └── com.project.tests.posts/  # PostTests.java
├── performance-tests/
│   └── load_smoke.jmx
├── config/
│   └── config.properties
├── testdata/
│   └── users.csv
├── reports/
│   ├── api-reports/
│   └── jmeter-reports/
├── TestPlan.md
├── TestCases.md
├── FinalReport.md
├── README.md
└── pom.xml
```


## 11. Deliverables
- `TestPlan.md` (this document)
- `TestCases.md` — concise table of implemented tests and expected results
- `api-tests/` — working REST Assured test code (Users, Posts)
- `performance-tests/load_smoke.jmx` — JMeter smoke test
- `config/config.properties` — baseUrl and API keys placeholder
- `reports/` — sample run artifacts: API report and JMeter JTL
- `FinalReport.md` — short project summary for portfolio
- `README.md` — how to set up and run the project locally


## 12. Test Schedule — 6 days (detailed)
> Each day is planned for ~6–7 hours of focused work.

**Day 1 — Setup & Bootstrapping**
- Create project skeleton, add `pom.xml`, add `config.properties` sample
- Implement minimal `BaseTest` or `ApiClient` for REST Assured configuration
- Add CI skeleton (optional) only for API tests

**Day 2 — Users tests**
- Implement `GET /user/{id}` happy path and negative case
- Add one data-driven test
- Local runs and fix issues

**Day 3 — Posts tests**
- Implement `GET /post/{id}`, collection GET tests
- If safe, add simple POST test; otherwise omit
- Run and collect basic reports

**Day 4 — Test cases documentation & reporting**
- Finalize `TestCases.md` with a clear table
- Add reporting configuration (Allure or plain HTML)
- Run full API test suite and save report

**Day 5 — JMeter smoke test**
- Create `load_smoke.jmx` (30–50 users, short duration)
- Run JMeter CLI, collect JTL and generate a basic report

**Day 6 — Final report and polish**
- Write `FinalReport.md` (summary, key results, lessons learned)
- Final README polish, add run commands and example outputs
- Tag repo and prepare for portfolio submission


## 13. Example Test Cases (short)
| ID | Feature | Test Case | Type | Steps | Expected Result |
|----|--------:|----------|:----:|-------|-----------------|
| TC-API-001 | Users | Get existing user by id | Functional | Send GET /user/{validId} | 200 OK + body contains id, firstName or name |
| TC-API-002 | Users | Get user with invalid id | Negative | Send GET /user/{invalidId} | 404 or 400 |
| TC-API-003 | Posts | Get post by id | Functional | Send GET /post/{validId} | 200 OK + body contains id, text/owner |
| TC-API-004 | Posts | Posts collection smoke | Functional | GET /post?limit=10 | 200 OK + array length <= limit |


## 14. How to run (local) — examples
> These exact commands are included in the repo README. Replace `${BASE_URL}` and `${APP_ID}` with your values.

**Run API tests (Maven + JUnit)**
```
mvn clean test -DbaseUrl=${BASE_URL} -DappId=${APP_ID}
```

**Run JMeter smoke test (CLI)**
```
jmeter -n -t performance-tests/load_smoke.jmx -l reports/jmeter-reports/load_smoke.jtl -JbaseUrl=${BASE_URL}
```


## 15. Reporting & Metrics
- API tests: console + HTML/Allure reports (optional). Include list of failed tests and short request/response log snippets.
- JMeter: store JTL file and, optionally, a generated HTML snapshot. Keep only a short summary (average response time, throughput, max response time) for portfolio.


## 16. Defect Reporting
- Use a simple defect template saved as `DefectReport.md` containing: summary, steps to reproduce, expected vs actual, logs/JTL references, and status.
- Keep defects to a maximum of 3 documented items for the portfolio (choose most meaningful findings).


## 17. Risks & Mitigation
- **Risk:** API rate limits or side-effectful endpoints.  
  **Mitigation:** Use read-only endpoints for smoke runs; request an API key that allows testing. Document any limitations.
- **Risk:** Overly complex test infrastructure.  
  **Mitigation:** Keep test runner and reports simple and local-first.


## 18. Roles & Responsibilities
- **Author / Implementer:** Aleksa Aleksić — implement tests, run and collect artifacts.
- **Reviewer (optional):** Mentor or peer — quick code review and run verification.


## 19. Approval
- Project owner signs off when all in-scope tests are implemented and reports generated.

---
