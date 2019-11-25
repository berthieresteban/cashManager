#
# ./Dockefile
# EPITECH 2019 - CashManager - Client
#

FROM gradle:5.4.1 AS build  
COPY ./CashManagerApp /CashManagerApp  
RUN cd /CashManagerApp && ./gradlew clean build assembleDebug

FROM python:2.7
COPY --from=build /CashManagerApp/app/build/outputs/apk/debug/app-debug.apk /client/client.apk  
CMD cd /client && python -m SimpleHTTPServer 8081