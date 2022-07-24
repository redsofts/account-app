FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/app.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]