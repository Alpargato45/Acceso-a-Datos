package org.acdat.jdbc;

import org.acdat.negocio.Agencia;
import org.acdat.negocio.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDao {
    public String mostrarAgencia(MiJDBC miJDBC) {
        String cadena = "";
        try {
            ResultSet rs = miJDBC.getStatement().executeQuery("SELECT * FROM agencias");
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
    public boolean agregarAgencia(Connection connection,  Agencia agencia) throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }

    public boolean actualizarAgencia(Connection connection,  Cliente cliente) throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }

    public Agencia cargarAgencia (Connection connection, int id) throws SQLException {

        Agencia agencia = null;

            return agencia;
    }

    public boolean existeAgencia (Connection connection, int id) throws SQLException {
        boolean respuesta = true;

        return respuesta;
    }

    public boolean eliminarAgencia(Connection connection, int agenciaId) throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }
}
