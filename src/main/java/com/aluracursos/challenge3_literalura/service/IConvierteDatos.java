package com.aluracursos.challenge3_literalura.service;

//Interfaz para convertir el json a algo legible
public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
