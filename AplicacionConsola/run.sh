#!/bin/bash
JAR="lib/mysql-connector-j-8_2_0.jar"
BIN_DIR="bin"

echo ""
echo "  *** BiblioTerm - Compilando..."
echo ""

mkdir -p "$BIN_DIR"

javac -cp "$JAR" -sourcepath src -d "$BIN_DIR" \
    src/biblioteca/BaseDeDatos.java \
    src/biblioteca/Libro.java \
    src/biblioteca/Prestamo.java \
    src/biblioteca/Terminal.java \
    src/biblioteca/LibroDAO.java \
    src/biblioteca/PrestamoDAO.java \
    src/biblioteca/App.java

if [ $? -ne 0 ]; then
    echo "  [ERROR] Fallo la compilacion."
    exit 1
fi

echo "  Compilado correctamente. Iniciando..."
echo ""

java -cp "$BIN_DIR:$JAR" biblioteca.App