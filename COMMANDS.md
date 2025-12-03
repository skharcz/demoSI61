# 🛠️ Comandos Útiles para Render

## 📦 Preparación Local

### Inicializar Git (Primera vez)
```powershell
# Ejecutar el script de inicialización
.\init-git.ps1
```

### O manualmente:
```bash
git init
git add .
git commit -m "Initial commit - Ready for Render deployment"
```

### Conectar con GitHub
```bash
# Crear repositorio en GitHub primero, luego:
git remote add origin https://github.com/TU_USUARIO/TU_REPO.git
git branch -M main
git push -u origin main
```

---

## 🧪 Pruebas Locales Antes de Desplegar

### Compilar el proyecto
```bash
./mvnw clean install
```

### Ejecutar localmente
```bash
./mvnw spring-boot:run
```

### Probar que el JAR se genera correctamente
```bash
./mvnw clean package
java -jar target/demoSI61-0.0.1-SNAPSHOT.jar
```

---

## 🚀 Actualizar Aplicación en Render

### Después de hacer cambios:
```bash
git add .
git commit -m "Descripción de los cambios"
git push origin main
```

**Render detectará el push automáticamente y redesplegar la aplicación.**

---

## 🔍 Debugging en Render

### Ver logs en tiempo real (desde Render Dashboard)
1. Ve a tu Web Service
2. Click en "Logs"
3. Selecciona "Live" para ver en tiempo real

### Forzar redespliegue
1. Ve a tu Web Service
2. Click en "Manual Deploy"
3. Selecciona "Clear build cache & deploy"

---

## 🧪 Probar la API Desplegada

### Verificar que está viva
```bash
curl https://tu-app.onrender.com/swagger-ui.html
```

### Login (obtener JWT)
```bash
curl -X POST https://tu-app.onrender.com/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### Guardar el token en variable (Linux/Mac)
```bash
TOKEN=$(curl -s -X POST https://tu-app.onrender.com/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}' \
  | jq -r '.jwttoken')

echo $TOKEN
```

### Guardar el token en variable (PowerShell)
```powershell
$response = Invoke-RestMethod -Uri "https://tu-app.onrender.com/login" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"username":"admin","password":"admin123"}'

$token = $response.jwttoken
Write-Host $token
```

### Listar dispositivos (con token)
```bash
curl -X GET https://tu-app.onrender.com/dispositivos \
  -H "Authorization: Bearer $TOKEN"
```

### Crear un dispositivo
```bash
curl -X POST https://tu-app.onrender.com/dispositivos \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nameDevice": "Smart TV",
    "typeDevice": "Television",
    "priceDevice": 599.99,
    "purchaseDateDevice": "2024-01-15",
    "numberDeviceMaintenance": 0,
    "room": {
      "idRoom": 1
    }
  }'
```

### Obtener reportes
```bash
# Cantidad de dispositivos por habitación
curl -X GET https://tu-app.onrender.com/dispositivos/cantidades \
  -H "Authorization: Bearer $TOKEN"

# Suma de precios por habitación
curl -X GET https://tu-app.onrender.com/dispositivos/sumas \
  -H "Authorization: Bearer $TOKEN"
```

---

## 🗄️ Gestión de Base de Datos

### Conectarse a PostgreSQL de Render (desde local)

1. En Render Dashboard → PostgreSQL → "Info"
2. Copia la "External Database URL"
3. Usa un cliente como pgAdmin o DBeaver

**Connection String:**
```
postgresql://user:password@dpg-xxxxx.oregon-postgres.render.com:5432/datos1
```

### Usando psql (línea de comandos)
```bash
psql "postgresql://user:password@dpg-xxxxx.oregon-postgres.render.com:5432/datos1"
```

### Backup de la base de datos
```bash
pg_dump "postgresql://user:password@dpg-xxxxx.oregon-postgres.render.com:5432/datos1" > backup.sql
```

### Restaurar backup
```bash
psql "postgresql://user:password@dpg-xxxxx.oregon-postgres.render.com:5432/datos1" < backup.sql
```

---

## 🔐 Generar JWT Secret Seguro

### Opción 1: Online
Visita: https://www.grc.com/passwords.htm

### Opción 2: OpenSSL
```bash
openssl rand -base64 32
```

### Opción 3: PowerShell
```powershell
$bytes = New-Object byte[] 32
[Security.Cryptography.RNGCryptoServiceProvider]::Create().GetBytes($bytes)
[Convert]::ToBase64String($bytes)
```

### Opción 4: Node.js
```bash
node -e "console.log(require('crypto').randomBytes(32).toString('base64'))"
```

---

## 📊 Monitoreo y Métricas

### Ver estado de los servicios
```bash
# Instala render-cli (opcional)
npm install -g render-cli

# Login
render login

# Ver servicios
render services list

# Ver logs
render logs -s tu-servicio-id
```

---

## 🔄 Rollback (Volver a versión anterior)

1. Ve a Render Dashboard → Web Service
2. Click en "Events"
3. Encuentra el deploy exitoso anterior
4. Click en "Rollback to this deploy"

---

## 🧹 Limpieza y Mantenimiento

### Limpiar build cache local
```bash
./mvnw clean
```

### Limpiar build cache en Render
1. Manual Deploy → "Clear build cache & deploy"

### Eliminar target local
```bash
rm -rf target/
```

---

## 📝 Variables de Entorno

### Ver variables actuales (Render Dashboard)
1. Web Service → Environment
2. Aquí puedes agregar/editar/eliminar variables

### Variables importantes:
```
SPRING_PROFILES_ACTIVE=production
DATABASE_URL=postgresql://...
JWT_SECRET=tu_secreto_seguro
PORT=<asignado automáticamente por Render>
```

---

## 🐛 Troubleshooting Rápido

### La app no inicia
```bash
# Verifica logs en Render
# Busca errores como:
# - "Connection refused" → Problema con DATABASE_URL
# - "Port already in use" → Verifica Start Command
# - "ClassNotFoundException" → Problema en build
```

### Build falla
```bash
# Prueba localmente:
./mvnw clean install -X  # -X para modo verbose

# Si funciona local pero falla en Render:
# - Verifica que mvnw tenga permisos
# - Revisa que todas las dependencias estén en pom.xml
```

### Base de datos no conecta
```bash
# Verifica:
# 1. DATABASE_URL usa "Internal" URL (no External)
# 2. Web Service y DB están en la misma región
# 3. DB está en estado "Available"
```

---

## 📚 Recursos Adicionales

- **Render Docs**: https://render.com/docs
- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **PostgreSQL Docs**: https://www.postgresql.org/docs/

---

## 💡 Tips Pro

1. **Usa Swagger UI** para probar endpoints fácilmente
2. **Guarda los tokens JWT** en variables de entorno locales
3. **Haz commits frecuentes** con mensajes descriptivos
4. **Monitorea los logs** después de cada deploy
5. **Documenta cambios** en el README
6. **Haz backup** de la base de datos regularmente
7. **Usa branches** para features nuevas antes de merge a main

---

**¿Necesitas más ayuda?** Revisa `DEPLOY_GUIDE.md` para instrucciones detalladas.
