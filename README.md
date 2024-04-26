# Microservicio de Productos (ms-products)

Este microservicio gestiona información de productos, permitiendo operaciones como obtener productos activos, crear nuevos productos y actualizar información de productos existentes.

## Requisitos Previos
Para ejecutar el microservicio, necesitarás:
- **Java 17** o superior
- **Apache Maven** para compilación y ejecución

## Collection Postman
- [PROJECT02.postman_collection.json](PROJECT02.postman_collection.json)
- [Desarrollo.postman_environment.json](Desarrollo.postman_environment.json)

## Configuración
El microservicio usa valores de entorno para la configuración. Antes de ejecutar el microservicio, asegúrate de definir estos valores clave:
- **DATABASE_URL**: URI para conectar a la base de datos.
- **JWT_SECRET**: Clave secreta para la validación de tokens JWT.
- **JWT_EXPIRATION**: Duración del token JWT en milisegundos.

La configuración puede hacerse usando archivos `application.properties` o variables de entorno.

## Instrucciones de Ejecución
Para ejecutar el microservicio localmente:
1. **Clona el repositorio**:
```
mvn clean package
```

2. **Ejecuta el microservicio**:
```
mvn spring-boot:run
```
