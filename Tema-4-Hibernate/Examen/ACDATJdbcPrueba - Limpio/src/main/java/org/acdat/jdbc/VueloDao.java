package org.acdat.jdbc;

import org.acdat.negocio.Vuelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloDao {
    public List<Vuelo> mostrarVuelos(Connection connection) throws SQLException, SQLException {
        List<Vuelo> VueloList = new ArrayList<Vuelo>();

        return VueloList;
    }

    public boolean agregarVuelo(Connection connection,  Vuelo Vuelo) throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }

    public boolean actualizarVuelo(Connection connection,  Vuelo Vuelo) throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }

    public Vuelo cargarVuelo (Connection connection, int id) throws SQLException {

        Vuelo Vuelo = null;


        return Vuelo;

    }

    public boolean existeVuelo (Connection connection, int id) throws SQLException {
        boolean respuesta = true;

        return respuesta;
    }

    public boolean eliminarVuelo(Connection connection, int VueloId) throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }
}
