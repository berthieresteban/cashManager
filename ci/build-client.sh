#!/bin/bash
set -e

cd apps/mobile/CashManagerApp
mv ./app/src/main/java/com/epitech/cashmanagerapp/helper/Utils.kt ../../../ci/Utils.kt
mv ../../../ci/Utils.2.kt ./app/src/main/java/com/epitech/cashmanagerapp/helper/Utils.kt
./gradlew clean build assembleDebug
mv ./app/build/outputs/apk/debug/app-debug.apk ./
mv ./app/src/main/java/com/epitech/cashmanagerapp/helper/Utils.kt ../../../ci/Utils.2.kt
mv ../../../ci/Utils.kt ./app/src/main/java/com/epitech/cashmanagerapp/helper/Utils.kt