# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por �ltimo el inicio y configuraci�n de la aplicaci�n.

Lee el art�culo [Clean Architecture � Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el m�dulo m�s interno de la arquitectura, pertenece a la capa del dominio y encapsula la l�gica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este m�dulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define l�gica de aplicaci�n y reacciona a las invocaciones desde el m�dulo de entry points, orquestando los flujos hacia el m�dulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no est�n arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
gen�ricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patr�n de dise�o [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicaci�n o el inicio de los flujos de negocio.

## Application

Este m�dulo es el m�s externo de la arquitectura, es el encargado de ensamblar los distintos m�dulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma autom�tica, inyectando en �stos instancias concretas de las dependencias declaradas. Adem�s inicia la aplicaci�n (es el �nico m�dulo del proyecto donde encontraremos la funci�n �public static void main(String[] args)�.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

# ms-coupon-solver API

ms-coupon-solver es un servicio diseñado para maximizar el uso de un cupón dado un presupuesto y una lista de ítems disponibles. Proporciona una API REST para calcular qué ítems seleccionar para gastar el monto del cupón de manera óptima.

---

## 📋 Requisitos Previos

1. **Java**: Versión 17 o superior.
2. **Gradle**: Versión 8.8 o superior.
3. **Spring Boot**: Configurado como parte del proyecto.
4. **API MercadoLibre**: Se utiliza la API de MercadoLibre para obtener precios de los ítems.

---

## 🚀 Ejecución Local

### 1. Clonar el Repositorio

```bash
git clone https://github.com/carlinhos-coder/ms-coupons-solver.git
cd ms-coupons-solver
 ```
### 2. Instalar Dependencias

```bash
gradle build
``` 

### 3. Ejecutar la Aplicación

```bash
gradle bootRun
```

### 4. Acceder a la API
Desde Postman, acceder a la siguiente URL:

```bash
http://localhost:8080/coupons/
```
con el siguiente body:

```bash
{
    "item_ids": ["MLA1", "MLA2", "MLA3", "MLA4", "MLA5"],
    "amount": 1000
}
```
y los headers:

```bash
Content-Type: application/json
```
debe retornar existos status 200 OK:

```bash
{
    "item_ids": ["MLA1", "MLA2", "MLA3"],
    "total": 1000
}
```
debe retornar no existos status 404 NOT FOUND:

```bash
{
    "message": "404-NOT_FOUND."
}
```

## 🚀 Ejecución Desplegada

### 1. Acceder a la API
Desde Postman, acceder a la siguiente URL:

```bash
http://54.175.28.12:8080/coupons/
```
con el siguiente body:

```bash
{
    "item_ids": ["MLA1", "MLA2", "MLA3"],
    "total": 1000
}

```
y los headers:

```bash
Content-Type: application/json
```
debe retornar:

```bash
{
    "item_ids": ["MLA1", "MLA2", "MLA3"],
    "total": 1000
}
```
