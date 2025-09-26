#!/bin/bash
javac -d out src/app/*.java \
src/conquistas/*.java \
src/conquistas_reestruturadas/*.java \
src/controller/*.java \
src/desafios/*.java \
src/historico/*.java \
src/infra/*.java \
src/model/*.java \
src/repository/*.java \
src/service/*.java \
src/usuarios/*.java \
src/relatorios/*.java \
src/view/*.java

java -cp out app.MainConsole
