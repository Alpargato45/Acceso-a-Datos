package org.acdat.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiJDBC {
    protected String driver;
    protected String url;
    protected String user;
    protected String password;
    protected Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public MiJDBC() {
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/BBDDViajes";
        this.user = "postgres";
        this.password = "iesbelen";
    }

    public boolean abrirConexion() {
        boolean respuesta = true;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            respuesta = false;
            System.out.println("Error de conexión");
            System.out.println("Código de error: " + e.getErrorCode());
            System.out.println("Mensaje de error: " + e.getMessage());
            System.out.println("Error SQL: " + e.getSQLState());
        } catch (ClassNotFoundException e) {
            respuesta = false;
            System.out.println("Error Driver no encontrado");
            System.out.println(e.getMessage());
        } finally {
            return respuesta;
        }
    }

    public boolean cerrarConexion() {
        boolean respuesta = true;
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(("Error al cerrar la conexión"));
                System.out.println("Código de error: " + e.getErrorCode());
                System.out.println("Mensaje de error: " + e.getMessage());
                System.out.println("Error SQL: " + e.getSQLState());
                respuesta = false;
            }
        }
        return respuesta;
    }

    public void sqlDDL (String ddlSQL) {

    }

    public void IniciarTransaccion() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commitTransaccion() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollbackTransaccion () {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
