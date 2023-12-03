package org.acdat.negocio;

import org.acdat.jdbc.AgenciaDao;
import org.acdat.jdbc.ClienteDao;
import org.acdat.jdbc.MiJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Agencia {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;

    public Agencia(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Agencia() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }


    public String mostrarAgencias() throws SQLException {
        String respuesta="";

        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
        AgenciaDao agenciaDao = new AgenciaDao();
        respuesta = agenciaDao.mostrarAgencia(miJDBC);
        miJDBC.cerrarConexion();
        return respuesta;
    }

    public boolean  agregarAgencia() throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }

    public boolean  actualizarAgencia() throws SQLException {
        boolean respuesta = false;

        return respuesta;
    }

    public boolean cargarAgencia() throws SQLException {
        boolean respuesta = false;


        return respuesta;
    }

    public boolean existeAgencia() throws SQLException {
        boolean respuesta = false;


        return respuesta;
    }

    public boolean  eliminarAgencia() throws SQLException {
        boolean respuesta = false;


        return respuesta;
    }
}