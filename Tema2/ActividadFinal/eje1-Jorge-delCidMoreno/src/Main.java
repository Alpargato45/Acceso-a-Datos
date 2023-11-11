import entradadatos.EntradaDatos;
import java.io.*;
import java.util.*;

public class Main {

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

    public static void main(String[] args) {

        int menu = 0;

        ArrayList<Jugador> listaJugadores = new ArrayList<>();

        // Cargar datos desde el archivo de texto
        File archivo = new File("jugadores.txt");
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader("jugadores.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    Jugador jugador = new Jugador(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]), partes[4]);
                    listaJugadores.add(jugador);
                }
            } catch (IOException ex) {
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
                    crearJugador(listaJugadores);
                }
                case 2 -> {
                    mostrarListaJugadores(listaJugadores);
                }
                case 3 -> {
                    String nombre = EntradaDatos.pedirCadena("Ingrese el nombre del jugador");
                    String apodo = EntradaDatos.pedirCadena("Ingrese el apodo del jugador");
                    int dorsal = EntradaDatos.pedirEntero("Ingrese el dorsal del jugador");

                    buscarJugador(listaJugadores, nombre, apodo, dorsal);
                }
            }

        } while (menu != 4);

        // Guardar datos en el archivo de texto
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("jugadores.txt"))) {
            for (Jugador jugador : listaJugadores) {
                bw.write(jugador.getNombre() + "," + jugador.getApodo() + "," + jugador.getPuesto() + "," + jugador.getDorsal() + "," + jugador.getDescripcion());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}