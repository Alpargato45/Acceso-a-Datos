package org.acdat.jdbc;

import java.sql.*;
import java.util.*;

public class MiJDBC {
    private String driver;
    private String url;
    private String user;
    private String password;
    private Connection conn;
    private Statement statement;

    public Connection getConnection() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }

    public MiJDBC() {
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/BBDDViajes";
        this.user = "postgres";
        this.password = "patata";
    }

    public void abrirConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                this.conn = DriverManager.getConnection(url, user, password);
                this.statement = conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void IniciarTransaccion() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commitTransaccion() {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollbackTransaccion () {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearTabla(String nombreTabla, List<String> parametros) throws SQLException {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + nombreTabla + " (");

        for (int i = 0; i < parametros.size(); i++) {
            sql.append(parametros.get(i));

            if (i < parametros.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");
            statement.execute(sql.toString());
            System.out.println("Tabla creada exitosamente.");
        cerrarConexion();
    }

    public void insertar(String tabla, Object... valores) throws SQLException {

        StringBuilder sql = new StringBuilder("INSERT INTO " + tabla + " VALUES (");
        for (int i = 0; i < valores.length; i++) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append("?");
        }
        sql.append(")");

        PreparedStatement ps = conn.prepareStatement(sql.toString());
        for (int i = 0; i < valores.length; i++) {
            ps.setObject(i + 1, valores[i]);
        }
        ps.executeUpdate();

        System.out.println("Los valores se insertaron correctamente.");
        ps.close();
        cerrarConexion();
    }

    public void insertarDEFAULT(String tabla, Object... valores) throws SQLException {

        StringBuilder sql = new StringBuilder("INSERT INTO " + tabla + " VALUES (DEFAULT");
        for (int i = 0; i < valores.length; i++) {
            sql.append(", ?");
        }
        sql.append(")");

        PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < valores.length; i++) {
            ps.setObject(i + 1, valores[i]);
        }
        ps.executeUpdate();

        System.out.println("Los valores se insertaron correctamente.");
        ps.close();
        cerrarConexion();
    }

}
