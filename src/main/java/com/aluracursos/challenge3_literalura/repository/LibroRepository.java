package com.aluracursos.challenge3_literalura.repository;

import com.aluracursos.challenge3_literalura.model.Autor;
import com.aluracursos.challenge3_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//JPA repository maneja las solicitudes CRUD de los libros
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT i FROM Libro i WHERE i.idioma=:idioma")
    List<Libro> findByIdioma(String idioma);

}
