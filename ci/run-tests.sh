#!/bin/bash
set -e

echo
echo " ### CashManager Launch API###"
echo "     ==============================="
echo

docker stop berthieresteban/cashmanager_api || true && docker rm berthieresteban/cashmanager_api || true
docker stop cashmanager_db || true && docker rm cashmanager_db || true

docker-compose -f ./docker-compose.yml up -d

echo " Waiting for Api to be up at http://localhost:9090"
echo

until curl --output /dev/null --silent --fail -d -H "Content-Type: application/json" -X GET http://localhost:9090/Carts; do
  printf '.'
  sleep 5
done

echo -e "\n API started!"

echo -e "\n Running tests!"

newman run ./ci/CashManager.postman_collection.json  --timeout 1800000 --timeout-request 1800000 --timeout-script 1800000 --bail