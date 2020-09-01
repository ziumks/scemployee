# docker build -t apim_employee .
# docker run --rm -p 8084:8084 -p 29001:29001  \
# --env eureka.client.serviceUrl.defaultZone=http://192.168.0.181:8761/eureka/  \
# --env spring.cloud.conig.uri=http://192.168.0.181:8888 \   #이건 안되네...
# apim_employee  --spring.profiles.active=service0
FROM adoptopenjdk/openjdk11:alpine-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
