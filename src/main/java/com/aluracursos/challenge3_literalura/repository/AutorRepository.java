package com.aluracursos.challenge3_literalura.repository;

import com.aluracursos.challenge3_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
