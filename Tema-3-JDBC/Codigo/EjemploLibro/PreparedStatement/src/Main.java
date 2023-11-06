import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String password = "patata";

        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO asignaturas(nombre, anyo) VALUES (?,?)");
        pstmt.setString(1, "Formación y orientación laboral");
        pstmt.setInt(2, 1);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}