import entradadatos.EntradaDatos;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String nombreArchivo = EntradaDatos.pedirCadena("Ingrese el nombre del archivo de texto: ");

        String cadenaBuscada = EntradaDatos.pedirCadena("Ingrese la cadena que desea buscar: ");

        try (BufferedReader archivo = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = archivo.readLine()) != null) {
                numeroLinea++;

                if (linea.contains(cadenaBuscada)) {
                    System.out.println("Línea " + numeroLinea + ": " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo no existe: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
