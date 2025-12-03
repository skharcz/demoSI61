# 🚀 DemoSI61 - Spring Boot API

Sistema de gestión de dispositivos IoT con autenticación JWT desarrollado con Spring Boot 3.3.3 y Java 21.

## 📋 Características

- ✅ API REST completa
- ✅ Autenticación JWT
- ✅ Base de datos PostgreSQL
- ✅ Documentación Swagger/OpenAPI
- ✅ Gestión de dispositivos y habitaciones
- ✅ Sistema de usuarios y roles
- ✅ Reportes y consultas personalizadas

## 🛠️ Stack Tecnológico

- **Java**: 21
- **Spring Boot**: 3.3.3
- **PostgreSQL**: Database
- **Spring Security**: Autenticación y autorización
- **JWT**: Tokens de autenticación
- **Maven**: Gestión de dependencias
- **SpringDoc**: Documentación API

## 🚀 Despliegue en Render

### Opción 1: Usando render.yaml (Recomendado)

1. **Crear cuenta en Render**: [https://render.com](https://render.com)

2. **Conectar repositorio Git**:
   - Sube tu proyecto a GitHub/GitLab
   - En Render, selecciona "New +" → "Blueprint"
   - Conecta tu repositorio

3. **Render detectará automáticamente** el archivo `render.yaml` y creará:
   - Base de datos PostgreSQL
   - Web Service con la aplicación

### Opción 2: Configuración Manual

#### Paso 1: Crear Base de Datos PostgreSQL

1. En Render Dashboard, click en "New +" → "PostgreSQL"
2. Configuración:
   - **Name**: `demosi61-db`
   - **Database**: `datos1`
   - **User**: `demosi61_user`
   - **Region**: Selecciona la más cercana
   - **Plan**: Free
3. Click "Create Database"
4. **Guarda la "Internal Database URL"** (la necesitarás después)

#### Paso 2: Crear Web Service

1. En Render Dashboard, click en "New +" → "Web Service"
2. Conecta tu repositorio Git
3. Configuración:
   - **Name**: `demosi61-api`
   - **Region**: La misma que la base de datos
   - **Branch**: `main` (o tu rama principal)
   - **Runtime**: `Java`
   - **Build Command**: `./mvnw clean install -DskipTests`
   - **Start Command**: `java -Dserver.port=$PORT -Dspring.profiles.active=production -jar target/demoSI61-0.0.1-SNAPSHOT.jar`
   - **Plan**: Free

4. **Variables de Entorno** (Environment Variables):
   ```
   SPRING_PROFILES_ACTIVE=production
   DATABASE_URL=<pega aquí la Internal Database URL de tu PostgreSQL>
   JWT_SECRET=<genera un secreto seguro, ej: tu_clave_secreta_muy_larga_123>
   ```

5. Click "Create Web Service"

#### Paso 3: Esperar el Despliegue

- Render compilará tu aplicación (puede tomar 5-10 minutos)
- Una vez completado, verás "Live" en verde
- Tu API estará disponible en: `https://demosi61-api.onrender.com`

## 📚 Documentación API

Una vez desplegado, accede a la documentación Swagger en:

```
https://tu-app.onrender.com/swagger-ui.html
```

## 🔐 Autenticación

### Login

**Endpoint**: `POST /login`

**Request**:
```json
{
  "username": "tu_usuario",
  "password": "tu_password"
}
```

**Response**:
```json
{
  "jwttoken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Usar el Token

En todas las peticiones subsecuentes, incluye el header:

```
Authorization: Bearer <tu_token_jwt>
```

## 📡 Endpoints Principales

### Dispositivos
- `GET /dispositivos` - Listar todos
- `POST /dispositivos` - Crear dispositivo
- `GET /dispositivos/{id}` - Obtener por ID
- `PUT /dispositivos` - Actualizar
- `DELETE /dispositivos/{id}` - Eliminar
- `GET /dispositivos/buscar?fecha=2024-01-01` - Buscar por fecha
- `GET /dispositivos/cantidades` - Reporte de cantidades por habitación
- `GET /dispositivos/sumas` - Reporte de sumas por habitación

### Habitaciones
- `GET /habitaciones` - Listar todas
- `POST /habitaciones` - Crear habitación
- `PUT /habitaciones` - Actualizar
- `DELETE /habitaciones/{id}` - Eliminar

### Usuarios
- `GET /usuarios` - Listar usuarios
- `POST /usuarios` - Crear usuario
- `PUT /usuarios` - Actualizar
- `DELETE /usuarios/{id}` - Eliminar

## 🧪 Desarrollo Local

### Requisitos

- Java 21
- PostgreSQL
- Maven

### Configuración

1. Crear base de datos:
```sql
CREATE DATABASE datos1;
```

2. Actualizar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost/datos1
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

3. Ejecutar:
```bash
./mvnw spring-boot:run
```

4. Acceder a:
- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`

## 🔧 Solución de Problemas en Render

### Error: "Application failed to start"

1. Revisa los logs en Render Dashboard
2. Verifica que `DATABASE_URL` esté configurada correctamente
3. Asegúrate de que la base de datos esté en estado "Available"

### Error: "Port already in use"

- Render asigna automáticamente el puerto mediante `$PORT`
- No cambies el `startCommand`

### Error de conexión a base de datos

- Usa la **Internal Database URL** (no la External)
- Formato: `postgresql://user:password@host:port/database`

### Logs lentos o timeout

- El plan Free de Render puede tardar en iniciar
- La primera petición puede ser lenta (cold start)

## 📝 Notas Importantes

- **Plan Free de Render**: 
  - La aplicación se "duerme" después de 15 minutos de inactividad
  - El primer request después de dormir puede tardar 30-60 segundos
  - 750 horas gratis al mes

- **Base de datos Free**:
  - Expira después de 90 días
  - Máximo 1GB de almacenamiento

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es parte del curso SI61 de la UPC.

## 👥 Autores

- Desarrollado para el curso SI61 - UPC

---

**¿Necesitas ayuda?** Revisa la [documentación de Render](https://render.com/docs) o contacta al equipo de desarrollo.
