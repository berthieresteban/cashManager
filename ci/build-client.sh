#!/bin/bash
set -e

cd apps/mobile/CashManagerApp
./gradlew clean build assembleDebug
mv ./app/build/outputs/apk/debug/app-debug.apk ./