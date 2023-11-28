package org.example;

import java.sql.*;

public class metodosPostgre {
    private static final String URL = "jdbc:postgresql://localhost:5432/EmpleadosInstituto";
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
            System.out.println("\n-----------------\n\t" + mensaje + "\n-----------------");

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            cerrarBBDD(statement);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
    }

    public void BBDDStatement(String sql) {
        conectarBBDD();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            cerrarBBDD(statement);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
    }

    public int BBDDCount(String sql) {
        conectarBBDD();
        try {
            ResultSet rs = this.statement.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1);
            }
            cerrarBBDD(statement);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
        return 0;
    }

    public void BBDDProcedimiento(String sql) {
        conectarBBDD();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.executeUpdate();
            ResultSet rs = this.statement.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            cerrarBBDD(statement);
            cerrarBBDD(statement);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
    }

    public void cerrarBBDD(Statement conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
