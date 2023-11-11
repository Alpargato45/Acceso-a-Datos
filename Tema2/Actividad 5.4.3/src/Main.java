import entradadatos.EntradaDatos;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String nombreArchivo = EntradaDatos.pedirCadena("Ingrese el nombre del archivo que desea mostrar: ");

        try (BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int lineaActual = 0;

            while ((linea = archivo.readLine()) != null) {
                lineaActual++;

                System.out.println(linea);

                if (lineaActual % 23 == 0) {
                EntradaDatos.pedirCadena("Presiona 'Enter' para continuar la lectura...");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}