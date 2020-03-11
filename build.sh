#!/bin/sh
mvn clean install
docker container rm spring-boot-webservice
docker build --build-arg USER=webservice -t boilerplates/spring-boot-webservice-0.0.1 .
docker run -p 8080:8000 --name spring-boot-webservice -e JAVA_OPTS=-Dserver.port=8000 boilerplates/spring-boot-webservice-0.0.1
