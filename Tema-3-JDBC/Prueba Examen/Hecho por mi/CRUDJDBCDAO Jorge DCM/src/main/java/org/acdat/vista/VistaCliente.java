package org.acdat.vista;

import entradadatos.EntradaDatos;
import org.acdat.negocio.Cliente;
import java.sql.SQLException;
import java.util.*;
public class VistaCliente {
    private Cliente cliente = new Cliente();


    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Clientes");
        System.out.println("1. Mostrar Clientes");
        System.out.println("2. Agregar Cliente");
        System.out.println("3. Actualizar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("0. Salir");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudCliente() throws SQLException {

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(mostrarClientes());
                    break;
                case 2:
                    this.agregarCliente();
                    break;
                case 3:
                    this.actualizarCliente();
                    break;
                case 4:
                    this.eliminarCliente();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elija nuevamente.");
            }
        }
    }

    public String mostrarClientes() throws SQLException {
        String respuesta;
        respuesta = cliente.mostrarClientes();
        return respuesta;
    }

    public void agregarCliente() {
        Cliente cliente = new Cliente();

        cliente.setNombre(EntradaDatos.pedirCadena("Ingrese el nombre del cliente: "));
        cliente.setCorreo(EntradaDatos.pedirCadena("Ingrese el correo del cliente: "));
        cliente.setTelefono(EntradaDatos.pedirCadena("Ingrese el teléfono del cliente: "));

        try {
            if (cliente.agregarCliente()) {
                System.out.println("Cliente agregado correctamente");
            } else {
                System.out.println("Error al crear el cliente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarCliente() throws SQLException {
        System.out.println(this.mostrarClientes());

        int clienteId = EntradaDatos.pedirEntero("Ingrese el ID del cliente que desea actualizar: ");
        cliente.setId(clienteId);
        if (cliente.existeCliente()) {
            if (cliente.cargarCliente()) {
                cliente.setNombre(EntradaDatos.pedirCadena("Ingrese el nuevo nombre del cliente ("+ cliente.getNombre() +"): "));
                cliente.setCorreo(EntradaDatos.pedirCadena("Ingrese el nuevo correo del cliente("+ cliente.getCorreo() +"): "));
                cliente.setTelefono(EntradaDatos.pedirCadena("Ingrese el nuevo teléfono del cliente("+ cliente.getTelefono() +"): "));
            }
            try {
                if (cliente.actualizarCliente()) {
                        System.out.println("Cliente actualizado correctamente");
                    } else {
                        System.out.println("Error al actualizar el cliente");
                    }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("El cliente seleccionado " + clienteId + " no existe");
        }
    }

    public void eliminarCliente() throws SQLException {

        System.out.println(this.mostrarClientes());

        int clienteId = EntradaDatos.pedirEntero("Ingrese el ID del cliente que desea actualizar: ");
        cliente.setId(clienteId);
        if (cliente.existeCliente()) {
            try {
                if (cliente.eliminarCliente()) {
                    System.out.println("Cliente eliminado correctamente");
                } else {
                    System.out.println("Error al eliminar el cliente");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("El cliente seleccionado " + clienteId + " no existe");
        }
    }
}
