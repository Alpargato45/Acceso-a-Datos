package org.acdat.jdbc;

import org.acdat.negocio.Agencia;
import org.acdat.negocio.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDao {
    public List<Agencia> mostrarClientes(Connection connection) throws SQLException, SQLException {
        List<Agencia> agenciaList = new ArrayList<Agencia>();


        return agenciaList;
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
