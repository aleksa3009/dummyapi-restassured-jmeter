# Daily Report – 07-10-2025 (Day 1)

---

## Activities:

* Created project repository structure for **DummyAPI REST Assured + JMeter** locally and on GitHub.
* Configured **Maven project** with dependencies for REST Assured, JUnit 4, Hamcrest, Jackson, and SLF4J.
* Implemented **Config.java** for centralized reading of `baseUrl` and `appId` from `config.properties`, system properties, or environment variables.
* Implemented **BaseTest.java** with REST Assured setup and reusable `RequestSpecification`.
* Developed **ApiClient.java** as a small wrapper for GET requests (`/user/{id}`, `/post/{id}`, `/post?limit=...`).
* Created example functional test **UsersGetByIdTest.java** to validate `/user/{id}` endpoint.
* Prepared **config.properties.sample** with placeholders for `baseUrl` and `appId`.
* Updated `.gitignore` to exclude sensitive config, target folders, reports, IDE-specific files, and OS artifacts.
* Added **GitHub Actions workflow skeleton** (`.github/workflows/api-tests.disabled.yml`) demonstrating CI integration with Maven and environment secrets, but disabled for showcase.
* Created minimal **README.md skeleton** with project setup, run instructions, and placeholder notes about showcase nature of tests.
* Finalized **Test Plan** document, outlining scope, objectives, strategy, test items, deliverables, schedule, and repository structure.
* Organized project folders: `src/test/java` (clients, config, base, tests), `config`, `testdata`, `reports`, and performance test skeletons.

---

## Environment:

* **OS:** Linux Ubuntu 22.04 LTS
* **Java:** JDK 11
* **Maven:** 3.9.11
* **IDE:** IntelliJ IDEA 2025.2.2 (Community Edition)
* **Tools/Libraries:** REST Assured 4.5.1, JUnit 4.13.2, Hamcrest 2.2, Jackson 2.15.2, SLF4J 1.7.36, JMeter latest stable

---

## Issues:

* Cannot obtain valid `appId` from DummyAPI; API tests fail with 403 Forbidden without a real key.
* CI workflow cannot be fully tested because `DUMMYAPI_APP_ID` secret is unavailable.
* Tests are currently **showcase-only**; functionality verified only with placeholders.
* No other blockers; project structure, code, and documentation stable.

---

## Next Steps (Day 2):

* Implement GET `/user/{id}` happy path and negative case
* Add one data-driven test
* Local runs and fix issues
* Finalize Daily Report for Day 2
