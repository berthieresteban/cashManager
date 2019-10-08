#
# ./Dockefile
# EPITECH 2019 - CashManager - Project
#

FROM java:8
VOLUME /tmp
ADD ./target/products-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]