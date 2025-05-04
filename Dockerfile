FROM openjdk:18-jdk-slim

WORKDIR /app

COPY target/microservicio_veterinario-0.0.1-SNAPSHOT.jar app.jar

COPY Wallet_QPCAE9W9T5T15F65 /app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]