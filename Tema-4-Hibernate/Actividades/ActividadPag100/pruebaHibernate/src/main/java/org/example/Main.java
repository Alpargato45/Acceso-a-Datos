package org.example;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Main {

    static Session abrirSession() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            throw new Exception("Error abriendo la sesion");
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