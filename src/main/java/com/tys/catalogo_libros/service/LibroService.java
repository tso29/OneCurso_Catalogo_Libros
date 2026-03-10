package com.tys.catalogo_libros.service;

import com.tys.catalogo_libros.model.Autor;
import com.tys.catalogo_libros.model.Libro;
import com.tys.catalogo_libros.repository.AutorRepository;
import com.tys.catalogo_libros.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void guardarLibro(DatosLibro datosLibro) {

        if(libroRepository.findByTitulo(datosLibro.titulo()) != null){
            System.out.println("El libro ya está registrado.");
            return;
        }

        DatosAutor datosAutor = datosLibro.autores().get(0);

        Autor autor = autorRepository.findByNombre(datosAutor.nombre());

        if (autor == null) {
            autor = new Autor(datosAutor);
            autorRepository.save(autor);
        }

        Libro libro = new Libro(
                datosLibro.titulo(),
                datosLibro.idiomas().get(0),
                datosLibro.numeroDescargas().intValue(),
                autor
        );

        libroRepository.save(libro);
    }
}