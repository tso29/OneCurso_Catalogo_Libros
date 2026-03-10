package com.tys.catalogo_libros.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)  // <- Esto ignora campos no declarados en el JSON
public record DatosRespuesta(

        @JsonAlias("results")   // coincidiendo con el JSON de Gutendex
        List<DatosLibro> resultados

) {}