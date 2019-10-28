#
# ./Dockefile
# EPITECH 2019 - CashManager - Project
#

FROM maven:3.5-jdk-9 AS build  
COPY ./src /usr/src/app/src  
COPY ./pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package install 

FROM  openjdk:9
COPY --from=build /usr/src/app/target/products-0.0.1-SNAPSHOT.jar /usr/app/products-0.0.1-SNAPSHOT.jar  
ENTRYPOINT ["java","-jar","/usr/app/products-0.0.1-SNAPSHOT.jar"]  