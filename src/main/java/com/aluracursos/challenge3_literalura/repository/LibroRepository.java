package com.aluracursos.challenge3_literalura.repository;

import com.aluracursos.challenge3_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
