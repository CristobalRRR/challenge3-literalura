package com.aluracursos.challenge3_literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idioma;
    private Double numeroDescargas;

    @ManyToOne(fetch = FetchType.EAGER)
    private List<Autor> autores;

    public Libro() {
    }
}
