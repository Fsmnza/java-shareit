# Stage 1: Build
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app
COPY ../pom.xml ./pom.xml
COPY ../server ./server
COPY ../gateway ./gateway
RUN mvn clean package -pl server -am -DskipTests -B

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/server/target/*.jar ./server.jar

CMD ["java", "-jar", "server.jar"]
