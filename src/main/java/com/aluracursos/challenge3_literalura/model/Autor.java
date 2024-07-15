package com.aluracursos.challenge3_literalura.model;

import jakarta.persistence.*;

import java.util.List;

//Definir la clase autor como entidad en la base de datos
@Entity
@Table(name = "autores")
public class Autor {
    //Id incremental autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //El nombre debe ser unique para no duplicar los autores
    @Column(unique = true)
    private String nombre;
    private String fechaNacimiento;
    private String fechaFallecimiento;

    //Relacion uno a muchos con libros, la columna que los conecta es el autor del libro
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    //Constructor personalizado
    public Autor() {
    }

    public Autor (DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaFallecimiento = datosAutor.fechaFallecimiento();
    }

    @Override
    //toString personalizado para mostrar datos del autor
    public String toString() {
        String autor = "\n**********************" +
                "\nNombre: " + this.nombre +
                "\nFecha de nacimiento: " + this.fechaNacimiento +
                "\nFecha de fallecimiento: " + this.fechaFallecimiento +
                "\n**********************\n";
        return autor;
    }


        //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(String fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
