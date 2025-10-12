# DummyAPI – REST Assured + JMeter API Testing Project

This project demonstrates a complete API automation testing workflow for the **DummyAPI public demo service** ([https://dummyapi.io](https://dummyapi.io)) using **Java, REST Assured, JUnit 4, JSON Schema validation, data-driven tests, and Apache JMeter**. The main objective was to validate **Users** and **Posts** endpoints through functional, data-driven, and performance tests — while maintaining modularity, reproducibility, and professional documentation.

All artifacts were implemented to reflect **real-world QA automation structure and methodology**, tailored for a **junior-level portfolio project**.

---

## Author

**Aleksa Aleksić**  
*Junior QA Automation Engineer (Portfolio Project)*  
**Project Duration:** 07-10-2025 → 12-10-2025

---

## Project Overview

The repository showcases a full example of how to design, implement, and document API test automation.  
Each component demonstrates proper separation of concerns, test readability, and maintainability.

- **Users API:** Covers fetching user list, retrieving specific user by ID, and handling negative scenarios for invalid IDs. Includes CSV-driven tests and JSON schema validation.
- **Posts API:** Includes fetching all posts, retrieving posts by ID, validating schema consistency, and verifying response structure using data-driven techniques.
- **Utilities:** Logging, schema validation, and retry logic implemented to mimic enterprise-grade test practices.
- **Performance Testing:** Lightweight JMeter smoke plan (`load_smoke.jmx`) validates response consistency and performance under a small concurrent load.
- **Reporting:** Allure-compatible results and HTML reports from Maven Surefire and JMeter provide full visibility into test outcomes.

All modules were implemented using **robust, reusable classes and utility layers**, ensuring the repository reflects professional automation standards.

---

## Robust Repository Structure (Overview)

> The following structure shows the main components of the project. Only essential folders and files are listed.

```bash
~/dummyapi-restassured-jmeter/
├── api-tests/
│   └── src/test/java/com/qa/dummyapi/
│       ├── base/               # BaseTest.java (REST Assured setup)
│       ├── client/             # ApiClient.java (wrapper for HTTP requests)
│       ├── config/             # Config.java (baseUrl, appId management)
│       ├── tests/
│       │   ├── users/          # Users API tests (CRUD, CSV-driven, negative)
│       │   └── posts/          # Posts API tests (CRUD, CSV-driven, negative)
│       ├── utils/              # LoggingUtils.java, SchemaValidationUtils.java
│       └── rules/              # RetryRule.java (optional retry logic)
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
│   └── api-reports/
├── target/
│   └── surefire-reports/
├── FinalReport.md
└── DailyReports/
```

This structure emphasizes clarity and scalability — each folder has a dedicated purpose (tests, configuration, schemas, test data, scripts, and reports).

---

## Tools & Environment

**Programming Language:** Java 11+  
**Test Framework:** REST Assured + JUnit 4  
**JSON Mapping & Validation:** Jackson, rest-assured-json-schema-validator  
**Performance Testing:** Apache JMeter (load_smoke.jmx)  
**Reporting:** Maven Surefire + Allure-ready results + JMeter HTML report  
**Version Control:** Git & GitHub  
**OS / IDE:** Ubuntu 22.04 LTS, IntelliJ IDEA  
**Build Tool:** Apache Maven  
**CI/CD (optional):** GitHub Actions workflow skeleton prepared

---

## Test Planning & Execution Flow

The project was executed in six structured workdays, following a logical progression:

- **Day 1:** Repository setup, Maven configuration, dependencies, and project skeleton.
- **Day 2:** Implemented Config.java, BaseTest.java, and ApiClient.java to centralize request configuration and client logic.
- **Day 3:** Developed initial Users API tests (happy path and negative), plus Posts API tests scaffolding.
- **Day 4:** Added data-driven (CSV) tests and SchemaValidationUtils.java, enabling flexible test data and structure validation.
- **Day 5:** Created JMeter smoke plan (load_smoke.jmx), performance CSV data, run scripts, and finalized reports.
- **Day 6:** Completed documentation — FinalReport.md and README.md — and verified repository readiness for portfolio presentation.

Each daily progress note and implemented class is detailed in `/DailyReports/`.

---

## Test Coverage & Metrics (Showcase)

| Module / Feature | Test Cases | Type | PASS | FAIL |
|------------------|-------------|------|------|------|
| Users API | 6 | Functional, Negative, Data-driven, Schema Validation | / | / |
| Posts API | 6 | Functional, Negative, Data-driven, Schema Validation | / | / |
| Utilities | 2 | Logging, Schema, Retry | / | / |
| Performance | 1 | JMeter Smoke | / | / |
| **Total (Implemented)** | **15+** | — | / | / |

> **Note:** Since DummyAPI requires a valid `app-id`, public runs may not achieve 100% success — this is expected and explained in `FinalReport.md`.

---

## Notable Implementations

- **Deterministic Data-Driven Testing:** `users.csv` and `posts.csv` ensure consistent execution regardless of API volatility.
- **Schema Validation:** Custom `SchemaValidationUtils` provides lightweight contract checks for response structure.
- **Logging Utilities:** `LoggingUtils.java` logs every request/response for transparency and debugging.
- **Retry Mechanism:** Optional `RetryRule.java` improves test resilience against transient demo API errors.
- **JMeter Smoke Plan:** `load_smoke.jmx` validates basic endpoint stability and response times under load.
- **Automation Scripts:** `run_api_tests.sh` and `run_jmeter.sh` provide reproducible execution from CLI.

---

## How to Run Locally

**Precondition:** Obtain a valid DummyAPI `app-id` from [https://dummyapi.io](https://dummyapi.io).

**Run Functional API Tests**
```bash
mvn clean test -DbaseUrl=https://dummyapi.io/data/v1 -DappId=YOUR_APP_ID
```

**Run JMeter Smoke Test**
```bash
./scripts/run_jmeter.sh performance-tests/load_smoke.jmx https://dummyapi.io/data/v1 YOUR_APP_ID 30 60
```

**Generate Allure Report (Optional)**
```bash
allure generate target/allure-results -o reporting/allure-report --clean
```

**Artifacts produced:**
- `target/surefire-reports/` – raw test results
- `target/allure-results/` – Allure data for reporting
- `reports/api-reports/*.log` – request/response logs
- `performance-tests/reports/load_smoke_html/` – JMeter dashboard

---

## Known Limitations & Notes

- Some tests require a valid `app-id`; without it, certain endpoints return **403 Unauthorized**.
- The project is a **showcase artifact**, not a live CI-green test suite.
- No destructive (POST/DELETE) operations are executed by default.
- All scripts and schemas are included for review and reproducibility.

---

## Conclusion

The DummyAPI REST Assured + JMeter Project successfully demonstrates:

- Professional API automation structure in Java
- Data-driven and schema-validated functional testing
- Lightweight performance validation via JMeter
- Clear documentation and reproducible environment setup

All source code, scripts, and artifacts are organized for **portfolio and learning purposes**, showcasing essential QA automation skills suitable for a junior automation tester.

---

## Essential Takeaways

- **Structured Framework:** Clear, reusable base and client layers
- **Data-Driven Testing:** CSV inputs ensure deterministic coverage
- **Validation Discipline:** Schema validation and negative testing
- **Performance Awareness:** JMeter integration for endpoint verification
- **Documentation Excellence:** Complete README, FinalReport, and daily logs
- **Portfolio Ready:** Shows practical QA automation fundamentals with clarity  