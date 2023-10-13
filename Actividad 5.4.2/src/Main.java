import entradadatos.EntradaDatos;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String nombreArchivo1 = EntradaDatos.pedirCadena("Ingrese el nombre del primer archivo ordenado: ");
        String nombreArchivo2 = EntradaDatos.pedirCadena("Ingrese el nombre del segundo archivo ordenado: ");

        File archivo1 = new File(nombreArchivo1);
        File archivo2 = new File(nombreArchivo2);

        if (!archivo1.exists() || !archivo2.exists()) {
            System.out.println("Uno o ambos archivos no existen.");
            return;
        }

        try (BufferedReader archivoUno = new BufferedReader(new FileReader(archivo1));
             BufferedReader archivoDos = new BufferedReader(new FileReader(archivo2));
             BufferedWriter archivoOrdenado = new BufferedWriter(new FileWriter("ordenado.txt"))) {

            ArrayList<String> lineas = new ArrayList<>();

            String linea;

            while ((linea = archivoUno.readLine()) != null) {
                lineas.add(linea);
            }

            while ((linea = archivoDos.readLine()) != null) {
                lineas.add(linea);
            }

            Collections.sort(lineas);

            for (String lineaOrdenada : lineas) {
                archivoOrdenado.write(lineaOrdenada);
                archivoOrdenado.newLine();
            }

            System.out.println("Archivos fusionados y ordenados en ordenado.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}