##
## EPITECH PROJECT, 2019
## Makefile
## Julien Legay
##

build:
		mvn clean package install -f ./apps/products/pom.xml
		docker-compose build

run:
		docker-compose up
