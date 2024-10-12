# Etapa 1: Construcción del proyecto con Maven
FROM maven:3.9.9-eclipse-temurin-17-focal AS build
WORKDIR /app
COPY . . 
RUN mvn clean install -DskipTests && mvn clean package -DskipTests

# Etapa 2: Imagen final con OpenJDK para ejecutar la aplicación
FROM openjdk:17-buster
WORKDIR /app
ENV DOCKERIZE_VERSION=v0.8.0
RUN apt-get update \
    && apt-get install -y wget \
    && wget -O - https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz | tar xzf - -C /usr/local/bin \
    && apt-get autoremove -yqq --purge wget && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/microservicio-orquestador-biblioteca-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["dockerize", "-wait", "tcp://mysql_db:3306", "-timeout", "60s", "java", "-jar", "/app/app.jar"]
