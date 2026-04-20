# Microservicio de Categorías

Este es el microservicio encargado de gestionar las categorías del sistema.

## Descripción

El servicio de Categorías proporciona APIs para:
- Registrar nuevas categorías
- Consultar categorías existentes
- Actualizar categorías
- Eliminar categorías
- Obtener categorías activas
- Buscar categorías por nombre

## Puertos

- **Desarrollo (Dev)**: 8094
- **Producción**: 8094

## Configuración

### Variables de entorno

- `SPRING_PROFILES_ACTIVE`: Perfil activo (dev o prod)
- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`: URL del servidor Eureka
- `SPRING_CONFIG_IMPORT`: URL del servidor de configuración
- `SPRING_DATASOURCE_URL`: URL de la base de datos (solo prod)
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos (solo prod)
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos (solo prod)

## Ejecutar en Desarrollo

```bash
./mvnw spring-boot:run
```

## Docker

### Imagen de desarrollo
```bash
docker-compose -f docker-compose-dev.yml up
```

### Imagen de producción
```bash
docker-compose up
```

## Endpoints

### GET /api/categorias
Obtiene todas las categorías

### GET /api/categorias/{id}
Obtiene una categoría por ID

### GET /api/categorias/nombre/{nombre}
Obtiene una categoría por nombre

### GET /api/categorias/activas
Obtiene las categorías activas

### POST /api/categorias
Crea una nueva categoría

### PUT /api/categorias/{id}
Actualiza una categoría

### DELETE /api/categorias/{id}
Elimina una categoría

## Base de datos

La base de datos se migra automáticamente usando Flyway.
