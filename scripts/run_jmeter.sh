#!/usr/bin/env bash
set -euo pipefail

# Runs a JMeter test plan and generates an HTML report.

JMETER_TEST=${1:-performance-tests/load_smoke.jmx}
OUT_DIR=${2:-performance-tests/reports/load_smoke_html}
JTL_FILE=${3:-performance-tests/reports/load_smoke.jtl}
BASE_URL=${4:-${BASE_URL:-https://dummyapi.io/data/v1}}

echo "Running JMeter plan: ${JMETER_TEST}"
echo "Output: ${JTL_FILE}"
echo "Base URL: ${BASE_URL}"

# Clean previous report output
rm -rf "${OUT_DIR}" || true

# Run JMeter test in non-GUI mode
jmeter -n -t "${JMETER_TEST}" -l "${JTL_FILE}" -JbaseUrl="${BASE_URL}"

# Generate HTML report
echo "Generating HTML report to ${OUT_DIR} ..."
jmeter -g "${JTL_FILE}" -o "${OUT_DIR}"

echo "JMeter report generated at: ${OUT_DIR}"
