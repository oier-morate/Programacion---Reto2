@echo off
set JAR=lib\mysql-connector-j-8.2.0.jar
set BIN_DIR=bin

echo.
echo   *** BiblioTerm - Compilando...
echo.

if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

javac -cp "%JAR%" -sourcepath src -d "%BIN_DIR%" ^
    src\biblioteca\BaseDeDatos.java ^
    src\biblioteca\Libro.java ^
    src\biblioteca\Prestamo.java ^
    src\biblioteca\Terminal.java ^
    src\biblioteca\LibroDAO.java ^
    src\biblioteca\PrestamoDAO.java ^
    src\biblioteca\App.java

if errorlevel 1 (
    echo.
    echo   [ERROR] Fallo la compilacion.
    pause
    exit /b 1
)

echo   Compilado correctamente. Iniciando...
echo.

java -cp "%BIN_DIR%;%JAR%" biblioteca.App
pause