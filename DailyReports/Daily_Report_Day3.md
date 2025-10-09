# Daily Report – 09-10-2025 (Day 3)

---

## Activities:

* Implemented **Posts API tests** to extend the DummyAPI test coverage and demonstrate CRUD diversity.
* Created the following test classes under `src/test/java/com/aleksa/dummyapi/tests/posts`:

    * **PostsGetByIdTest.java** – retrieves a post by valid and invalid IDs, validates structure and response code.
    * **PostsCollectionTest.java** – validates `/post?limit=` behavior and ensures returned collection size ≤ limit.
    * **PostsUpdateTest.java** – performs `PUT /post/{id}` with a small update payload (e.g. text or tags) and asserts 200 OK + field update.
    * **PostsDeleteTest.java** – deletes post by ID and checks for correct 200/204 response and response message.
    * **PostsUpdateOptionalTest.java** – alternative PUT variant with different payload fields and structure.
    * **PostsDeleteOptionalTest.java** – alternative DELETE variant that handles non-existing post and verifies 404/400 handling.
* Verified consistent folder structure: all new files correctly placed under the `posts` module.
* Ensured each test class uses **ApiClient** with per-test logging via `LoggingUtils.createRequestSpecWithLogs()`.
* Updated **TestCases.md** with new Posts test cases (GET, PUT, DELETE) following the same documentation pattern as Users.
* Committed all changes to Git with clear single-line commit messages:
    * `test: add GET, PUT, DELETE Posts API tests`
    * `docs: update API TestCases.md with new Posts test cases`

---

## Environment:

* **OS:** Linux Ubuntu 22.04 LTS
* **Java:** JDK 11
* **Maven:** 3.9.11
* **IDE:** IntelliJ IDEA 2025.2.2 (Community Edition)
* **Tools/Libraries:** REST Assured 4.5.1, JUnit 4.13.2, Hamcrest 2.2, Jackson 2.15.2, SLF4J 1.7.36

---

## Issues:

* PUT and DELETE endpoints not verified against live API due to dummy `appId` usage — expected to work logically based on structure.
* Some tests (optional variants) are placeholders for documentation/demo purposes and not expected to execute successfully without a valid token.
* No structural or syntax issues detected in implemented code; all tests compile successfully.

---

## Next Steps (Day 4):

* **Finalize TestCases.md** with a clear and complete test case table (Users + Posts).
* **Add reporting configuration** – either Allure or basic Maven Surefire HTML report.
* **Run full API test suite** locally and save report output for documentation.
* Verify that all test results and logs are properly generated and organized in the `reports/` directory.
