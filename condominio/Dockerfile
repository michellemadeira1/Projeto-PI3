# Etapa 1: Build com Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o .jar (sem rodar os testes)
RUN mvn clean package -DskipTests

# Etapa 2: Imagem de execução leve com Java 21
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o .jar gerado na etapa de build
COPY --from=build /app/target/condominio-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação Spring Boot
EXPOSE 8080

# Comando para executar o .jar
ENTRYPOINT ["java", "-jar", "app.jar"]
