package com.aluracursos.challenge3_literalura.principal;

import com.aluracursos.challenge3_literalura.model.Datos;
import com.aluracursos.challenge3_literalura.service.ConsumoAPI;
import com.aluracursos.challenge3_literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convertir = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void interaccionUsuario(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = convertir.obtenerDatos(json, Datos.class);
        System.out.println(datos);
    }
}
