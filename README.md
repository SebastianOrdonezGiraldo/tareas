# 📋 Sistema de Gestión de Tareas

Aplicación web desarrollada con **Spring Boot** y **MySQL** para la gestión de tareas, completamente dockerizada y lista para producción.

## 🚀 Características

- ✅ API REST completa con operaciones CRUD
- ✅ Persistencia de datos con MySQL 8.0
- ✅ Arquitectura de contenedores con Docker
- ✅ Interfaz visual de base de datos con Adminer
- ✅ Validación de datos y manejo de errores
- ✅ Documentación completa de endpoints

## 🛠️ Tecnologías

- **Backend:** Spring Boot 3.4.12
- **Base de datos:** MySQL 8.0
- **ORM:** Spring Data JPA / Hibernate
- **Contenedores:** Docker & Docker Compose
- **Java:** 17
- **Build Tool:** Maven
- **Gestor DB:** Adminer

## 📋 Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- [Docker Desktop](https://www.docker.com/products/docker-desktop) (versión 20.10 o superior)
- [Docker Compose](https://docs.docker.com/compose/install/) (incluido en Docker Desktop)
- Git (opcional, para clonar el repositorio)

## 🚀 Inicio Rápido

### 1. Clonar el repositorio

```bash
git clone https://github.com/SebastianOrdonezGiraldo/tareas.git
cd tareas
```

### 2. Levantar los contenedores

```bash
docker-compose up -d
```

Este comando descargará las imágenes necesarias, construirá la aplicación y levantará todos los servicios.

### 3. Verificar que todo esté corriendo

```bash
docker-compose ps
```

Deberías ver 3 contenedores corriendo:
- `tareas-app` (Spring Boot)
- `tareas-mysql` (MySQL)
- `tareas-adminer` (Adminer)

### 4. Acceder a la aplicación

- **API REST:** http://localhost:8080/api/tareas
- **Adminer (Gestor DB):** http://localhost:8081

## 📡 Endpoints de la API

### Listar todas las tareas
```http
GET http://localhost:8080/api/tareas
```

### Crear una tarea
```http
POST http://localhost:8080/api/tareas
Content-Type: application/json

{
  "titulo": "Mi tarea",
  "descripcion": "Descripción de la tarea",
  "completada": false
}
```

### Obtener una tarea por ID
```http
GET http://localhost:8080/api/tareas/{id}
```

### Actualizar una tarea
```http
PUT http://localhost:8080/api/tareas/{id}
Content-Type: application/json

{
  "titulo": "Tarea actualizada",
  "descripcion": "Nueva descripción",
  "completada": true
}
```

### Eliminar una tarea
```http
DELETE http://localhost:8080/api/tareas/{id}
```

## 🗄️ Acceso a la Base de Datos

### Adminer (Interfaz Web)

1. Abre http://localhost:8081
2. Completa los datos de conexión:
   - **Sistema:** MySQL
   - **Servidor:** `mysql`
   - **Usuario:** `tareas_user`
   - **Contraseña:** `tareas_password`
   - **Base de datos:** `tareas_db`

### Cliente MySQL (Terminal)

```bash
docker exec -it tareas-mysql mysql -u tareas_user -ptareas_password tareas_db
```

### Desde herramientas externas (MySQL Workbench, DBeaver, etc.)

- **Host:** `localhost`
- **Puerto:** `3308`
- **Usuario:** `tareas_user`
- **Contraseña:** `tareas_password`
- **Base de datos:** `tareas_db`

## 📦 Estructura del Proyecto

```
tareas/
├── src/
│   ├── main/
│   │   ├── java/com/example/tareas/
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositorios
│   │   │   ├── service/         # Lógica de negocio
│   │   │   ├── exception/       # Manejo de excepciones
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   └── console/         # Menú de consola
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Tests unitarios
├── Dockerfile                   # Construcción de la imagen
├── docker-compose.yml           # Orquestación de contenedores
├── pom.xml                      # Dependencias Maven
└── README.md
```

## 🧪 Pruebas con Postman

Importa la colección de Postman incluida en el proyecto para probar todos los endpoints:

1. Abre Postman
2. Click en **Import**
3. Selecciona el archivo `Tareas_API.postman_collection.json`
4. Ejecuta los requests en orden

## 🔧 Comandos Útiles

### Ver logs de la aplicación
```bash
docker-compose logs -f app
```

### Ver logs de MySQL
```bash
docker-compose logs -f mysql
```

### Reiniciar la aplicación
```bash
docker-compose restart app
```

### Detener todos los servicios
```bash
docker-compose down
```

### Detener y eliminar datos (⚠️ borra la base de datos)
```bash
docker-compose down -v
```

### Reconstruir la aplicación
```bash
docker-compose build --no-cache app
docker-compose up -d
```

## 🐛 Solución de Problemas

### El puerto 3306 está ocupado
Cambia el puerto en `docker-compose.yml`:
```yaml
ports:
  - "3308:3306"  # Usa 3308 en lugar de 3306
```

### La aplicación no se conecta a MySQL
Verifica que MySQL esté listo:
```bash
docker-compose logs mysql | grep "ready for connections"
```

### Reconstruir desde cero
```bash
docker-compose down -v
docker-compose build --no-cache
docker-compose up -d
```

## 📊 Arquitectura

```
┌─────────────────────────────────────┐
│   Cliente (Navegador/Postman)      │
└─────────────┬───────────────────────┘
              │
              ↓
┌─────────────────────────────────────┐
│   API REST (Spring Boot)            │
│   Puerto: 8080                      │
│   - Controladores                   │
│   - Servicios                       │
│   - Validaciones                    │
└─────────────┬───────────────────────┘
              │
              ↓
┌─────────────────────────────────────┐
│   MySQL 8.0                         │
│   Puerto: 3308                      │
│   Base de datos: tareas_db          │
│   Volumen: mysql_data               │
└─────────────┬───────────────────────┘
              │
              ↓
┌─────────────────────────────────────┐
│   Adminer (Gestor Visual)           │
│   Puerto: 8081                      │
└─────────────────────────────────────┘
```

## 🔐 Configuración de Seguridad

**⚠️ IMPORTANTE:** Las credenciales incluidas en este proyecto son para desarrollo local únicamente. 

Para producción, debes:
- Usar variables de entorno seguras
- Cambiar las contraseñas por defecto
- Implementar autenticación JWT
- Configurar HTTPS/SSL
- Usar secretos de Docker/Kubernetes

## 📝 Variables de Entorno

El proyecto usa las siguientes variables de entorno:

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `DB_URL` | URL de conexión a MySQL | `jdbc:mysql://mysql:3306/tareas_db` |
| `DB_USER_NAME` | Usuario de MySQL | `tareas_user` |
| `DB_PASSWORD` | Contraseña de MySQL | `tareas_password` |
| `SPRING_PROFILES_ACTIVE` | Perfil de Spring | `docker` |

## 🤝 Contribuir

Las contribuciones son bienvenidas. Para cambios importantes:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👤 Autor

**Sebastian Ordoñez Giraldo**

- GitHub: [@SebastianOrdonezGiraldo](https://github.com/SebastianOrdonezGiraldo)

## 🙏 Agradecimientos

- Spring Boot Team
- MySQL Community
- Docker Community
- Adminer Team

---

⭐️ Si este proyecto te fue útil, no olvides darle una estrella en GitHub!