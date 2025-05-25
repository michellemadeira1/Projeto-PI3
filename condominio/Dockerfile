
# Etapa de construção
FROM maven:3.8.7-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar /app/springdeskgestaoescolar.jar
ENTRYPOINT ["java", "-jar", "/app/springdeskcondominio.jar"]