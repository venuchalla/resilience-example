FROM openjdk:19-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} resilience-example.jar
ENTRYPOINT ["java","-jar","/resilience-example.jar"]