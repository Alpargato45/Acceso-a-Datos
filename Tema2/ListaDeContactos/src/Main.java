import entradadatos.EntradaDatos;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void crearContacto(ArrayList<Contacto> Agenda) {
        String nombre;
        String apellido;
        String correo;
        String telefono;
        String descripcion;

        nombre = EntradaDatos.pedirCadena("Nombre del Contacto");
        apellido = EntradaDatos.pedirCadena("Apellido del Contacto");
        correo = EntradaDatos.pedirCadena("Correo del Contacto");
        telefono = EntradaDatos.pedirCadena("Teléfono del Contacto");
        descripcion = EntradaDatos.pedirCadena("Descripción del Contacto");

        Contacto contacto = new Contacto(nombre, apellido, correo, telefono, descripcion);
        Agenda.add(contacto);
    }

    public static void mostrarListaContactos(ArrayList<Contacto> Agenda) {
        for (int i = 0; i < Agenda.size(); i++) {
            System.out.println("Contacto " + (i + 1) + ":\n" + Agenda.get(i) + "\n");
        }
    }

    public static void buscarContacto(ArrayList<Contacto> Agenda, String busqueda) {
        boolean encontrado = false;

        for (Contacto contacto : Agenda) {
            if (contacto.getNombreCompleto().equalsIgnoreCase(busqueda) || contacto.getTelefono().equalsIgnoreCase(busqueda)) {
                System.out.println("Contacto encontrado:\n" + contacto + "\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron contactos con el nombre o teléfono proporcionados.");
        }
    }

    public static void main(String[] args) {
        int menu = 0;
        ArrayList<Contacto> Agenda = new ArrayList<>();

        File archivo = new File("contactos.obj");
        if (archivo.exists()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("contactos.obj"))) {
                Agenda = (ArrayList<Contacto>) entrada.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        do {
            System.out.println("\tMENU");
            System.out.println("1. Crear Nuevo Contacto");
            System.out.println("2. Mostrar la lista actual de los contactos");
            System.out.println("3. Buscar Contacto por su nombre completo o teléfono");
            System.out.println("4. Salir");
            do {
                try {
                    menu = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("caracter introducido no válido");
                }
            } while (menu < 1 || menu > 4);

            switch (menu) {
                case 1 -> {
                    crearContacto( Agenda);
                    System.out.println("Contacto añadido con éxito");
                }
                case 2 -> {
                    mostrarListaContactos(Agenda);
                }
                case 3 -> {
                    String busqueda = EntradaDatos.pedirCadena("Ingrese el nombre completo o teléfono");
                    buscarContacto(Agenda, busqueda);
                }
            }

        } while (menu != 4);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("contactos.obj"))) {
            salida.writeObject(Agenda);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}