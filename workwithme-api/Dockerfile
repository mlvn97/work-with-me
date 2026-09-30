FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package && cp target/*.jar app.jar

FROM eclipse-temurin:21-jre

WORKDIR /app
RUN groupadd --system app && useradd --system --gid app app
COPY --from=build --chown=app:app /workspace/app.jar app.jar

USER app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
