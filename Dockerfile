FROM maven:3.9-eclipse-temurin-26 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -DskipTests clean package

FROM eclipse-temurin:26-jdk

WORKDIR /app

COPY --from=build /app/target/support-crm-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]