package com.aluracursos.challenge3_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//DTO de los datos autor con lo que se necesita mostrar, ya que no es buena
//practica mostrar el objeto directo de la base de la datos
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNacimiento,
        @JsonAlias("death_year") String fechaFallecimiento
        ) {
}
