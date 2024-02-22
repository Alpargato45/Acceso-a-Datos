package org.acdat.entitiesJPA;

import jakarta.persistence.*;
import org.acdat.negocio.Agencia;
import org.acdat.negocio.Vuelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VuelosJPA {
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

    public VuelosJPA() {
    }

    public VuelosJPA(int id, String origen, String destino, String fecha_salida, String fecha_llegada, double costo) {
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
        List<VuelosJPA> vueloJPAList;
        List<Vuelo> devolverLista = new ArrayList<>();
        Session session = null;
        try {
            session = abrirSession();
            if(session != null){
                Query<VuelosJPA> miQuery = session.createQuery("from org.acdat.entitiesJPA.VuelosJPA");
                vueloJPAList = miQuery.list();
                for(Object obj : vueloJPAList) {
                    VuelosJPA vueloJPA = (VuelosJPA) obj;
                    Vuelo vuelo = new Vuelo();
                    vuelo.setId(vueloJPA.id);
                    vuelo.setOrigen(vueloJPA.origen);
                    vuelo.setDestino(vueloJPA.destino);
                    vuelo.setFecha_salida(vueloJPA.getFecha_salida());
                    vuelo.setFecha_llegada(vueloJPA.getFecha_llegada());
                    vuelo.setCosto(vueloJPA.costo);
                    devolverLista.add(vuelo);
                }
            }

            session.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return devolverLista;
    }

    public boolean agregarVuelo(Vuelo vuelo) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = abrirSession();
            if (session != null) {
                transaction = session.beginTransaction();
                VuelosJPA vueloJPA = new VuelosJPA();
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
                VuelosJPA vueloJPA = new VuelosJPA();
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
        VuelosJPA vueloJPAEntity = null;
        List<VuelosJPA> vueloJPAEntities = new ArrayList<>();
        try {
            Session session = abrirSession();

            if (session != null){
                vueloJPAEntities = session.createNativeQuery("SELECT * FROM VuelosJPA WHERE vuelo_id = ?",VuelosJPA.class).setParameter(1,id).list();
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
        VuelosJPA vueloExistente = new VuelosJPA();
        try {
            Session session = abrirSession();

            if (session != null){
                vueloExistente = session.get(VuelosJPA.class, id);
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (vueloExistente != null)
            return true;

        return false;
    }

    public boolean eliminarVuelo(int vueloId) throws SQLException {
        List<VuelosJPA> vueloJPAEntities = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = abrirSession();

            if (session != null){
                VuelosJPA vueloExistente = session.get(VuelosJPA.class, vueloId);
                if (vueloExistente == null) {
                    System.out.println("No existe ningúna agencia con el id " + id + ". No se puede borrar.");
                    return false;
                } else {
                    transaction = session.beginTransaction();
                    session.delete(vueloExistente);
                    transaction.commit();
                    session.close();
                }
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
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