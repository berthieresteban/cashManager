#
# ./Dockefile
# EPITECH 2019 - CashManager - Client
#

FROM python:2.7
COPY  ./CashManagerApp/app-debug.apk /client/
RUN mv /client/app-debug.apk /client/client.apk
CMD cd /client && python -m SimpleHTTPServer 8081