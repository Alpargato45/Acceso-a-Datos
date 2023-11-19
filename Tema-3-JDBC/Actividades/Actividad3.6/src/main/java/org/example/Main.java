package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String password = "patata";

        try {
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement createTableStatement = conn.createStatement();
                Statement addColumnStatement = conn.createStatement();
                PreparedStatement insertValuesStatement = conn.prepareStatement("INSERT INTO cursos (nombre) VALUES (?), (?)");
                PreparedStatement showTableStatement = conn.prepareStatement("SELECT * FROM cursos");
            createTableStatement.executeUpdate("CREATE TABLE IF NOT EXISTS cursos (codigo SERIAL PRIMARY KEY, nombre VARCHAR(90) NOT NULL)");
            System.out.println("Tabla 'cursos' creada correctamente.");

            insertValuesStatement.setString(1, "Desarrollo de aplicaciones multiplataforma");
            insertValuesStatement.setString(2, "Desarrollo web");
            insertValuesStatement.executeUpdate();
            System.out.println("Valores insertados en la tabla 'cursos' correctamente.");

            addColumnStatement.executeUpdate("ALTER TABLE asignaturas ADD COLUMN IF NOT EXISTS curso INT REFERENCES cursos(codigo)");
            System.out.println("Nueva columna 'curso' agregada a la tabla 'asignaturas'.");

            try (PreparedStatement insertAsignaturasStatement = conn.prepareStatement("INSERT INTO asignaturas (nombre, curso) VALUES (?, ?), (?, ?)")) {
                insertAsignaturasStatement.setString(1, "Asignatura1");
                insertAsignaturasStatement.setInt(2, 1); // Aquí debes especificar el código del curso correspondiente
                insertAsignaturasStatement.setString(3, "Asignatura2");
                insertAsignaturasStatement.setInt(4, 2); // Aquí debes especificar el código del curso correspondiente
                insertAsignaturasStatement.executeUpdate();
                System.out.println("Valores insertados en la tabla 'asignaturas' correctamente.");
            }
            try (ResultSet rs = showTableStatement.executeQuery()) {
                System.out.println("Código" + "\t" + "Nombre");
                System.out.println("-----------------------------------------------");
                while (rs.next()) {
                    System.out.println(rs.getInt("codigo") + "\t" + rs.getString("nombre"));
                }
            }
            try (PreparedStatement showAsignaturasStatement = conn.prepareStatement("SELECT * FROM asignaturas")) {
                try (ResultSet rs = showAsignaturasStatement.executeQuery()) {
                    System.out.println("Código" + "\t" + "Nombre" + "\t" + "Curso");
                    System.out.println("-----------------------------------------------");
                    while (rs.next()) {
                        System.out.println(rs.getInt("codigo") + "\t" + rs.getString("nombre") + "\t" + rs.getInt("curso"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}