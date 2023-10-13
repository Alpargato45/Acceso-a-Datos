import entradadatos.EntradaDatos;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("¡Bienvenido al Bloc de Notas!");
        String nombreArchivo = EntradaDatos.pedirCadena("Ingrese el nombre del archivo: ");

        File archivo = new File(nombreArchivo);
        boolean sobrescribir = true;

        if (archivo.exists()) {
            String respuesta = EntradaDatos.pedirCadena("El archivo ya existe. ¿Desea sobrescribirlo? (S/N): ").trim().toLowerCase();
            sobrescribir = respuesta.equals("s");
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, !sobrescribir))) {
            int numeroLinea = 1;
            String linea;

            do {
                linea = EntradaDatos.pedirCadena("Escriba una oración (o escriba 'salir' para finalizar): ");

                if (!linea.equalsIgnoreCase("salir")) {
                    escritor.write(numeroLinea + ". " + linea);
                    escritor.newLine();
                    numeroLinea++;
                }
            } while (!linea.equalsIgnoreCase("salir"));

            System.out.println("Las oraciones se han guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}