# Etapa 1: Construcción
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/tareas-0.0.1.jar app_tareas.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_tareas.jar"]