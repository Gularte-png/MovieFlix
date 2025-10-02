FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre
LABEL maintainer="andresacon"
WORKDIR /app

#COPY --from=<nome_do_estagio> <caminho_de_origem> <caminho_de_destino>
COPY --from=builder /app/target/movieflix-0.0.1-SNAPSHOT.jar /app/movieflix-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/app/movieflix-0.0.1.jar"]


