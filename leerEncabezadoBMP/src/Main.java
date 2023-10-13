import java.io.*;

public class Main {

    // Función para convertir un array de bytes en un entero
    private static int byteArrayToInt(byte[] bytes, int offset) {
        return ((bytes[offset + 3] & 0xFF) << 24) | ((bytes[offset + 2] & 0xFF) << 16) |
                ((bytes[offset + 1] & 0xFF) << 8) | (bytes[offset] & 0xFF);
    }

    public static void main(String[] args) {
        String rutaBMP = "ruta/al/archivo.bmp"; // Reemplaza con la ruta de tu archivo BMP

        try (FileInputStream archivoBMP = new FileInputStream(rutaBMP)) {
            // Leer y descartar los primeros 54 bytes (cabecera BMP)
            byte[] cabecera = new byte[54];
            archivoBMP.read(cabecera);

            // Extraer la información necesaria de la cabecera
            int tamano = byteArrayToInt(cabecera, 2);
            int ancho = byteArrayToInt(cabecera, 18);
            int alto = byteArrayToInt(cabecera, 22);
            boolean comprimido = cabecera[30] != 0;

            // Mostrar la información
            System.out.println("Tamaño: " + tamano + " bytes");
            System.out.println("Ancho: " + ancho + " píxeles");
            System.out.println("Alto: " + alto + " píxeles");
            System.out.println("Comprimido: " + (comprimido ? "Sí" : "No"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}