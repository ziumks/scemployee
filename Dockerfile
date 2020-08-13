#docker build -t employee-service .
#docker run --rm -p 8082:8082 employee-service
#FROM openjdk:11-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]