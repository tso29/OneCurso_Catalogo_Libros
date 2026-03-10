package com.tys.catalogo_libros.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (Exception e) {
            // Incluimos el mensaje original para ver qué campo falla
            throw new RuntimeException("Error al convertir JSON: " + e.getMessage(), e);
        }
    }
}