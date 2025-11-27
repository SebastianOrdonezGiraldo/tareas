# Guía de Docker para Tareas App

## Requisitos previos
- Docker instalado
- Docker Compose instalado

## Opción 1: Usar Docker Compose (Recomendado)

### Iniciar la aplicación con MySQL
```bash
docker-compose up -d
```

### Ver los logs
```bash
docker-compose logs -f app
```

### Detener la aplicación
```bash
docker-compose down
```

### Detener y eliminar volúmenes (borra la base de datos)
```bash
docker-compose down -v
```

## Opción 2: Usar Docker sin Compose

### Construir el JAR localmente primero
```bash
./mvnw clean package
```

### Construir la imagen Docker
```bash
docker build -t tareas-app .
```

### Ejecutar con variables de entorno
```bash
docker run -p 8080:8080 \
  -e DB_URL="jdbc:mysql://host.docker.internal:3306/tareas_db" \
  -e DB_USERR_NAME="tu_usuario" \
  -e DB_PASSWORD="tu_password" \
  tareas-app
```

## Acceder a la aplicación
Una vez iniciada, la aplicación estará disponible en:
- **URL:** http://localhost:8080

## Verificar el estado de los contenedores
```bash
docker-compose ps
```

## Conectarse a MySQL
```bash
docker exec -it tareas-mysql mysql -u tareas_user -p
# Password: tareas_password
```

## Notas de seguridad
⚠️ **IMPORTANTE:** Las contraseñas en `docker-compose.yml` son para desarrollo. En producción:
- Usa variables de entorno
- Usa Docker secrets
- Nunca subas credenciales al repositorio
