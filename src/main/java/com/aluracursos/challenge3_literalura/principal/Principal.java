package com.aluracursos.challenge3_literalura.principal;

import com.aluracursos.challenge3_literalura.model.*;
import com.aluracursos.challenge3_literalura.repository.AutorRepository;
import com.aluracursos.challenge3_literalura.repository.LibroRepository;
import com.aluracursos.challenge3_literalura.service.ConsumoAPI;
import com.aluracursos.challenge3_literalura.service.ConvierteDatos;

import java.util.List;
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
            -----------------------------------------
            1. Buscar un libro por su titulo
            2. Listar los libros ya buscados
            3. Lista de autores registrados
            4. Lista de autores vivos en cierto año
            5. Listar libros en un idioma especifico
            
            9. Salir
            Por favor escoja una opcion
            -----------------------------------------
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
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    verAutores();
                    break;
                case 4:
                    verAutoresVivosEnCiertaFecha();
                    break;
                case 5:
                    mostrarLibrosEnIdiomaEspecifico();
                    break;
                case 9:
                    System.out.println("Gracias por su preferencia");
                    break;
                default:
                    System.out.println("Opcion no valida");
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

    public void mostrarLibrosBuscados(){
        List<Libro> librosBuscados = libroRepository.findAll();
        librosBuscados.stream().forEach(l -> System.out.println(l.toString())); //Usamos el toString personalizado de la clase Libro
    }

    private void verAutores() {
        List<Autor> autoresRegistrados = autorRepository.findAll();
        autoresRegistrados.stream().forEach(a -> System.out.println(a.toString()));
    }

    private void verAutoresVivosEnCiertaFecha(){
        System.out.println("Ingrese el año que desea buscar");
        var fecha = teclado.nextInt();
        List<Autor> autoresVivos = autorRepository.autoresVivosEntreFechas(fecha);
        autoresVivos.stream().forEach(a -> System.out.println(a.toString()));
    }

    private void mostrarLibrosEnIdiomaEspecifico(){
        System.out.println("""
                Ingrese el numero del idioma que desea buscar:
                1. Español
                2. Ingles
                """);
        var opcionIdioma = teclado.nextInt();
        switch (opcionIdioma) {
            case 1:
                List<Libro> librosEs = libroRepository.findByIdioma("es");
                librosEs.stream().forEach(l -> System.out.println(l.toString()));
                break;
            case 2:
                List<Libro> librosEn = libroRepository.findByIdioma("en");
                librosEn.stream().forEach(l -> System.out.println(l.toString()));
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}
