package com.aluracursos.challenge3_literalura.model;

import jakarta.persistence.*;

import java.util.List;

//Definir la clase libro como entidad en la base de datos
@Entity
@Table(name = "libros")
public class Libro {

    //Columna Id auto incremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Los libros no pueden duplicarse, asi que el titulo debe ser unique
    @Column(unique = true)
    private String titulo;

    //Relacionar libro con autor
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Double numeroDescargas;


    //Constructor por defecto
    public Libro() {
    }

    //Constructor con parametros DTO
    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    //Darle formato al toString al devolver libro
    @Override
    public String toString(){
        String libro = "\nTitulo: \n" + this.titulo +
                "\nAutor: \n" + this.autor.getNombre() +
                "\nIdioma: \n" + this.idioma +
                "\nDescargas: \n" + this.numeroDescargas;
        return libro;
    }

    //Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
