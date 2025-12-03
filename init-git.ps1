# Inicializar Git y preparar para Render

Write-Host "🚀 Preparando proyecto para despliegue en Render..." -ForegroundColor Cyan
Write-Host ""

# Inicializar Git
Write-Host "📦 Inicializando repositorio Git..." -ForegroundColor Yellow
git init

# Agregar todos los archivos
Write-Host "➕ Agregando archivos al repositorio..." -ForegroundColor Yellow
git add .

# Crear commit inicial
Write-Host "💾 Creando commit inicial..." -ForegroundColor Yellow
git commit -m "Initial commit - Ready for Render deployment"

Write-Host ""
Write-Host "✅ ¡Repositorio Git inicializado correctamente!" -ForegroundColor Green
Write-Host ""
Write-Host "📋 Próximos pasos:" -ForegroundColor Cyan
Write-Host "1. Crea un repositorio en GitHub: https://github.com/new" -ForegroundColor White
Write-Host "2. Ejecuta estos comandos (reemplaza TU_USUARIO y TU_REPO):" -ForegroundColor White
Write-Host ""
Write-Host "   git remote add origin https://github.com/TU_USUARIO/TU_REPO.git" -ForegroundColor Gray
Write-Host "   git branch -M main" -ForegroundColor Gray
Write-Host "   git push -u origin main" -ForegroundColor Gray
Write-Host ""
Write-Host "3. Ve a Render.com y sigue la guía en DEPLOY_GUIDE.md" -ForegroundColor White
Write-Host ""
Write-Host "📚 Lee el archivo DEPLOY_GUIDE.md para instrucciones detalladas" -ForegroundColor Magenta
