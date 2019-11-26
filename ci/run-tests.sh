#!/bin/bash
set -e

echo
echo " ### CashManager Run API tests###"
echo "     ==============================="
echo

newman run ./ci/CashManager.postman_collection.json  -e ./ci/Cash_manager_api_test.postman_environment.json  --timeout 1800000 --timeout-request 1800000 --timeout-script 1800000 --bail