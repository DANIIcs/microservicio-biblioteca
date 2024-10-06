# Etapa 1: Construcción del proyecto con Maven
FROM maven:3.9.9-eclipse-temurin-17-focal AS build
WORKDIR /app
COPY . . 
RUN mvn clean install -DskipTests && mvn clean package -DskipTests

# Etapa 2: Imagen final con OpenJDK para ejecutar la aplicación
FROM openjdk:17-buster
WORKDIR /app
COPY --from=build /app/target/microservicio-orquestador-biblioteca-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
