# Catálogo de Libros

Aplicación de consola desarrollada con Java 
y Spring Boot que permite buscar libros utilizando 
la API de Gutendex (Project Gutenberg) y almacenarlos 
en una base de datos PostgreSQL.

## Funcionalidades

El programa permite:

1. Buscar libros por título desde la API.
2. Guardar libros y autores en la base de datos.
3. Listar libros registrados.
4. Listar autores registrados.
5. Mostrar autores vivos en un año específico.
6. Listar libros por idioma.

## Configuración de base de datos

Antes de ejecutar el proyecto debes configurar las siguientes variables de entorno:

DB_URL=jdbc:postgresql://localhost:5432/catalogo_libros  
DB_USER=postgres  
DB_PASSWORD=tu_password

También debes crear la base de datos en PostgreSQL:

CREATE DATABASE catalogo_libros;

## Autor:
Tayra Soto Meza
