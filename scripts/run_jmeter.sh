#!/usr/bin/env bash
set -euo pipefail

# Runs a JMeter test plan and generates an HTML report (JTL in CSV format)

JMETER_TEST=${1:-performance-tests/load_smoke.jmx}
OUT_DIR=${2:-performance-tests/reports/load_smoke_html}
JTL_FILE=${3:-performance-tests/reports/load_smoke.jtl}
BASE_URL=${4:-${BASE_URL:-https://dummyapi.io/data/v1}}
APP_ID=${5:-REPLACE_WITH_YOUR_APP_ID}

# Check if JMeter is installed
if ! command -v jmeter &> /dev/null; then
    echo "Error: JMeter is not installed or not in PATH."
    exit 1
fi

# Check if JMX file exists
if [ ! -f "$JMETER_TEST" ]; then
    echo "Error: JMX file not found at $JMETER_TEST"
    exit 1
fi

echo "Running JMeter plan: ${JMETER_TEST}"
echo "Output JTL: ${JTL_FILE} (CSV format)"
echo "HTML report: ${OUT_DIR}"
echo "Base URL: ${BASE_URL}"
echo "App ID: ${APP_ID}"

# Clean previous report output
rm -rf "${OUT_DIR}" || true

# Run JMeter test in non-GUI mode with CSV output
jmeter -n -t "${JMETER_TEST}" \
  -l "${JTL_FILE}" \
  -JbaseUrl="${BASE_URL}" \
  -JappId="${APP_ID}" \
  -Jjmeter.save.saveservice.output_format=csv \
  -Jjmeter.save.saveservice.assertion_results_failure_message=true \
  -Jjmeter.save.saveservice.default_delimiter=,

# Generate HTML report
echo "Generating HTML report to ${OUT_DIR} ..."
jmeter -g "${JTL_FILE}" -o "${OUT_DIR}"

echo "JMeter report generated at: ${OUT_DIR}/index.html"
