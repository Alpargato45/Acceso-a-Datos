import entradadatos.EntradaDatos;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class Main implements Serializable {

    public static void crearJugador(ArrayList<Jugador> listaJugadores) {
        String nombre;
        String apodo;
        String puesto;
        int dorsal;
        String descripcion;

        nombre = EntradaDatos.pedirCadena("Nombre del Jugador");
        apodo = EntradaDatos.pedirCadena("Apodo del Jugador");
        puesto = EntradaDatos.pedirCadena("Puesto del Jugador");
        dorsal = EntradaDatos.pedirEntero("Dorsal del Jugador");
        descripcion = EntradaDatos.pedirCadena("Descripción del Jugador");

        Jugador jugador = new Jugador(nombre, apodo, puesto, dorsal, descripcion);
        listaJugadores.add(jugador);
        System.out.println("Jugador añadido con éxito");
    }

    public static void mostrarListaJugadores(ArrayList<Jugador> listaJugadores) {
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println("Jugador " + (i + 1) + ":\n" + listaJugadores.get(i) + "\n");
        }
    }

    public static void buscarJugador(ArrayList<Jugador> listaJugadores, String nombre, String apodo, int dorsal) {
        boolean encontrado = false;

        for (Jugador jugador : listaJugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre) || jugador.getApodo().equalsIgnoreCase(apodo) || (jugador.getDorsal() == dorsal)) {
                System.out.println("Jugador encontrado:\n" + jugador + "\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron jugadores con el nombre.");
        }
    }

    public static void convertirObjetoAXML(ArrayList<Jugador> listaJugadores) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream("jugadores.xml"))) {
            encoder.writeObject(listaJugadores);
            System.out.println("Lista de jugadores convertida a XML con éxito.");
        } catch (IOException ex) {
            System.out.println("Error al convertir la lista de jugadores a XML: " + ex.getMessage());
        }
    }

    public static void convertirXMLAObjeto(ArrayList<Jugador> listaJugadores) {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("jugadores.xml"))) {
            listaJugadores.clear();
            listaJugadores.addAll((ArrayList<Jugador>) decoder.readObject());
            System.out.println("Lista de jugadores cargada desde XML con éxito.");
        } catch (IOException ex) {
            System.out.println("Error al cargar la lista de jugadores desde XML: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {

        int menu = 0;

        ArrayList<Jugador> listaJugadores = new ArrayList<>();

        File archivo = new File("jugadores.obj");
        if (archivo.exists()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("jugadores.obj"))) {
                listaJugadores = (ArrayList<Jugador>) entrada.readObject();
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
            System.out.println("1. Crear Jugador");
            System.out.println("2. Mostrar Lista actual de Jugadores");
            System.out.println("3. Buscar jugador por Nombre, apodo y dorsal");
            System.out.println("4. Convertir de obj a XML");
            System.out.println("5. Convertir de XML a obj");
            System.out.println("6. Salir");
            do {
                try {
                    menu = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("caracter introducido no válido");
                }
            } while (menu < 1 || menu > 6);

            switch (menu) {
                case 1 -> {
                    crearJugador(listaJugadores);
                }
                case 2 -> {
                    mostrarListaJugadores(listaJugadores);
                }
                case 3 -> {
                    String nombre = EntradaDatos.pedirCadena("Ingrese el nombre del jugador");
                    String apodo = EntradaDatos.pedirCadena("Ingrese el apodo del jugador");
                    int dorsal = EntradaDatos.pedirEntero("Ingrese el dorsal del jugador");

                    buscarJugador(listaJugadores, nombre,apodo,dorsal);
                }
                case 4 -> {
                    convertirObjetoAXML(listaJugadores);
                }
                case 5 -> {
                    convertirXMLAObjeto(listaJugadores);
                }
            }

        } while (menu != 6);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("jugadores.obj"))) {
            salida.writeObject(listaJugadores);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}