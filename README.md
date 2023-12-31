# AccountRestApp


# Proyecto de Gestión de Cuentas

Este proyecto es una aplicación de gestión de cuentas desarrollada utilizando Spring Boot.

## Descripción

La aplicación permite gestionar clientes, cuentas y movimientos bancarios. Proporciona una API RESTful para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en los diferentes modelos de datos.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.1
- Maven
- Spring Data JPA
- H2
- Swagger 

## Configuración del proyecto

1. Clona el repositorio del proyecto:

   ```
   git clone https://github.com/jocob44/AccountRestApp.git
   ```

2. Importa el proyecto en tu entorno de desarrollo preferido (por ejemplo, IntelliJ IDEA, Eclipse).

3. Ejecuta la aplicación utilizando el comando en el director AppCuentas:

   ```
   mvn spring-boot:run
   ```

4. La aplicación estará disponible en la siguiente URL: `http://localhost:8080`. Puedes acceder a ella desde tu navegador web o utilizar herramientas como cURL o Postman para interactuar con la API RESTful.

## Endpoints de la API

Todos los endpoints desarrollados se pueden consultar en la siguiente url luego de correr la aplicación:

http://localhost:8080/swagger-ui/index.html

A continuación se muestran algunos ejemplos de los endpoints disponibles en la API:

- `GET /clientes`: Obtiene la lista de todos los clientes.
- `GET /clientes/{id}`: Obtiene los detalles de un cliente específico.
- `POST /clientes`: Crea un nuevo cliente.
- `PUT /clientes/{id}`: Actualiza los datos de un cliente existente.
- `DELETE /clientes/{id}`: Elimina un cliente.

Puedes encontrar más detalles sobre los endpoints y su funcionamiento en la documentación de la API en la
url http://localhost:8080/swagger-ui/index.html.
