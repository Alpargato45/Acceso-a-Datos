package org.acdat.jdbc;

import org.acdat.negocio.Cliente;
import org.acdat.negocio.Vuelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloDao {
    public String mostrarVuelo(MiJDBC miJDBC) {
        String cadena = "";
        try {
            ResultSet rs = miJDBC.getStatement().executeQuery("SELECT * FROM vuelos");
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    cadena += (rs.getString(i) + "\t");
                }
                cadena += "\n";
            }
            miJDBC.cerrarConexion();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
        return cadena;
    }

    public boolean agregarVuelo(Connection connection,  Vuelo Vuelo) throws SQLException {
        boolean respuesta = false;
        int res = 0;
        String sql = "INSERT INTO vuelos (origen,destino,fecha_salida,fecha_llegada,costo) VALUES (?, ?, ?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Vuelo.getOrigen());
            preparedStatement.setString(2, Vuelo.getDestino());
            preparedStatement.setDate(3, Date.valueOf(Vuelo.getFecha_salida()));
            preparedStatement.setDate(4, Date.valueOf(Vuelo.getFecha_llegada()));
            preparedStatement.setDouble(5, Vuelo.getCosto());
            res = preparedStatement.executeUpdate();
            if (res > 0) respuesta = true;
        }
        return respuesta;
    }

    public boolean actualizarVuelo(Connection connection,  Vuelo Vuelo) throws SQLException {
        boolean respuesta = false;
        int res = 0;

        String sql = "UPDATE vuelos SET origen=?, destino=?, fecha_salida=?, fecha_llegada=?, costo=? WHERE vuelo_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(6,Vuelo.getId());
            preparedStatement.setString(1, Vuelo.getOrigen());
            preparedStatement.setString(2, Vuelo.getDestino());
            preparedStatement.setDate(3, Date.valueOf(Vuelo.getFecha_salida()));
            preparedStatement.setDate (4, Date.valueOf(Vuelo.getFecha_llegada()));
            preparedStatement.setDouble(5,Vuelo.getCosto());
            res = preparedStatement.executeUpdate();
            if (res == 1) respuesta = true;
        }
        return respuesta;
    }

    public Vuelo cargarVuelo (Connection connection, int id) throws SQLException {

        Vuelo vuelo = null;
        String sql = "SELECT * FROM vuelos WHERE vuelo_id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                vuelo = new Vuelo();
                vuelo.setId(resultSet.getInt("vuelo_id"));
                vuelo.setOrigen(resultSet.getString("origen"));
                vuelo.setDestino(resultSet.getString("destino"));
                vuelo.setFecha_salida(String.valueOf(resultSet.getDate("fecha_salida")));
                vuelo.setFecha_llegada(String.valueOf(resultSet.getDate("fecha_llegada")));
                vuelo.setCosto(resultSet.getDouble("costo"));
            }
            return vuelo;
        }
    }

    public boolean existeVuelo (Connection connection, int id) throws SQLException {
        Vuelo vuelo = null;
        String sql = "SELECT * FROM vuelos WHERE vuelo_id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()  ) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean eliminarVuelo(Connection connection, int VueloId) throws SQLException {
        boolean respuesta = false;
        int res = 0;

        String sql = "DELETE FROM vuelos WHERE vuelo_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, VueloId);
            res = preparedStatement.executeUpdate();
            if (res == 1) respuesta = true;
        }
        return respuesta;
    }

    public void consultaVuelo() {
        MiJDBC miJDBC = new MiJDBC();
        miJDBC.BBDDQuery("SELECT c.nombre_cliente, v.*\n" +
                "FROM vuelos v\n" +
                "         JOIN clientes_vuelos cv ON v.vuelo_id = cv.vuelo_id\n" +
                "         JOIN clientes c ON cv.cliente_id = c.cliente_id;");
    }
}
