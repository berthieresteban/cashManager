#!/bin/bash
set -e

echo
echo " ### CashManager Launch API###"
echo "     ==============================="
echo

docker stop berthieresteban/cashmanager_api || true && docker rm berthieresteban/cashmanager_api || true
docker stop cashmanager_db || true && docker rm cashmanager_db || true

docker-compose -f ./docker-compose.yml up -d api db

echo " Waiting for Api to be up at http://localhost:8080"
echo

until curl --output /dev/null --silent --fail -d -H "Content-Type: application/json" -X GET http://localhost:8080/isApiUp; do
  printf '.'
  sleep 5
done

echo -e "\n API started!"
