FROM eclipse-temurin:21-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} resilience-example.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/resilience-example.jar"]
