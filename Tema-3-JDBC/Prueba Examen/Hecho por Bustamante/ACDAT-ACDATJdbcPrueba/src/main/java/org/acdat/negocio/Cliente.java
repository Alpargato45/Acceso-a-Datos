package org.acdat.negocio;

import org.acdat.jdbc.ClienteDao;
import org.acdat.jdbc.MiJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    protected int id;
    protected String nombre;
    protected String correo;
    protected String telefono;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;

        Cliente cliente = (Cliente) o;

        if (getId() != cliente.getId()) return false;
        if (!getNombre().equals(cliente.getNombre())) return false;
        if (!getCorreo().equals(cliente.getCorreo())) return false;
        return getTelefono().equals(cliente.getTelefono());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNombre().hashCode();
        result = 31 * result + getCorreo().hashCode();
        result = 31 * result + getTelefono().hashCode();
        return result;
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
        List<Cliente> clienteList = new ArrayList<Cliente>();

        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
        ClienteDao clienteDao = new ClienteDao();

        clienteList = clienteDao.mostrarClientes(miJDBC.getConnection());

        for (Cliente cliente : clienteList) {
            respuesta = respuesta + "\n" + cliente.toString();
        }

        return respuesta;
    }

    public boolean  agregarCliente() throws SQLException {
        boolean respuesta = false;
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = clienteDao.agregarCliente(miJDBC.getConnection(), this);
        };
        return respuesta;
    }

    public boolean  actualizarCliente() throws SQLException {
        boolean respuesta = false;
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = clienteDao.actualizarCliente(miJDBC.getConnection(), this);
        };
        return respuesta;
    }

    public boolean cargarCliente() throws SQLException {
        boolean respuesta = false;
        Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
             cliente = clienteDao.cargarCliente(miJDBC.getConnection(), this.id);
             this.id = cliente.id;
             this.nombre = cliente.nombre;
             this.correo = cliente.correo;
             this.telefono = cliente.telefono;
             respuesta = true;
        }

        return respuesta;
    }

    public boolean existeCliente() throws SQLException {
        boolean respuesta = false;
        Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = clienteDao.existeCliente(miJDBC.getConnection(), this.id);
        }

        return respuesta;
    }

    public boolean  eliminarCliente() throws SQLException {
        boolean respuesta = false;
        ClienteDao clienteDao = new ClienteDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = clienteDao.eliminarCliente(miJDBC.getConnection(), this.id);
        };
        return respuesta;
    }


}
