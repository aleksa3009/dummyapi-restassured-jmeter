# API Test Cases

## Users Tests – Day 2 Implementation

| Test ID    | Module | Test Description                       | Type                | Endpoint / Steps                                         | Expected Result |
|------------|--------|---------------------------------------|-------------------|--------------------------------------------------------|----------------|
| TC-API-001 | Users  | Get existing user by id (dynamic)     | Functional        | GET /user?limit=1 then GET /user/{id}                 | 200 OK + id, firstName |
| TC-API-002 | Users  | Get user by id (fixed)                | Functional        | GET /user/{validId}                                    | 200 OK + id, firstName, lastName |
| TC-API-003 | Users  | Get user with invalid id               | Negative          | GET /user/{invalidId}                                  | 400/404 |
| TC-API-004 | Users  | CSV-driven tests for multiple users   | Functional/Negative | Read CSV rows and GET /user/{id}                     | Expected status from CSV (200, 404, 400) |
| TC-API-005 | Users  | Request without app-id header          | Negative          | GET /user/{id} without required header                | 401/403 or 4xx |
