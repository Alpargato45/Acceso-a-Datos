package org.acdat.negocio;

import org.acdat.entitiesJPA.VuelosJPA;
import org.acdat.jdbc.VueloDao;
import org.acdat.jdbc.MiJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return id == vuelo.id && Double.compare(costo, vuelo.costo) == 0 && Objects.equals(origen, vuelo.origen) && Objects.equals(destino, vuelo.destino) && Objects.equals(fecha_salida, vuelo.fecha_salida) && Objects.equals(fecha_llegada, vuelo.fecha_llegada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origen, destino, fecha_salida, fecha_llegada, costo);
    }

    public String mostrarVuelos() throws SQLException {
        String respuesta="";
        List<Vuelo> vueloList = new ArrayList<>();

        VuelosJPA vueloJPA = new VuelosJPA();
        vueloList = vueloJPA.mostrarVuelos();

        for (Vuelo vuelo : vueloList) {
            respuesta = respuesta + "\n" + vuelo.toString();
        }

        return respuesta;
    }

    public boolean  agregarVuelo() throws SQLException {
        boolean respuesta = false;
        VuelosJPA vueloJPA = new VuelosJPA();
        respuesta = vueloJPA.agregarVuelo(this);
        return respuesta;
    }

    public boolean  actualizarVuelo() throws SQLException {
        boolean respuesta = false;
        VuelosJPA vueloJPA = new VuelosJPA();
        respuesta = vueloJPA.actualizarVuelo(this);
        return respuesta;
    }

    public boolean cargarVuelo() throws SQLException {
        boolean respuesta = false;
        VuelosJPA vueloJPA = new VuelosJPA();
        Vuelo vuelo = vueloJPA.cargarVuelo(this.id);

        if (vuelo != null){
            this.id = vuelo.getId();
            this.origen = vuelo.getOrigen();
            this.destino = vuelo.getDestino();
            this.fecha_salida = vuelo.getFecha_salida();
            this.fecha_llegada = vuelo.getFecha_llegada();
            this.costo = vuelo.getCosto();

            respuesta = true;
        }
        return respuesta;
    }

    public boolean existeVuelo() throws SQLException {
        boolean respuesta = false;
        VuelosJPA vueloJPA = new VuelosJPA();
        respuesta = vueloJPA.existeVuelo(this.id);

        return respuesta;
    }

    public boolean  eliminarVuelo() throws SQLException {
        boolean respuesta = false;
        VuelosJPA vueloJPA = new VuelosJPA();
        respuesta = vueloJPA.eliminarVuelo(this.id);

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