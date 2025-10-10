#!/usr/bin/env bash
set -euo pipefail

# Run all API tests and generate Allure results
BASE_URL=${1:-${BASE_URL:-https://dummyapi.io/data/v1}}
APP_ID=${2:-${APP_ID:-REPLACE_WITH_APP_ID}}

echo "Running API tests with BASE_URL=${BASE_URL}"

# Clean previous results
rm -rf target/allure-results target/site/allure-maven-plugin || true

# Execute test suite
mvn clean test -DbaseUrl="${BASE_URL}" -DappId="${APP_ID}"

echo "Tests finished."
echo "Allure results: target/allure-results"
echo "View report with: allure serve target/allure-results"