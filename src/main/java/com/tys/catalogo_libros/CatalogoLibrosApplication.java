package com.tys.catalogo_libros;

import com.tys.catalogo_libros.principal.Principal;
import com.tys.catalogo_libros.repository.AutorRepository;
import com.tys.catalogo_libros.repository.LibroRepository;
import com.tys.catalogo_libros.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final LibroService libroService;

    public CatalogoLibrosApplication(LibroRepository libroRepository,
                                     AutorRepository autorRepository,
                                     LibroService libroService) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.libroService = libroService;
    }

    @Override
    public void run(String... args) {

        Principal principal = new Principal(libroRepository, autorRepository, libroService);
        principal.mostrarMenu();
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogoLibrosApplication.class, args);
    }
}