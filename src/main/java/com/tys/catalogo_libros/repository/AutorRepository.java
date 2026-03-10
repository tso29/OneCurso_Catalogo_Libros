package com.tys.catalogo_libros.repository;

import com.tys.catalogo_libros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNombre(String nombre);
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(Integer nacimiento, Integer fallecimiento);

    @Query("""
        SELECT a FROM Autor a
        WHERE a.nacimiento <= :anio
        AND (a.fallecimiento IS NULL OR a.fallecimiento >= :anio)
        """)
    List<Autor> autoresVivosEnAnio(@Param("anio") int anio);

}
