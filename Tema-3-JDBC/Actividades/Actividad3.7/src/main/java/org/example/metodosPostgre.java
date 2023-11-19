package org.example;

import java.sql.*;

public class metodosPostgre {
    private static final String URL = "jdbc:postgresql://localhost:5432/EmpleadosInstiuto";
    private static final String USER = "postgres";
    private static final String PASSWORD = "patata";
    private Connection conn;
    private Statement statement;

    public void conectarBBDD() {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
                this.statement = conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void BBDDQuery(String sql, String mensaje) {
        conectarBBDD();
        try {
            ResultSet rs = this.statement.executeQuery(sql);
            System.out.println(mensaje);

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            cerrarBBDD(statement,rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
    }

    public void cerrarBBDD(Statement conn, ResultSet rs) {
        try {
            rs.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
