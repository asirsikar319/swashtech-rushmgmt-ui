FROM openjdk:8-jdk-alpine
ARG JAR_FILE=*.war
WORKDIR /
COPY ${JAR_FILE} app.war
ENTRYPOINT ["clean","jetty:run"]