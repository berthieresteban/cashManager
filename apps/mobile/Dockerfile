#
# ./Dockefile
# EPITECH 2019 - CashManager - Client
#

FROM gradle:5.4.1 AS build  
COPY ./CashManagerApp /CashManagerApp 

ENV SDK_URL="https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip" \
    ANDROID_HOME="/usr/local/android-sdk" \
    ANDROID_VERSION=28 \
    ANDROID_BUILD_TOOLS_VERSION=27.0.3

# Download Android SDK
RUN mkdir "$ANDROID_HOME" .android \
    && cd "$ANDROID_HOME" \
    && curl -o sdk.zip $SDK_URL \
    && unzip sdk.zip \
    && rm sdk.zip \
    && mkdir "$ANDROID_HOME/licenses" || true \
    && echo "24333f8a63b6825ea9c5514f83c2829b004d1fee" > "$ANDROID_HOME/licenses/android-sdk-license"
    
RUN cd /CashManagerApp && ./gradlew clean build assembleDebug

FROM python:2.7
COPY --from=build /CashManagerApp/app/build/outputs/apk/debug/app-debug.apk /client/client.apk  
CMD cd /client && python -m SimpleHTTPServer 8081