package com.aluracursos.challenge3_literalura.repository;

import com.aluracursos.challenge3_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//JPA repository maneja las solicitudes CRUD de los autores
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento>=:fechaInicio and a.fechaFallecimiento<=:fechaFin")
    List<Autor> autoresVivosEntreFechas(Integer fechaInicio, Integer fechaFin);
}
