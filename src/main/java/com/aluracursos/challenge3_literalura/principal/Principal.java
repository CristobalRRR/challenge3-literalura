package com.aluracursos.challenge3_literalura.principal;

import com.aluracursos.challenge3_literalura.model.*;
import com.aluracursos.challenge3_literalura.repository.AutorRepository;
import com.aluracursos.challenge3_literalura.repository.LibroRepository;
import com.aluracursos.challenge3_literalura.service.ConsumoAPI;
import com.aluracursos.challenge3_literalura.service.ConvierteDatos;

import java.util.Scanner;

//Clase principal, aqui hacemos todas las opciones para que interactue el usuario
public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final String BUSQUEDA = "?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convertir = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    //Hay que poner los repositorios para usar el servicio, ya que trabajamos con base de datos
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
    }
    //Mostrar el menu
    private String menu = """
            1. Buscar un libro por su titulo
            
            9. Salir
            Por favor escoja una opcion
            """;

    public void interaccionUsuario(){
        var opcion=-1;
        System.out.println("Bienvenido a literalura\n");
        while (opcion != 9){
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 9:
                    System.out.println("Gracias por su preferencia");
                    break;
            }
        }
    }



    private void buscarLibrosPorTitulo(){
        Datos datos = convertir.obtenerDatos(encontrarLibro(), Datos.class);
        try {
            DatosLibro datosLibro = datos.resultados().get(0);
            DatosAutor datosAutor = datos.resultados().get(0).autor().get(0);
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));
            if(libroRepository.findByTitulo(datosLibro.titulo()).isEmpty()){
                Libro libro = new Libro(datosLibro);
                libro.setAutor(autor);
                libroRepository.save(libro);
                System.out.println("Nuevo libro añadido con exito: \n" + libro);
            } else {
                System.out.println("Este libro ya existe en la base de datos\n");
            }
        } catch (Exception e){
            System.out.println("Lo sentimos, ha ocurrido un error\n");
        }
    }

    public String encontrarLibro(){
        System.out.println("Ingrese el titulo de un nuevo libro: \n");
        var titulo = teclado.nextLine().replace(" ","%20"); //El replace es para respestar el formato de url en la API
        var json = consumoAPI.obtenerDatos(URL_BASE + BUSQUEDA + titulo);
        return json;
    }

}
