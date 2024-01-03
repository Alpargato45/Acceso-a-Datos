package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    static Session abrirSession() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            throw new Exception("Error abriendo la sesion");
        }else {
            System.out.println("Conexión Establecida");
        }
        return session;
    }

    public static void main(String[] args) {
        try {
            Session  miSession = abrirSession();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}