FROM ubuntu:latest
LABEL authors="matejvana"

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/PproProjektExpenseTracker-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-Dspring.profiles.active=local-postgres", "-jar", "app.jar"]