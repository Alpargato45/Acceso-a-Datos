import entradadatos.*;

import java.io.*;

/**
 *
 * @author Jorge del Cid Moreno
 *
 */

public class Main {

    public static String detectarFormato(String archivoImagen) {
        try (InputStream is = new FileInputStream(archivoImagen)) {
            byte[] bytes = new byte[8]; // Leemos los primeros 8 bytes

            if (is.read(bytes) != -1) {
                // Comparamos los primeros bytes con los valores hexadecimales de las cabeceras de formatos
                if (esJPEG(bytes)) {
                    return "JPEG";
                } else if (esPNG(bytes)) {
                    return "PNG";
                } else if (esGIF(bytes)) {
                    return "GIF";
                } else if (esICO(bytes)) {
                    return "ICO";
                } else {
                    return "Formato no reconocido";
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return "No se pudo leer el archivo";
    }

    public static boolean esArchivoDeImagen(String nombreArchivo) {
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif") || extension.equals("ico");
    }

    public static boolean esJPEG(byte[] bytes) {
        return (bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8);
    }

    public static boolean esPNG(byte[] bytes) {
        return (bytes[0] == (byte) 0x89 && bytes[1] == (byte) 0x50 && bytes[2] == (byte) 0x4E && bytes[3] == (byte) 0x47);
    }

    public static boolean esGIF(byte[] bytes) {
        return (bytes[0] == (byte) 0x47 && bytes[1] == (byte) 0x49 && bytes[2] == (byte) 0x46);
    }

    public static boolean esICO(byte[] bytes) {
        return (bytes[0] == (byte) 0x00 && bytes[1] == (byte) 0x00 && bytes[2] == (byte) 0x01 && bytes[3] == (byte) 0x00);
    }

    public static void main(String[] args) {

        String rutaImagen = EntradaDatos.pedirCadena("Ruta de la imagen:");

        File directorioImagenes = new File(rutaImagen);
        if (!directorioImagenes.isDirectory()) {
            System.err.println("La ruta especificada no es un directorio válido.");
            return;
        }

        File[] archivos = directorioImagenes.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && esArchivoDeImagen(archivo.getName())) {
                    String formato = detectarFormato(archivo.getAbsolutePath());
                    System.out.println("El archivo " + archivo.getName() + " es de formato: " + formato);
                }
            }
        }
    }
}



