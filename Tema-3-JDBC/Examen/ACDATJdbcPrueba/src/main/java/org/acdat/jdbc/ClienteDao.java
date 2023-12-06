package org.acdat.jdbc;

import org.acdat.negocio.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    public List<Cliente> mostrarClientes(Connection connection) throws SQLException, SQLException {
        List<Cliente> clienteList = new ArrayList<Cliente>();
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            //System.out.println("ID\tNombre\t\tCorreo\t\tTeléfono");
            //System.out.println("--------------------------------------------------------------------------");
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("cliente_id"));
                cliente.setNombre(resultSet.getString("nombre_cliente"));
                cliente.setCorreo(resultSet.getString("correo_cliente"));
                cliente.setTelefono(resultSet.getString("telefono_cliente"));
                clienteList.add(cliente);
            }
            // System.out.println();
            return clienteList;
        }
    }

    public boolean agregarCliente(Connection connection,  Cliente cliente) throws SQLException {
        boolean respuesta = false;
        int res = 0;
        String sql = "INSERT INTO clientes (nombre_cliente, correo_cliente, telefono_cliente) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getCorreo());
            preparedStatement.setString(3, cliente.getTelefono());
            res = preparedStatement.executeUpdate();
            if (res > 0) respuesta = true;
        }
        return respuesta;
    }

    public boolean actualizarCliente(Connection connection,  Cliente cliente) throws SQLException {
        boolean respuesta = false;
        int res = 0;

        String sql = "UPDATE clientes SET nombre_cliente=?, correo_cliente=?, telefono_cliente=? WHERE cliente_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getCorreo());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.setInt (4, cliente.getId());
            res = preparedStatement.executeUpdate();
            if (res == 1) respuesta = true;
        }
        return respuesta;
    }

    public Cliente cargarCliente (Connection connection, int id) throws SQLException {

        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE cliente_id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            //System.out.println("ID\tNombre\t\tCorreo\t\tTeléfono");
            //System.out.println("--------------------------------------------------------------------------");
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("cliente_id"));
                cliente.setNombre(resultSet.getString("nombre_cliente"));
                cliente.setCorreo(resultSet.getString("correo_cliente"));
                cliente.setTelefono(resultSet.getString("telefono_cliente"));
            }
            // System.out.println();
            return cliente;
        }
    }

    public boolean existeCliente (Connection connection, int id) throws SQLException {

        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE cliente_id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()  ) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean eliminarCliente(Connection connection, int clienteId) throws SQLException {
        boolean respuesta = false;
        int res = 0;

        String sql = "DELETE FROM clientes WHERE cliente_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, clienteId);
            res = preparedStatement.executeUpdate();
            if (res == 1) respuesta = true;
        }
        return respuesta;
    }
}
