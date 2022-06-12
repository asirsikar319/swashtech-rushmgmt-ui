FROM openjdk:8-jdk-alpine
ARG JAR_FILE=swashtech-rushmgmt-ui-1.0-SNAPSHOT.war
WORKDIR /
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","app.jar"]