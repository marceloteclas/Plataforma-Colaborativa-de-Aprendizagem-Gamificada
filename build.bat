@echo off
title Compilando Plataforma Gamificada
color 0A

echo ============================================
echo   🚀 Compilando o projeto, aguarde...
echo ============================================

set CLASSPATH=libs\openpdf-3.0.0.jar

javac -cp %CLASSPATH% -d out src\app\*.java ^
src\conquistas_reestruturadas\*.java ^
src\controller\*.java ^
src\desafios\*.java ^
src\historico\*.java ^
src\infra\*.java ^
src\model\*.java ^
src\repository\*.java ^
src\relatorios\*.java ^
src\service\*.java ^
src\api_externa\*.java ^
src\adaptador\*.java ^
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

java -cp out;%CLASSPATH% app.MainConsole

echo.
echo 🏁 Execução finalizada.
pause
