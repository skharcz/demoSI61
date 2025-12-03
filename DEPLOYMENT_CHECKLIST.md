# ✅ Checklist de Despliegue en Render

## 📋 Antes de Empezar

- [ ] Tengo una cuenta en GitHub (https://github.com)
- [ ] Tengo una cuenta en Render (https://render.com)
- [ ] He revisado que mi proyecto compile localmente
- [ ] He leído el archivo `DEPLOY_GUIDE.md`

## 🔧 Preparación del Proyecto

- [ ] Archivos de configuración creados:
  - [ ] `render.yaml` ✅
  - [ ] `system.properties` ✅
  - [ ] `Procfile` ✅
  - [ ] `build.sh` ✅
  - [ ] `application-production.properties` ✅
  - [ ] `.env.example` ✅
  - [ ] `README.md` ✅
  - [ ] `DEPLOY_GUIDE.md` ✅

- [ ] `pom.xml` actualizado con configuración de build ✅
- [ ] `.gitignore` actualizado ✅

## 📦 Git y GitHub

- [ ] Repositorio Git inicializado
  ```powershell
  # Ejecuta este script:
  .\init-git.ps1
  ```

- [ ] Repositorio creado en GitHub
  - Nombre sugerido: `demoSI61` o `demosi61-api`
  - Visibilidad: Público o Privado (ambos funcionan)

- [ ] Código subido a GitHub
  ```bash
  git remote add origin https://github.com/TU_USUARIO/TU_REPO.git
  git branch -M main
  git push -u origin main
  ```

## 🎯 Despliegue en Render

### Opción A: Blueprint (Automático) - RECOMENDADO

- [ ] En Render Dashboard → "New +" → "Blueprint"
- [ ] Repositorio conectado
- [ ] Render detectó `render.yaml`
- [ ] Click en "Apply"
- [ ] Variable `JWT_SECRET` agregada manualmente en Environment
- [ ] Servicios creados:
  - [ ] `demosi61-db` (PostgreSQL)
  - [ ] `demosi61-api` (Web Service)

### Opción B: Manual

#### Base de Datos
- [ ] PostgreSQL creado en Render
  - [ ] Name: `demosi61-db`
  - [ ] Database: `datos1`
  - [ ] Plan: Free
  - [ ] Estado: "Available"
- [ ] Internal Database URL copiada

#### Web Service
- [ ] Web Service creado
  - [ ] Name: `demosi61-api`
  - [ ] Runtime: Java
  - [ ] Build Command: `./mvnw clean install -DskipTests`
  - [ ] Start Command: `java -Dserver.port=$PORT -Dspring.profiles.active=production -jar target/demoSI61-0.0.1-SNAPSHOT.jar`
  - [ ] Plan: Free
  - [ ] Región: Misma que la base de datos

- [ ] Variables de entorno configuradas:
  - [ ] `SPRING_PROFILES_ACTIVE=production`
  - [ ] `DATABASE_URL=<Internal Database URL>`
  - [ ] `JWT_SECRET=<clave segura generada>`

## 🧪 Verificación Post-Despliegue

- [ ] Build completado exitosamente (sin errores en logs)
- [ ] Estado del servicio: "Live" (verde)
- [ ] URL de la aplicación accesible
- [ ] Swagger UI funciona: `https://tu-app.onrender.com/swagger-ui.html`

### Pruebas Funcionales

- [ ] Endpoint de login responde:
  ```bash
  curl -X POST https://tu-app.onrender.com/login \
    -H "Content-Type: application/json" \
    -d '{"username":"test","password":"test123"}'
  ```

- [ ] Se obtiene token JWT en la respuesta

- [ ] Endpoints protegidos funcionan con token:
  ```bash
  curl -X GET https://tu-app.onrender.com/dispositivos \
    -H "Authorization: Bearer TU_TOKEN"
  ```

- [ ] Base de datos conectada correctamente (sin errores de conexión en logs)

## 📊 Monitoreo

- [ ] Logs revisados (sin errores críticos)
- [ ] Tiempo de respuesta aceptable
- [ ] Primera petición después de inactividad tarda ~30-60s (normal en plan Free)

## 📝 Documentación

- [ ] URL de producción documentada
- [ ] Credenciales de acceso guardadas de forma segura
- [ ] Variables de entorno respaldadas
- [ ] README actualizado con URL de producción

## 🎉 Finalización

- [ ] Aplicación desplegada y funcionando
- [ ] Equipo notificado de la URL de producción
- [ ] Documentación compartida
- [ ] Backup de configuración realizado

---

## 🆘 Si algo falla...

1. **Revisa los logs** en Render Dashboard
2. **Consulta** `DEPLOY_GUIDE.md` sección "Solución de Problemas"
3. **Verifica** que todas las variables de entorno estén correctas
4. **Asegúrate** de que la base de datos esté en la misma región
5. **Espera** al menos 5-10 minutos para el primer despliegue

---

## 📚 Recursos

- [DEPLOY_GUIDE.md](./DEPLOY_GUIDE.md) - Guía detallada paso a paso
- [README.md](./README.md) - Documentación del proyecto
- [Render Docs](https://render.com/docs) - Documentación oficial
- [.env.example](./.env.example) - Ejemplo de variables de entorno

---

**Última actualización**: 2025-12-02
**Versión**: 1.0
