package com.tys.catalogo_libros.repository;

import com.tys.catalogo_libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Libro findByTitulo(String titulo);
    @Query("SELECT l FROM Libro l JOIN FETCH l.autor WHERE l.idioma = :idioma")
    List<Libro> findByIdiomaConAutor(String idioma);

}