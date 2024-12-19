# Usar una imagen base de Java 17
FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080
RUN mkdir /app
RUN mkdir /config


COPY ./applications/app-service/build/libs/ms-coupons-solve.jar /app/app.jar
COPY ./applications/app-service/build/resources/main/application.yaml /config/application.yaml

# Comando para ejecutar la API
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
