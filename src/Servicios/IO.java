package Servicios;

import java.util.Scanner;

/**
 * Clase de utilidad para entrada/salida de consola
 */
public class IO {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Imprime un mensaje seguido de un salto de línea
     * @param mensaje El mensaje a imprimir
     */
    public static void println(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Imprime un mensaje sin salto de línea
     * @param mensaje El mensaje a imprimir
     */
    public static void print(String mensaje) {
        System.out.print(mensaje);
    }
    
    /**
     * Lee una línea de entrada del usuario
     * @return La línea leída
     */
    public static String readln() {
        return scanner.nextLine();
    }
}
