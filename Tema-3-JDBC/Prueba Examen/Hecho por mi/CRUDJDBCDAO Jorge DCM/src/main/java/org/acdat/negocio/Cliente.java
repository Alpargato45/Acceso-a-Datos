package org.acdat.negocio;

import org.acdat.jdbc.ClienteDao;
import org.acdat.jdbc.MiJDBC;
import java.sql.SQLException;
import java.util.*;

public class Cliente {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;

    public Cliente(int id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Cliente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public String mostrarClientes() throws SQLException {
        String respuesta="";

        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
        ClienteDao clienteDao = new ClienteDao();
        respuesta = clienteDao.mostrarCliente(miJDBC);
        miJDBC.cerrarConexion();
        return respuesta;
    }

    public boolean  agregarCliente() throws SQLException {
        boolean respuesta = false;
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
            respuesta = clienteDao.agregarCliente(miJDBC.getConnection(), this);
        miJDBC.cerrarConexion();
        return respuesta;
    }

    public boolean  actualizarCliente() throws SQLException {
        boolean respuesta = false;
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
            respuesta = clienteDao.actualizarCliente(miJDBC.getConnection(), this);
        miJDBC.cerrarConexion();
        return respuesta;
    }

    public boolean cargarCliente() throws SQLException {
        boolean respuesta = false;
        Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
             cliente = clienteDao.cargarCliente(miJDBC.getConnection(), this.id);
             this.id = cliente.id;
             this.nombre = cliente.nombre;
             this.correo = cliente.correo;
             this.telefono = cliente.telefono;
             respuesta = true;

        miJDBC.cerrarConexion();
        return respuesta;
    }

    public boolean existeCliente() throws SQLException {
        boolean respuesta = false;
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
            respuesta = clienteDao.existeCliente(miJDBC.getConnection(), this.id);
        miJDBC.cerrarConexion();
        return respuesta;
    }

    public boolean  eliminarCliente() throws SQLException {
        boolean respuesta = false;
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
            respuesta = clienteDao.eliminarCliente(miJDBC.getConnection(), this.id);
            miJDBC.cerrarConexion();
        return respuesta;
    }
}
