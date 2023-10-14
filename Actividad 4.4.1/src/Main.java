import java.io.*;

public class Main {
    public static void main(String[] args) {

        FileInputStream fIn = null;
        FileOutputStream fOut = null;
        try {
            fIn = new FileInputStream("entrada.dat");
            fOut = new FileOutputStream("salida.dat");

            byte[] buffer = new byte[16]; // Usamos un búfer de 16 bytes (128 bits)

            int bytesRead;
            while ((bytesRead = fIn.read(buffer)) != -1) {
                fOut.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el fichero");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida");
        } finally {
            try {
                if (fIn != null) {
                    fIn.close();
                }
                if (fOut != null) {
                    fOut.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar los archivos");
            }
        }
    }
}