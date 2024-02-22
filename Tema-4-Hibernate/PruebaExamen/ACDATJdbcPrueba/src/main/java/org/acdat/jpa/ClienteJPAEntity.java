package org.acdat.jpa;

import jakarta.persistence.*;
import org.acdat.negocio.Agencia;
import org.acdat.negocio.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ClientesJPA")
public class ClienteJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    protected int id;
    @Column
    protected String nombre;
    @Column
    protected String correo;
    @Column
    protected String telefono;

    public ClienteJPAEntity() {
    }

    public ClienteJPAEntity(int id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
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

    public List<Cliente> mostrarClientes() throws SQLException, SQLException {
        List<Cliente> clienteList = new ArrayList<Cliente>();
        List<ClienteJPAEntity> clienteJPAEntities = new ArrayList<>();

        Session session = null;
        try {
            session = abrirSession();
            if(session != null){
                clienteJPAEntities = session.createNativeQuery("SELECT * FROM ClientesJPA", ClienteJPAEntity.class ).list();
            }

            session.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < clienteJPAEntities.size(); i++){
            ClienteJPAEntity clienteJPA = clienteJPAEntities.get(i);
            Cliente cliente = new Cliente();

            cliente.setId(clienteJPA.getId());
            cliente.setNombre(clienteJPA.getNombre());
            cliente.setCorreo(clienteJPA.getCorreo());
            cliente.setTelefono(clienteJPA.getTelefono());

            clienteList.add(cliente);
        }


        return clienteList;
    }

    public boolean agregarCliente(Cliente cliente) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = abrirSession();
            if (session != null) {
                transaction = session.beginTransaction();
                ClienteJPAEntity clienteJPA = new ClienteJPAEntity();
                clienteJPA.id = cliente.getId();
                clienteJPA.nombre = cliente.getNombre();
                clienteJPA.correo = cliente.getCorreo();
                clienteJPA.telefono = cliente.getTelefono();

                session.persist(clienteJPA);

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

    public boolean actualizarCliente(Cliente cliente) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = abrirSession();
            if (session != null) {
                transaction = session.beginTransaction();
                ClienteJPAEntity clienteJPA = new ClienteJPAEntity();
                clienteJPA.id = cliente.getId();
                clienteJPA.nombre = cliente.getNombre();
                clienteJPA.correo = cliente.getCorreo();
                clienteJPA.telefono = cliente.getTelefono();

                session.saveOrUpdate(clienteJPA);

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

    public Cliente cargarCliente (int id) throws SQLException {
        Cliente  cliente = new Cliente();
        ClienteJPAEntity clienteJPA = null;
        List<ClienteJPAEntity> clienteJPAEntities = new ArrayList<>();
        try {
            Session session = abrirSession();

            if (session != null){
                clienteJPAEntities = session.createNativeQuery("SELECT * FROM ClientesJPA WHERE cliente_id = ?",ClienteJPAEntity.class).setParameter(1,id).list();
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clienteJPA = clienteJPAEntities.get(0);

        cliente.setId(clienteJPA.getId());
        cliente.setNombre(clienteJPA.getNombre());
        cliente.setCorreo(clienteJPA.getCorreo());
        cliente.setTelefono(clienteJPA.getTelefono());

        return cliente;
    }

    public boolean existeCliente (int id) throws SQLException {
        List<ClienteJPAEntity> clienteJPAEntities = new ArrayList<>();
        try {
            Session session = abrirSession();

            if (session != null){
                clienteJPAEntities = session.createNativeQuery("SELECT * FROM ClientesJPA WHERE cliente_id = ?",ClienteJPAEntity.class).setParameter(1,id).list();
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (clienteJPAEntities.size() == 1)
            return true;

        return false;
    }

    public boolean eliminarCliente(int id) throws SQLException {
        List<ClienteJPAEntity> clienteJPAEntities = new ArrayList<>();
        Transaction transaction = null;
        try {
            Session session = abrirSession();

            if (session != null){
                transaction = session.beginTransaction();
                clienteJPAEntities = session.createNativeQuery("SELECT * FROM ClientesJPA WHERE cliente_id = ?",ClienteJPAEntity.class).setParameter(1,id).list();
                session.delete(clienteJPAEntities.get(0));
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
