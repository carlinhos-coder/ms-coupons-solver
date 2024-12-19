# Usar una imagen base de Java 17
FROM amazoncorretto:17

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY build/libs/ms-coupons-solver.jar app.jar

# Exponer el puerto de la API (ajústalo según tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
