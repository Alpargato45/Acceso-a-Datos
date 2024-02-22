package org.acdat.jpa;

import jakarta.persistence.*;
import org.acdat.negocio.Cliente;
import org.acdat.negocio.Vuelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "VuelosJPA")
public class VueloJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vuelo_id")
    protected int id;
    @Column
    protected String origen;
    @Column
    protected String destino;
    @Column
    protected String fecha_salida;

    @Column
    protected String fecha_llegada;

    @Column
    protected double costo;

    public VueloJPAEntity() {
    }

    public VueloJPAEntity(int id, String origen, String destino, String fecha_salida, String fecha_llegada, double costo) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.fecha_llegada = fecha_llegada;
        this.costo = costo;
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

    public List<Vuelo> mostrarVuelos() throws SQLException, SQLException {
        List<Vuelo> vueloList = new ArrayList<Vuelo>();
        List<VueloJPAEntity> vueloJPAEntities = new ArrayList<>();

        Session session = null;
        try {
            session = abrirSession();
            if(session != null){
                vueloJPAEntities = session.createNativeQuery("SELECT * FROM VuelosJPA", VueloJPAEntity.class ).list();
            }

            session.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < vueloJPAEntities.size(); i++){
            VueloJPAEntity vueloJPA = vueloJPAEntities.get(i);
            Vuelo vuelo = new Vuelo();

            vuelo.setId(vueloJPA.getId());
            vuelo.setOrigen(vueloJPA.getOrigen());
            vuelo.setDestino(vueloJPA.getDestino());
            vuelo.setFecha_salida(vueloJPA.getFecha_salida());
            vuelo.setFecha_llegada(vueloJPA.getFecha_llegada());
            vuelo.setCosto(vueloJPA.getCosto());

            vueloList.add(vuelo);
        }
        return vueloList;
    }

    public boolean agregarVuelo(Vuelo vuelo) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = abrirSession();
            if (session != null) {
                transaction = session.beginTransaction();
                VueloJPAEntity vueloJPA = new VueloJPAEntity();
                vueloJPA.id = vuelo.getId();
                vueloJPA.origen = vuelo.getOrigen();
                vueloJPA.destino = vuelo.getDestino();
                vueloJPA.fecha_salida = vuelo.getFecha_salida();
                vueloJPA.fecha_llegada = vuelo.getFecha_llegada();
                vueloJPA.costo = vuelo.getCosto();

                session.persist(vueloJPA);

                session.flush();
                transaction.commit();
                session.close();

                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean actualizarVuelo(Vuelo vuelo) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = abrirSession();
            if (session != null) {
                transaction = session.beginTransaction();
                VueloJPAEntity vueloJPA = new VueloJPAEntity();
                vueloJPA.id = vuelo.getId();
                vueloJPA.origen = vuelo.getOrigen();
                vueloJPA.destino = vuelo.getDestino();
                vueloJPA.fecha_salida = vuelo.getFecha_salida();
                vueloJPA.fecha_llegada = vuelo.getFecha_llegada();
                vueloJPA.costo = vuelo.getCosto();

                session.saveOrUpdate(vueloJPA);

                session.flush();
                transaction.commit();
                session.close();

                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public Vuelo cargarVuelo (int id) throws SQLException {
        Vuelo vuelo = new Vuelo();
        VueloJPAEntity vueloJPAEntity = null;
        List<VueloJPAEntity> vueloJPAEntities = new ArrayList<>();
        try {
            Session session = abrirSession();

            if (session != null){
                vueloJPAEntities = session.createNativeQuery("SELECT * FROM VuelosJPA WHERE vuelo_id = ?",VueloJPAEntity.class).setParameter(1,id).list();
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        vueloJPAEntity = vueloJPAEntities.get(0);

        vuelo.setId(vueloJPAEntity.getId());
        vuelo.setOrigen(vueloJPAEntity.getOrigen());
        vuelo.setDestino(vueloJPAEntity.getDestino());
        vuelo.setFecha_salida(vueloJPAEntity.getFecha_salida());
        vuelo.setFecha_llegada(vueloJPAEntity.getFecha_llegada());
        vuelo.setCosto(vueloJPAEntity.getCosto());

        return vuelo;
    }

    public boolean existeVuelo (int id) throws SQLException {
        List<VueloJPAEntity> vueloJPAEntities = new ArrayList<>();
        try {
            Session session = abrirSession();

            if (session != null){
                vueloJPAEntities = session.createNativeQuery("SELECT * FROM VuelosJPA WHERE vuelo_id = ?",VueloJPAEntity.class).setParameter(1,id).list();
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (vueloJPAEntities.size() == 1)
            return true;

        return false;
    }

    public boolean eliminarVuelo(int vueloId) throws SQLException {
        List<VueloJPAEntity> vueloJPAEntities = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = abrirSession();

            if (session != null){
                transaction = session.beginTransaction();
                vueloJPAEntities = session.createNativeQuery("SELECT * FROM VuelosJPA WHERE vuelo_id = ?",VueloJPAEntity.class).setParameter(1,vueloId).list();
                session.delete(vueloJPAEntities.get(0));
                session.flush();
                transaction.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public List<Vuelo> verificarVuelo(Connection connection) throws SQLException, SQLException {
        List<Vuelo> vueloList = new ArrayList<Vuelo>();

        return vueloList;
    }

    static Session abrirSession() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            return null;
        }else {
            System.out.println("Conexión Establecida");
        }
        return session;
    }
}
