# Daily Report – 11-10-2025 (Day 5)

---

## Activities:

* Designed **JMeter load_smoke.jmx** test plan for portfolio showcase, including:
    * GET `/post?limit=10` (posts collection)
    * GET `/user/{id}` (individual users, CSV-driven)
    * User Defined Variables: `baseUrl`, `appId`, `num_threads`, `ramp_up`, `test_duration`
    * CSV Data Set Config for user IDs
    * HTTP Header Manager for `app-id`
    * Response Assertions (HTTP 200) and Duration Assertions (max 2000ms)
* Created **performance-tests/testdata/jmeter-users.csv** with sample user IDs for JMeter tests.
* Developed **scripts/run_jmeter.sh** for reproducible CLI execution and HTML report generation:
    * Ensured JTL is CSV for easier parsing
    * Added checks for JMeter installation and JMX file existence
* Generated HTML dashboard reports (`performance-tests/reports/load_smoke_html`) and verified basic test execution locally.
* Added optional **GitHub Actions workflow** (`.disabled`) to demonstrate CI integration for JMeter smoke tests without automatic triggers.
* Documented placeholders and workflow notes for portfolio clarity.

---

## Environment:

* **OS:** Linux Ubuntu 22.04 LTS
* **Java:** JDK 11
* **Maven:** 3.9.11
* **IDE:** IntelliJ IDEA 2025.2.2 (Community Edition)
* **Tools/Libraries:** REST Assured 4.5.1, JUnit 4.13.2, Hamcrest 2.2, Jackson 2.15.2, SLF4J 1.7.36, JMeter 5.6.2

---

## Issues:

* JMeter execution for heavy load not performed — smoke test used for portfolio showcase only.
* CSV parsing scripts (awk) optional; skipped for simplicity.
* CI workflow disabled (`.disabled`) to prevent automatic run on GitHub Actions runners.

---

## Next Steps (Day 6):

* **Day 6 — Final report and polish**
    - Write `FinalReport.md` summarizing test plan, key results, and lessons learned.
    - Final README polish: include run commands, example outputs, and showcase instructions.
    - Tag repository and prepare for portfolio submission.
