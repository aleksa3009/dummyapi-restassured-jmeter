# DummyAPI REST Assured Project

This project demonstrates a structured approach to API testing using **REST Assured** and **JUnit 4**.  
**Note:** This is a **showcase project**; actual tests will not pass without a valid `appId` from DummyAPI.io.

---

## Requirements
- Java 11+
- Maven
- Git

---

## Project Structure
```
dummyapi-tests/
├── src/
│   └── test/
│       ├── java/com/qa/dummyapi/
│       │   ├── config/Config.java
│       │   ├── base/BaseTest.java
│       │   ├── client/ApiClient.java
│       │   └── tests/users/UsersGetByIdTest.java
│       └── resources/config/config.properties.sample
├── .gitignore
├── README.md
├── pom.xml
└── .github/workflows/api-tests.disabled.yml
```

---

## Config
- Sample configuration is in `src/test/resources/config/config.properties.sample`
- Example properties:
```
baseUrl=https://dummyapi.io/data/v1
appId=REPLACE_WITH_YOUR_APP_ID
```
- **Do not commit your real API keys**.
- You can override with CLI parameters:
```bash
mvn -DbaseUrl=https://dummyapi.io/data/v1 -DappId=YOUR_APP_ID test
```

---

## Running Tests (Local)
Since this is a showcase, tests will **fail without a valid appId**.  
Once you have a valid `appId`, run:

```bash
mvn -Dtest=UsersGetByIdTest test
```

Or run a single test method:

```bash
mvn -Dtest=UsersGetByIdTest#getUserById_happyPath test
```

---

## CI / GitHub Actions
- The workflow is provided as `.github/workflows/api-tests.disabled.yml`
- It is **disabled by default** since tests require a personal `appId`.
- To enable in a real scenario, rename the file and add your secrets (`BASE_URL`, `DUMMYAPI_APP_ID`).

---

## Notes
- All classes (`Config.java`, `BaseTest.java`, `ApiClient.java`, test classes) are fully implemented for demonstration purposes.
- This project is primarily a **showcase** to demonstrate structure, REST Assured usage, and Maven setup.
- No real requests will succeed without proper API credentials.
