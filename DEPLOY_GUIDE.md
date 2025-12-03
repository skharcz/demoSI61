# 🚀 Guía Paso a Paso: Despliegue en Render

## ✅ Pre-requisitos

- [ ] Cuenta en GitHub (o GitLab/Bitbucket)
- [ ] Cuenta en Render (gratis): https://render.com
- [ ] Tu proyecto subido a un repositorio Git

---

## 📋 OPCIÓN 1: Despliegue Automático con Blueprint (MÁS FÁCIL)

### Paso 1: Subir el proyecto a GitHub

```bash
# Si aún no has inicializado Git
git init
git add .
git commit -m "Initial commit - Ready for Render deployment"

# Crear repositorio en GitHub y luego:
git remote add origin https://github.com/TU_USUARIO/demoSI61.git
git branch -M main
git push -u origin main
```

### Paso 2: Conectar con Render

1. Ve a https://render.com y crea una cuenta (puedes usar GitHub)
2. En el Dashboard, click en **"New +"** → **"Blueprint"**
3. Conecta tu cuenta de GitHub/GitLab
4. Selecciona el repositorio **demoSI61**
5. Render detectará automáticamente el archivo `render.yaml`
6. Click en **"Apply"**

### Paso 3: Configurar Variables de Entorno

Render creará automáticamente:
- ✅ Base de datos PostgreSQL (`demosi61-db`)
- ✅ Web Service (`demosi61-api`)

**IMPORTANTE**: Agrega esta variable de entorno manualmente:

1. Ve a tu Web Service → **Environment**
2. Agrega:
   ```
   Key: JWT_SECRET
   Value: TuClaveSecretaMuyLargaYSegura123456789
   ```
3. Click **"Save Changes"**

### Paso 4: ¡Listo! 🎉

- Render compilará tu aplicación (5-10 minutos)
- Una vez que veas **"Live"** en verde, tu API estará disponible
- URL: `https://demosi61-api.onrender.com`
- Swagger: `https://demosi61-api.onrender.com/swagger-ui.html`

---

## 📋 OPCIÓN 2: Despliegue Manual (Más Control)

### Paso 1: Crear Base de Datos PostgreSQL

1. En Render Dashboard → **"New +"** → **"PostgreSQL"**
2. Configuración:
   - **Name**: `demosi61-db`
   - **Database**: `datos1`
   - **User**: `demosi61_user` (o déjalo automático)
   - **Region**: Oregon (US West) o la más cercana
   - **PostgreSQL Version**: 16
   - **Plan**: **Free**
3. Click **"Create Database"**
4. **IMPORTANTE**: Copia y guarda la **"Internal Database URL"**
   - Se ve así: `postgresql://user:password@dpg-xxxxx/datos1`
   - La necesitarás en el siguiente paso

### Paso 2: Crear Web Service

1. En Render Dashboard → **"New +"** → **"Web Service"**
2. Conecta tu repositorio Git (GitHub/GitLab)
3. Selecciona el repositorio **demoSI61**
4. Configuración:

   **Basic:**
   - **Name**: `demosi61-api`
   - **Region**: **La misma que tu base de datos** (importante!)
   - **Branch**: `main`
   - **Root Directory**: (déjalo vacío)
   - **Runtime**: `Java`

   **Build & Deploy:**
   - **Build Command**: 
     ```bash
     ./mvnw clean install -DskipTests
     ```
   - **Start Command**:
     ```bash
     java -Dserver.port=$PORT -Dspring.profiles.active=production -jar target/demoSI61-0.0.1-SNAPSHOT.jar
     ```

   **Plan:**
   - Selecciona **"Free"**

5. Click **"Advanced"** para agregar variables de entorno

### Paso 3: Configurar Variables de Entorno

En la sección **Environment Variables**, agrega:

```
SPRING_PROFILES_ACTIVE = production

DATABASE_URL = postgresql://user:password@dpg-xxxxx/datos1
(Pega aquí la Internal Database URL que copiaste en el Paso 1)

JWT_SECRET = TuClaveSecretaMuyLargaYSegura123456789
(Genera una clave segura única)
```

### Paso 4: Crear el Servicio

1. Click **"Create Web Service"**
2. Render comenzará a compilar tu aplicación
3. Puedes ver los logs en tiempo real

### Paso 5: Verificar el Despliegue

**Durante el build verás:**
```
==> Downloading dependencies
==> Building application
==> Starting application
```

**Cuando esté listo:**
- Estado: **"Live"** (verde)
- URL disponible: `https://demosi61-api.onrender.com`

---

## 🧪 Probar tu API Desplegada

### 1. Verificar que está funcionando

Abre en tu navegador:
```
https://tu-app.onrender.com/swagger-ui.html
```

### 2. Probar el Login (con Postman o cURL)

```bash
curl -X POST https://tu-app.onrender.com/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### 3. Usar el Token JWT

```bash
curl -X GET https://tu-app.onrender.com/dispositivos \
  -H "Authorization: Bearer TU_TOKEN_JWT_AQUI"
```

---

## 🔧 Solución de Problemas

### ❌ Error: "Build failed"

**Causa**: Problemas con Maven o dependencias

**Solución**:
1. Verifica que `mvnw` tenga permisos de ejecución
2. En el repositorio, ejecuta localmente:
   ```bash
   ./mvnw clean install
   ```
3. Si funciona localmente, sube los cambios a Git

### ❌ Error: "Application failed to start"

**Causa**: Problema con la base de datos o configuración

**Solución**:
1. Ve a **Logs** en Render
2. Busca el error específico
3. Verifica que `DATABASE_URL` esté correcta
4. Asegúrate de que la base de datos esté en estado **"Available"**

### ❌ Error: "Connection refused" o "Connection timeout"

**Causa**: URL de base de datos incorrecta

**Solución**:
- Usa la **Internal Database URL** (no la External)
- Verifica que el Web Service y la Database estén en la **misma región**

### ❌ La aplicación está muy lenta

**Causa**: Plan Free - Cold Start

**Explicación**:
- El plan Free "duerme" después de 15 min de inactividad
- El primer request puede tardar 30-60 segundos en despertar
- Esto es normal en el plan gratuito

**Solución**:
- Espera pacientemente en el primer request
- Considera actualizar a un plan de pago si necesitas mejor rendimiento

### ❌ Error 404 en todos los endpoints

**Causa**: La aplicación no inició correctamente

**Solución**:
1. Revisa los logs completos
2. Verifica que el `Start Command` sea correcto
3. Asegúrate de que el puerto sea `$PORT` (variable de Render)

---

## 📊 Monitoreo y Mantenimiento

### Ver Logs en Tiempo Real

1. Ve a tu Web Service en Render
2. Click en **"Logs"**
3. Verás todos los logs de tu aplicación

### Reiniciar la Aplicación

1. Ve a tu Web Service
2. Click en **"Manual Deploy"** → **"Clear build cache & deploy"**

### Actualizar la Aplicación

```bash
# Haz cambios en tu código
git add .
git commit -m "Update: descripción de cambios"
git push origin main

# Render detectará el push y desplegará automáticamente
```

---

## 💰 Límites del Plan Free

| Recurso | Límite |
|---------|--------|
| **Web Services** | 750 horas/mes (suficiente para 1 app 24/7) |
| **PostgreSQL** | 90 días, luego expira |
| **Storage** | 1 GB |
| **RAM** | 512 MB |
| **Build Time** | 500 horas/mes |
| **Bandwidth** | 100 GB/mes |

**Nota**: Después de 90 días, necesitarás crear una nueva base de datos Free o actualizar a un plan de pago.

---

## 🎯 Checklist Final

Antes de considerar el despliegue completo:

- [ ] La aplicación muestra estado **"Live"** (verde)
- [ ] Swagger UI es accesible en `/swagger-ui.html`
- [ ] El endpoint `/login` responde correctamente
- [ ] Puedes autenticarte y obtener un JWT
- [ ] Los endpoints protegidos funcionan con el token
- [ ] La base de datos está en estado **"Available"**
- [ ] Las variables de entorno están configuradas
- [ ] Los logs no muestran errores críticos

---

## 📚 Recursos Adicionales

- [Documentación oficial de Render](https://render.com/docs)
- [Render Community Forum](https://community.render.com/)
- [Spring Boot on Render Guide](https://render.com/docs/deploy-spring-boot)

---

## 🆘 ¿Necesitas Ayuda?

Si tienes problemas:

1. **Revisa los logs** en Render Dashboard
2. **Verifica la configuración** de variables de entorno
3. **Compara** con esta guía paso a paso
4. **Busca el error** en Google o Stack Overflow
5. **Pregunta** en el Render Community Forum

---

**¡Éxito con tu despliegue! 🚀**
