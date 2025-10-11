# JMeter Load Test Plan – DummyAPI

---

## 1. Objective

The goal of this load test is to validate the performance and stability of the **DummyAPI** service under light (smoke) load.  
Focus is on verifying response consistency, latency, and basic throughput for common GET endpoints.

---

## 2. Scope

This test includes two main API endpoints:

| Scenario | Endpoint | Description |
|-----------|-----------|-------------|
| 1 | `GET /post?limit=10` | Fetch collection of posts (collection smoke test) |
| 2 | `GET /user/{id}` | Fetch individual user details using IDs from CSV data |

---

## 3. Test Data

File: `performance-tests/testdata/user_ids.csv`

Example:
```csv
id
60d0fe4f5311236168a10a0d
60d0fe4f5311236168a10a0e
60d0fe4f5311236168a10a0f
```

---

## 4. Load Profile (Smoke Test)

| Parameter | Value | Description |
|------------|--------|-------------|
| Virtual Users (Threads) | 30 | Number of concurrent virtual users |
| Ramp-up Period | 10 s | Time to reach full load |
| Duration | 60 s | Total execution time |
| Loop Control | Runtime Controller or Thread Group duration | Defines iteration control |

---

## 5. Assertions

Each HTTP request must satisfy the following conditions:

| Assertion Type | Expected Value | Purpose |
|-----------------|----------------|----------|
| Response Code | `200` | Ensure successful HTTP response |
| Max Duration | ≤ `2000 ms` | Detect slow responses |

---

## 6. Output Reports

Results will be generated automatically after test execution:

| Type | File Path |
|------|------------|
| Raw results (JTL) | `performance-tests/reports/load_smoke.jtl` |
| HTML Dashboard | `performance-tests/reports/load_smoke_html/` |

---

## 7. Success Criteria (SLA Targets)

| Metric | Target | Description |
|---------|---------|-------------|
| Error rate | < 2% | Maximum allowed failed requests |
| Average response time | < 1000 ms | Mean latency across all samples |
| 95th percentile (p95) | < 2000 ms | 95% of requests must respond faster than 2 seconds |
| Throughput | Informative | Displayed for reference, not strict |

---

## 8. Tools & Execution

- **Tool:** Apache JMeter CLI (`/usr/bin/jmeter`)
- **Environment:** Local execution (can be replaced with Docker if needed)
- **Base URL:** `https://dummyapi.io/data/v1`
- **Headers:** `app-id` provided in test configuration

Command example (for later step):

```bash
jmeter -n -t performance-tests/DummyAPI_LoadTest.jmx \
  -l performance-tests/reports/load_smoke.jtl \
  -e -o performance-tests/reports/load_smoke_html/
```

---

## 9. Notes

- This is a **smoke-level performance test**, not a full stress test.
- Ideal for validating basic response times and verifying endpoint stability.
- Results can be compared across commits or environments to track regressions.

---