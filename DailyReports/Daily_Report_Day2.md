# Daily Report – 08-10-2025 (Day 2)

---

## Activities:

* Extended **ApiClient.java** with:

    * `getUsers(int limit)` and `getUsers(RequestSpecification, int limit)` for dynamic user collection fetch
    * Overloaded `getUserById(RequestSpecification, String id)` for per-test logging support
* Added **LoggingUtils.java** helper to create `RequestSpecification` with per-test request/response logging to file
* Added **RetryRule.java** to optionally retry flaky network-dependent tests (configured with simple backoff)
* Created **users.csv** test data file with valid, non-existing, and invalid user IDs
* Implemented Users API tests:

    * **UsersFetchThenGetTest.java** – dynamic happy path using `/user?limit=1` and then `/user/{id}` with logging
    * **UsersGetByIdTest.java** – fixed ID sanity test
    * **UsersNegativeTests.java** – invalid ID, non-existing user, and missing `app-id` header cases
    * **UsersCsvDrivenTest.java** – parameterized CSV-driven tests, each CSV row creates a separate test instance
* Updated **pom.xml** with minimal Maven Surefire plugin configuration for reporting and test selection
* Updated **TestCases.md** to document all new Users test cases with expected results
* Verified folder structure and ensured all Day 2 test classes reside in `src/test/java/com/qa/dummyapi/tests/users`

---

## Environment:

* **OS:** Linux Ubuntu 22.04 LTS
* **Java:** JDK 11
* **Maven:** 3.9.11
* **IDE:** IntelliJ IDEA 2025.2.2 (Community Edition)
* **Tools/Libraries:** REST Assured 4.5.1, JUnit 4.13.2, Hamcrest 2.2, Jackson 2.15.2, SLF4J 1.7.36, Maven Surefire 3.0.0-M8

---

## Issues:

* Cannot run tests with real `appId`; placeholder ID used for validation
* No live API verification; tests verified for structure, syntax, and correct status codes handling using dynamic/fixed IDs and CSV data
* CSV-driven tests robust, but rely on placeholder IDs in `users.csv`
* RetryRule implemented but not fully exercised without real network flakiness
* No blockers on project structure, test implementation, or reporting

---

## Next Steps (Day 3):

* Implement **Posts tests**:

    * GET `/post/{id}` individual retrieval
    * GET `/post?limit=...` collection tests
    * If safe, add simple POST test; otherwise omit
* Run all tests and collect basic reports
* Ensure per-test logging and Surefire output
* Begin integration of Posts tests into **TestCases.md**
