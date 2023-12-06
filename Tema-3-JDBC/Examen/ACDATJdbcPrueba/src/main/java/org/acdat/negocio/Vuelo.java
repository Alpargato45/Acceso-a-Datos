package org.acdat.negocio;

import org.acdat.jdbc.VueloDao;
import org.acdat.jdbc.MiJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vuelo {
    protected int id;
    protected String origen;
    protected String destino;
    protected String fecha_salida;

    protected String fecha_llegada;

    protected double costo;


    public Vuelo(int id, String origen, String destino, String fecha_salida, String fecha_llegada, double costo) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.fecha_llegada = fecha_llegada;
        this.costo = costo;
    }

    public Vuelo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String mostrarVuelos() throws SQLException {
        String respuesta="";

        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
        VueloDao VueloDao = new VueloDao();

        respuesta = VueloDao.mostrarVuelo(miJDBC);

        return respuesta;
    }

    public boolean  agregarVuelo() throws SQLException {
        boolean respuesta = false;
        VueloDao VueloDao = new VueloDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
            respuesta = VueloDao.agregarVuelo(miJDBC.getConnection(), this);
        return respuesta;
    }

    public boolean  actualizarVuelo() throws SQLException {
        boolean respuesta = false;
        VueloDao VueloDao = new VueloDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
            respuesta = VueloDao.actualizarVuelo(miJDBC.getConnection(), this);
        return respuesta;
    }

    public boolean cargarVuelo() throws SQLException {
        boolean respuesta = false;
        Vuelo Vuelo = new Vuelo();
        VueloDao VueloDao = new VueloDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
             Vuelo = VueloDao.cargarVuelo(miJDBC.getConnection(), this.id);
             this.id = Vuelo.id;
             this.origen = Vuelo.origen;
             this.destino = Vuelo.destino;
             this.fecha_llegada = Vuelo.fecha_llegada;
             this.fecha_salida = Vuelo.fecha_salida;
             this.costo = Vuelo.costo;
             respuesta = true;

        return respuesta;
    }

    public boolean existeVuelo() throws SQLException {
        boolean respuesta = false;
        Vuelo Vuelo = new Vuelo();
        VueloDao VueloDao = new VueloDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
            respuesta = VueloDao.existeVuelo(miJDBC.getConnection(), this.id);

        return respuesta;
    }

    public boolean  eliminarVuelo() throws SQLException {
        boolean respuesta = false;
        VueloDao VueloDao = new VueloDao();
        MiJDBC miJDBC = new MiJDBC();

        miJDBC.abrirConexion();
            respuesta = VueloDao.eliminarVuelo(miJDBC.getConnection(), this.id);
        return respuesta;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id=" + id +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fecha_salida='" + fecha_salida + '\'' +
                ", fecha_llegada='" + fecha_llegada + '\'' +
                ", costo=" + costo +
                '}';
    }
}
