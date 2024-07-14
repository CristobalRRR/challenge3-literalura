package com.aluracursos.challenge3_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//DTO de los datos en general al buscar el libro
@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("results")List<DatosLibro> resultados
        ) {
}
