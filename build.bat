@echo off
title Compilando Plataforma Gamificada
color 0A

echo ============================================
echo   🚀 Compilando o projeto, aguarde...
echo ============================================

javac -d out src\app\*.java ^
src\conquistas\*.java ^
src\conquistas_reestruturadas\*.java ^
src\controller\*.java ^
src\desafios\*.java ^
src\historico\*.java ^
src\infra\*.java ^
src\model\*.java ^
src\repository\*.java ^
src\service\*.java ^
src\usuarios\*.java ^
src\view\*.java

if %errorlevel% neq 0 (
    echo.
    echo ❌ Erro na compilação! Verifique os arquivos.
    pause
    exit /b
)

echo.
echo ✅ Compilação concluída com sucesso!
echo ============================================
echo   🧠 Iniciando a aplicação...
echo ============================================

java -cp out app.MainConsole

echo.
echo 🏁 Execução finalizada.
pause
