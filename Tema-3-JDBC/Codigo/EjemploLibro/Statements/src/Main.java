import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
            String user = "postgres";
            String password = "patata";

            Connection conn = DriverManager.getConnection(url,user,password);
            Statement statement = conn.createStatement();
            String SQLSentence = "SELECT * FROM Asignaturas";
            ResultSet rs = statement.executeQuery(SQLSentence);
            System.out.println("Código" + "\t" + "Nombre");
            System.out.println("-----------------------------------------------");
            while(rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }
            rs.close();
            conn.close();
        }
    }
    //DA FALLO, NO DETECTA EL CLASS.FORNAME