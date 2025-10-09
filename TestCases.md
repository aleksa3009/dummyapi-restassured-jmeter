# API Test Cases

## Users Tests – Day 2 Implementation

| Test ID    | Module | Test Description                    | Type                | Endpoint / Steps                       | Expected Result                          |
| ---------- | ------ | ----------------------------------- | ------------------- | -------------------------------------- | ---------------------------------------- |
| TC-API-001 | Users  | Get existing user by id (dynamic)   | Functional          | GET /user?limit=1 then GET /user/{id}  | 200 OK + id, firstName                   |
| TC-API-002 | Users  | Get user by id (fixed)              | Functional          | GET /user/{validId}                    | 200 OK + id, firstName, lastName         |
| TC-API-003 | Users  | Get user with invalid id            | Negative            | GET /user/{invalidId}                  | 400/404                                  |
| TC-API-004 | Users  | CSV-driven tests for multiple users | Functional/Negative | Read CSV rows and GET /user/{id}       | Expected status from CSV (200, 404, 400) |
| TC-API-005 | Users  | Request without app-id header       | Negative            | GET /user/{id} without required header | 401/403 or 4xx                           |

---

## Posts Tests – Day 3 Implementation

| Test ID    | Module | Test Description                            | Type                | Endpoint / Steps                                | Expected Result                          |
| ---------- | ------ | ------------------------------------------- | ------------------- | ----------------------------------------------- | ---------------------------------------- |
| TC-API-101 | Posts  | Fetch collection then get by id (dynamic)   | Functional          | GET /post?limit=1 → extract id → GET /post/{id} | 200 OK + id, text field                  |
| TC-API-102 | Posts  | Get post by id (fixed)                      | Functional          | GET /post/{validId}                             | 200 OK + id, text field                  |
| TC-API-103 | Posts  | Get post with non-existing id               | Negative            | GET /post/{nonExistingId}                       | 404 / 400                                |
| TC-API-104 | Posts  | Get post with invalid id format             | Negative            | GET /post/{invalidFormatId}                     | 400+                                     |
| TC-API-105 | Posts  | Collection limit validation                 | Functional/Smoke    | GET /post?limit={n}                             | 200 OK, data.size() ≤ n                  |
| TC-API-106 | Posts  | CSV-driven tests for multiple posts         | Functional/Negative | Read CSV rows → GET /post/{id}                  | Expected status from CSV (200, 404, 400) |
| TC-API-107 | Posts  | Missing app-id header                       | Negative            | GET /post/{id} without app-id header            | 401/403 or 4xx                           |
| TC-API-108 | Posts  | Bad query param (limit negative/non-number) | Negative            | GET /post?limit=-1 or limit=abc                 | 400+                                     |

---

### Notes

1. **Dynamic happy path (TC-API-101)** → uses first post from collection to generate id for GET test.
2. **Fixed-id sanity (TC-API-102)** → uses fixed postId for simple smoke test.
3. **CSV-driven tests (TC-API-106)** → uses `posts.csv` with 3 cases: valid, non-existing, invalid format.
4. **Negative tests (TC-API-103, TC-API-104, TC-API-107, TC-API-108)** → cover all scenarios returning 4xx/401/403.
5. **Collection limit (TC-API-105)** → ensures number of returned items does not exceed `limit`.
