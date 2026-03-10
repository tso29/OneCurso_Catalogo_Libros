package com.tys.catalogo_libros.principal;

import com.tys.catalogo_libros.model.Autor;
import com.tys.catalogo_libros.model.Libro;
import com.tys.catalogo_libros.repository.AutorRepository;
import com.tys.catalogo_libros.repository.LibroRepository;
import com.tys.catalogo_libros.service.*;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private final String URL_BASE = "https://gutendex.com/books/";

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private LibroService libroService;

    public Principal(LibroRepository libroRepository,
                     AutorRepository autorRepository,
                     LibroService libroService) {

        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.libroService = libroService;
    }

    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("""
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un año
                    5 - Listar libros por idioma
                    0 - Salir
                    """);

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {

        System.out.println("Escribe el nombre del libro:");
        String titulo = teclado.nextLine();

        String url = URL_BASE + "?search=" + titulo.replace(" ", "%20");

        String json = consumoAPI.obtenerDatos(url);

        DatosRespuesta respuesta = conversor.obtenerDatos(json, DatosRespuesta.class);

        if (!respuesta.resultados().isEmpty()) {

            DatosLibro datosLibro = respuesta.resultados().get(0);

            libroService.guardarLibro(datosLibro);

            System.out.println("\nLibro procesado:");
            System.out.println("Título: " + datosLibro.titulo());
            System.out.println("Autor: " + datosLibro.autores().get(0).nombre());
            System.out.println("Idioma: " + datosLibro.idiomas());
            System.out.println("Descargas: " + datosLibro.numeroDescargas());

        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibros() {
        var libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        System.out.println("\nLibros registrados:");
        for (var libro : libros) {
            System.out.println(libro);
            System.out.println("------------------------");
        }
    }

    private void listarAutores() {
        var autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }

        System.out.println("\nAutores registrados:");
        for (var autor : autores) {
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Nacimiento: " + autor.getNacimiento());
            System.out.println("Fallecimiento: " + autor.getFallecimiento());
            System.out.println("------------------------");
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para filtrar autores vivos:");
        int anio = teclado.nextInt();
        teclado.nextLine();

        var autores = autorRepository.autoresVivosEnAnio(anio);
        if (autores.isEmpty()) {
            System.out.println("No hay autores vivos en ese año.");
            return;
        }

        System.out.println("\nAutores vivos en " + anio + ":");
        for (var autor : autores) {
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Nacimiento: " + autor.getNacimiento());
            System.out.println("Fallecimiento: " + autor.getFallecimiento());
            System.out.println("------------------------");
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma (ejemplo: es, en, fr, pt):");
        String idioma = teclado.nextLine();

        List<Libro> libros = libroRepository.findByIdiomaConAutor(idioma);
        if(libros.isEmpty()) {
            System.out.println("No hay libros en ese idioma.");
        } else {
            System.out.println("Libros en idioma '" + idioma + "':");
            for(Libro libro : libros) {
                System.out.println(libro);
                System.out.println("------------------------");
            }
        }
    }
}