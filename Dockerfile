# Usar una imagen base de Java 17
FROM amazoncorretto:17

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por Gradle al contenedor
COPY build/libs/ms-coupons-solve.jar app.jar

# Exponer el puerto de la API (ajústalo si no es 8080)
EXPOSE 8080

# Comando para ejecutar la API
ENTRYPOINT ["java", "-jar", "app.jar"]
