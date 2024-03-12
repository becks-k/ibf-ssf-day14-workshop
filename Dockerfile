# Build Stage

# FROM base image AS alias
FROM maven:3-eclipse-temurin-21 AS builder

# Label docker file
LABEL MAINTAINER="becky"
LABEL APPLICATION="SSF DAY 14 LECTURE"

ARG APP_DIR=/app

# Directory to contain scr and target
WORKDIR ${APP_DIR}

# Copy all required files to build the app into the image app folder to build image
# Code is compiled with the folders in image
# mvnw w refers to wrapper, if computer doesn't have it will download maven
COPY pom.xml .
COPY src src
COPY mvnw.cmd .
COPY mvnw .
COPY .mvn .mvn

# Run command to run mvn package, exclude unit testing
# mvn package downloads dependecies and builds jar file into target folder
RUN mvn package -Dmaven.test.skip=true

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

# nameofapplication-0.0.1-SNAPSHOT.jar
ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar target/day14-0.0.1-SNAPSHOT.jar