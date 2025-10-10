# Daily Report – 10-10-2025 (Day 4)

---

## Activities:

* Added **SchemaValidationUtils.java** under `src/test/java/com/aleksa/dummyapi/utils` to enable JSON schema validation for REST Assured responses.
* Updated **UsersGetByIdTest.java** and **PostsGetByIdTest.java** to include schema validation calls using `SchemaValidationUtils.validate()`.
* Verified that both Users and Posts GET endpoints are validated against `schemas/user-schema.json` and `schemas/post-schema.json`.
* Modified `pom.xml` to include necessary dependencies for:
    * **JSON Schema Validator** (`rest-assured json-schema-validator`)
    * **Allure reporting** (`allure-junit4` and `allure-maven plugin`)
* Created `scripts/run_api_tests.sh` to automate running all API tests and produce Allure results locally.
* Updated `.github/workflows/api-tests.yml` to showcase CI workflow including:
    * Running API tests with Maven
    * Uploading Allure results
    * Uploading Surefire reports
      Workflow is disabled for automatic triggers and serves as a portfolio demonstration.
* Ensured all code compiles successfully and test classes maintain consistent structure:
    * `UsersGetByIdTest.java` – GET /user/{id} with schema validation
    * `PostsGetByIdTest.java` – GET /post/{id} with schema validation

---

## Environment:

* **OS:** Linux Ubuntu 22.04 LTS
* **Java:** JDK 11
* **Maven:** 3.9.11
* **IDE:** IntelliJ IDEA 2025.2.2 (Community Edition)
* **Tools/Libraries:** REST Assured 4.5.1, JUnit 4.13.2, Hamcrest 2.2, Jackson 2.15.2, SLF4J 1.7.36, Allure 2.21.0

---

## Issues:

* JMeter CLI not installed locally — JMeter-related scripts (`run_jmeter.sh`) are included for showcase only.
* Allure CLI not installed locally; local report generation not verified but workflow is configured in GitHub Actions.
* JSON schemas are basic/minimal; placeholder content sufficient for showcase and automated validation demo.

---

## Next Steps (Day 5):

* **Create JMeter load_smoke.jmx** – a short smoke test plan (30–50 virtual users, short duration).
* **Run JMeter CLI** using `scripts/run_jmeter.sh` to produce JTL output.
* **Generate HTML report** from JTL to verify JMeter test results.
* Optionally integrate JMeter reports into project documentation or GitHub Actions showcase.
* Continue expanding API test coverage and ensure all schema validations are included where applicable.
