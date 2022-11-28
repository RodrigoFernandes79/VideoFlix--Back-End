FROM maven:3.8.4-jdk-11-slim as build

COPY . .
RUN mvn clean package -DskipTests 

FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]