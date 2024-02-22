package org.acdat.negocio;

import org.acdat.jdbc.ClienteDao;
import org.acdat.jdbc.MiJDBC;
import org.acdat.jpa.AgenciaJPAEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Agencia {
    protected int id;
    protected String nombre;
    protected String direccion;
    protected String telefono;

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

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agencia)) return false;

        Agencia agencia = (Agencia) o;

        if (id != agencia.id) return false;
        if (!nombre.equals(agencia.nombre)) return false;
        if (!direccion.equals(agencia.direccion)) return false;
        return telefono.equals(agencia.telefono);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + telefono.hashCode();
        return result;
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
        List<Agencia> agenciaList = new ArrayList<>();

        AgenciaJPAEntity agenciaJPAEntity = new AgenciaJPAEntity();
        agenciaList = agenciaJPAEntity.mostrarAgencia();

        for (Agencia Agencia : agenciaList) {
            respuesta = respuesta + "\n" + Agencia.toString();
        }

        return respuesta;
    }

    public boolean  agregarAgencia() throws SQLException {
        boolean respuesta = false;

        AgenciaJPAEntity agenciaJPAEntity = new AgenciaJPAEntity();
        respuesta = agenciaJPAEntity.agregarAgencia(this);

        return respuesta;
    }

    public boolean  actualizarAgencia() throws SQLException {
        boolean respuesta = false;

        AgenciaJPAEntity agenciaJPAEntity = new AgenciaJPAEntity();
        respuesta = agenciaJPAEntity.actualizarAgencia(this);

        return respuesta;
    }

    public boolean cargarAgencia() throws SQLException {
        boolean respuesta = false;

        AgenciaJPAEntity agenciaJPAEntity = new AgenciaJPAEntity();
        Agencia agencia = agenciaJPAEntity.cargarAgencia(this.id);
        if (agencia != null){
            this.id = agencia.id;
            this.nombre = agencia.nombre;
            this.direccion = agencia.direccion;
            this.telefono = agencia.telefono;

            respuesta = true;
        }
        return respuesta;
    }

    public boolean existeAgencia() throws SQLException {
        boolean respuesta = false;
        AgenciaJPAEntity agenciaJPAEntity = new AgenciaJPAEntity();
        respuesta = agenciaJPAEntity.existeAgencia(this.id);

        return respuesta;
    }

    public boolean  eliminarAgencia() throws SQLException {
        boolean respuesta = false;
        AgenciaJPAEntity agenciaJPAEntity = new AgenciaJPAEntity();
        respuesta = agenciaJPAEntity.eliminarAgencia(this.id);

        return respuesta;
    }
}
