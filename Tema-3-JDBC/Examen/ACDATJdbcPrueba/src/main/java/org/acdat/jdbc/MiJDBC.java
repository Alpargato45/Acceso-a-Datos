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
        this.url = "jdbc:postgresql://localhost:5432/TuViajeFindeCurso";
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
    }

    public void añadirTablas() {
        try {
        List<String> destinos = new ArrayList<>();
        destinos.add("destino_id SERIAL PRIMARY KEY,\n" +
                "                          nombre_destino VARCHAR(100) NOT NULL,\n" +
                "                          descripcion TEXT,\n" +
                "                          costo_estadia NUMERIC");
            crearTabla("destinos",destinos);

        List<String> clientes = new ArrayList<>();
        clientes.add("cliente_id SERIAL PRIMARY KEY,\n" +
                "                          nombre_cliente VARCHAR(100) NOT NULL,\n" +
                "                          correo_cliente VARCHAR(100) UNIQUE,\n" +
                "                          telefono_cliente VARCHAR(20)");
        crearTabla("clientes",clientes);

        List<String> agencias = new ArrayList<>();
        agencias.add("agencia_id SERIAL PRIMARY KEY,\n" +
                "                          nombre_agencia VARCHAR(100) NOT NULL,\n" +
                "                          direccion_agencia TEXT,\n" +
                "                          telefono_agencia VARCHAR(20)");
        crearTabla("agencias",agencias);

        List<String> clientes_destinos = new ArrayList<>();
        clientes_destinos.add("cliente_id INTEGER REFERENCES clientes(cliente_id),\n" +
                "                                   destino_id INTEGER REFERENCES destinos(destino_id),\n" +
                "                                   PRIMARY KEY (cliente_id, destino_id)");
        crearTabla("clientes_destinos",clientes_destinos);

        List<String> destinos_agencias = new ArrayList<>();
        destinos_agencias.add("destino_id INTEGER REFERENCES destinos(destino_id),\n" +
                "                                   agencia_id INTEGER REFERENCES agencias(agencia_id),\n" +
                "                                   PRIMARY KEY (destino_id, agencia_id)");
        crearTabla("destinos_agencias",destinos_agencias);

        List<String> vuelos = new ArrayList<>();
        vuelos.add("vuelo_id SERIAL PRIMARY KEY,\n" +
                "                        origen VARCHAR(50),\n" +
                "                        destino VARCHAR(50),\n" +
                "                        fecha_salida DATE,\n" +
                "                        fecha_llegada DATE,\n" +
                "                        costo NUMERIC(10, 2)");
        crearTabla("vuelos",vuelos);

        List<String> clientes_vuelos = new ArrayList<>();
        clientes_vuelos.add("cliente_id INTEGER REFERENCES clientes(cliente_id),\n" +
                "                                 vuelo_id INTEGER REFERENCES vuelos(vuelo_id),\n" +
                "                                 PRIMARY KEY (cliente_id, vuelo_id)");
        crearTabla("clientes_vuelos",clientes_vuelos);
        commitTransaccion();
        } catch (SQLException e) {
            rollbackTransaccion();
            throw new RuntimeException(e);
        }
    }

    public void BBDDStatement(String sql) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
    }

    public void añadirInsert() {
        BBDDStatement("INSERT INTO destinos (nombre_destino, descripcion, costo_estadia) VALUES\n" +
                "                                                                      ('París', 'Ciudad del amor y la moda', 1500),\n" +
                "                                                                      ('Tokio', 'Capital de Japón con una mezcla única de tradición y tecnología', 2000),\n" +
                "                                                                      ('Nueva York', 'La Gran Manzana con rascacielos icónicos', 1800),\n" +
                "                                                                      ('Roma', 'Cuna de la civilización romana y arte renacentista', 1600),\n" +
                "                                                                      ('Sídney', 'Puente del puerto y la Ópera, destino emblemático en Australia', 2200),\n" +
                "                                                                      ('Barcelona', 'Arquitectura modernista y playas mediterráneas', 1700),\n" +
                "                                                                      ('Kioto', 'Antigua capital de Japón con templos y jardines zen', 1900),\n" +
                "                                                                      ('Marrakech', 'Ciudad imperial de Marruecos con zocos y palacios', 1400),\n" +
                "                                                                      ('Estambul', 'Puente entre Europa y Asia con la Mezquita Azul y Hagia Sophia', 2000),\n" +
                "                                                                      ('Ciudad del Cabo', 'Ciudad costera con la Montaña de la Mesa', 2100),\n" +
                "                                                                      ('Praga', 'Ciudad de las cien torres con un encanto medieval', 1600),\n" +
                "                                                                      ('Bali', 'Isla indonesia famosa por sus playas y arrozales', 1800),\n" +
                "                                                                      ('Machu Picchu', 'Ciudadela inca en lo alto de los Andes', 2500),\n" +
                "                                                                      ('Toronto', 'Ciudad cosmopolita con la Torre CN y las Cataratas del Niágara', 1900),\n" +
                "                                                                      ('Dubái', 'Ciudad del lujo y la modernidad en medio del desierto', 2300);\n" +
                "\n"
              );
        BBDDStatement("INSERT INTO agencias (nombre_agencia, direccion_agencia, telefono_agencia) VALUES\n" +
                "                                                                               ('Viajes Fantásticos', 'Calle Principal 123', '+999888777'),\n" +
                "                                                                               ('Aventuras Globales', 'Avenida Central 456', '+333222111'),\n" +
                "                                                                               ('Destinos Soñados', 'Plaza de la Libertad 789', '+555444333'),\n" +
                "                                                                               ('Mundo Viajero', 'Calle Viajera 101', '+777666555'),\n" +
                "                                                                               ('Turismo Excelente', 'Avenida de los Sueños 202', '+888777666'),\n" +
                "                                                                               ('Rutas Inolvidables', 'Paseo del Descanso 303', '+111000999'),\n" +
                "                                                                               ('Viajes y Más', 'Carrera Aventurera 505', '+222111000'),\n" +
                "                                                                               ('Explora el Mundo', 'Rincón del Viajero 606', '+444333222'),\n" +
                "                                                                               ('Destinos Únicos', 'Calle de la Aventura 707', '+666555444'),\n" +
                "                                                                               ('Aventuras Extremas', 'Plaza del Viajero 808', '+000999888');");
        BBDDStatement("INSERT INTO clientes (nombre_cliente, correo_cliente, telefono_cliente) VALUES\n" +
                "                                                                            ('Luisa Martínez', 'luisa@email.com', '+1234567890'),\n" +
                "                                                                            ('Pedro González', 'pedro@email.com', '+2345678901'),\n" +
                "                                                                            ('María Rodríguez', 'maria@email.com', '+3456789012'),\n" +
                "                                                                            ('Alejandro López', 'alejandro@email.com', '+4567890123'),\n" +
                "                                                                            ('Ana Sánchez', 'ana@email.com', '+5678901234'),\n" +
                "                                                                            ('Javier Ramírez', 'javier@email.com', '+6789012345'),\n" +
                "                                                                            ('Isabel Fernández', 'isabel@email.com', '+7890123456'),\n" +
                "                                                                            ('Carlos Pérez', 'carlos@email.com', '+8901234567'),\n" +
                "                                                                            ('Elena Gómez', 'elena@email.com', '+9012345678'),\n" +
                "                                                                            ('Andrés Díaz', 'andres@email.com', '+0123456789'),\n" +
                "                                                                            ('Laura Torres', 'laura@email.com', '+9876543210'),\n" +
                "                                                                            ('Roberto Herrera', 'roberto@email.com', '+8765432109'),\n" +
                "                                                                            ('Silvia Castro', 'silvia@email.com', '+7654321098'),\n" +
                "                                                                            ('Juan García', 'juan@email.com', '+6543210987'),\n" +
                "                                                                            ('Carmen Ruiz', 'carmen@email.com', '+5432109876'),\n" +
                "                                                                            ('Fernando Méndez', 'fernando@email.com', '+4321098765'),\n" +
                "                                                                            ('Sara Vargas', 'sara@email.com', '+3210987654'),\n" +
                "                                                                            ('Héctor Navarro', 'hector@email.com', '+2109876543'),\n" +
                "                                                                            ('Lucía Medina', 'lucia@email.com', '+1098765432'),\n" +
                "                                                                            ('Diego Ríos', 'diego@email.com', '+9876543210'),\n" +
                "                                                                            ('Patricia Silva', 'patricia@email.com', '+8765432109'),\n" +
                "                                                                            ('Adrián Mendoza', 'adrian@email.com', '+7654321098'),\n" +
                "                                                                            ('Natalia Romero', 'natalia@email.com', '+6543210987'),\n" +
                "                                                                            ('Martín Castro', 'martin@email.com', '+5432109876'),\n" +
                "                                                                            ('Eva Morales', 'eva@email.com', '+4321098765'),\n" +
                "                                                                            ('Raúl Guzmán', 'raul@email.com', '+3210987654'),\n" +
                "                                                                            ('Lorena Ortega', 'lorena@email.com', '+2109876543'),\n" +
                "                                                                            ('Pablo Roca', 'pablo@email.com', '+1098765432'),\n" +
                "                                                                            ('Beatriz León', 'beatriz@email.com', '+9876543210'),\n" +
                "                                                                            ('Andrea Díaz', 'andrea@email.com', '+8765432109'),\n" +
                "                                                                            ('Óscar Muñoz', 'oscar@email.com', '+7654321098'),\n" +
                "                                                                            ('Marta Cordero', 'marta@email.com', '+6543210987'),\n" +
                "                                                                            ('Ricardo Soto', 'ricardo@email.com', '+5432109876'),\n" +
                "                                                                            ('Cristina Paredes', 'cristina@email.com', '+4321098765'),\n" +
                "                                                                            ('Jorge Gallego', 'jorge@email.com', '+3210987654'),\n" +
                "                                                                            ('Natalie Rojas', 'natalie@email.com', '+2109876543'),\n" +
                "                                                                            ('Gabriel Gil', 'gabriel@email.com', '+1098765432'),\n" +
                "                                                                            ('Ana Rosa', 'anarosa@email.com', '+9876543210'),\n" +
                "                                                                            ('Manuel Mora', 'manuel@email.com', '+8765432109'),\n" +
                "                                                                            ('Rosa Serrano', 'rosa@email.com', '+7654321098'),\n" +
                "                                                                            ('Hugo Guerra', 'hugo@email.com', '+6543210987'),\n" +
                "                                                                            ('Julia Torres', 'julia@email.com', '+5432109876'),\n" +
                "                                                                            ('Eduardo Alvarado', 'eduardo@email.com', '+4321098765'),\n" +
                "                                                                            ('Mónica Pinto', 'monica@email.com', '+3210987654'),\n" +
                "                                                                            ('Gustavo Ramos', 'gustavo@email.com', '+2109876543'),\n" +
                "                                                                            ('Paola Jiménez', 'paola@email.com', '+1098765432'),\n" +
                "                                                                            ('Daniel Salgado', 'daniel@email.com', '+9876543210');");
        BBDDStatement("INSERT INTO clientes_destinos (cliente_id, destino_id)\n" +
                "VALUES\n" +
                "    (1, 1), (1, 3), (1, 5), (1, 7), (1, 9),\n" +
                "    (2, 2), (2, 4), (2, 6), (2, 8), (2, 10),\n" +
                "    (3, 3), (3, 5), (3, 7), (3, 9), (3, 11),\n" +
                "    -- ... continúa con los demás usuarios y destinos según sea necesario.\n" +
                "    (10, 10), (10, 12), (10, 14), (10, 11), (10, 2);");
        BBDDStatement("INSERT INTO destinos_agencias (destino_id, agencia_id)\n" +
                "VALUES\n" +
                "    (1, 1), (1, 3), (1, 5), (1, 7), (1, 9),\n" +
                "    (2, 2), (2, 4), (2, 6), (2, 8), (2, 10),\n" +
                "    (3, 3), (3, 5), (3, 7), (3, 9), (3, 10),\n" +
                "    -- ... continúa con los demás destinos y agencias según sea necesario.\n" +
                "    (10, 10), (10, 2), (10, 4);");
        BBDDStatement("INSERT INTO vuelos (origen, destino, fecha_salida, fecha_llegada, costo) VALUES\n" +
                "                                                                             ('Ciudad A', 'Ciudad B', '2023-12-01', '2023-12-05', 500.00),\n" +
                "                                                                             ('Ciudad C', 'Ciudad D', '2023-12-10', '2023-12-15', 700.00),\n" +
                "                                                                             ('Ciudad E', 'Ciudad F', '2023-12-20', '2023-12-25', 900.00);");
        BBDDStatement("INSERT INTO clientes (nombre_cliente, correo_cliente, telefono_cliente) VALUES\n" +
                "                                                                            ('Juan Pérez', 'juan2@email.com', '+123456789'),\n" +
                "                                                                            ('Ana López', 'ana12@email.com', '+987654321');");
        BBDDStatement("INSERT INTO clientes_vuelos (cliente_id, vuelo_id) VALUES\n" +
                "                                                       (1, 1), (1, 2),\n" +
                "                                                       (2, 2), (2, 3);");
        commitTransaccion();
    }

    public void BBDDQuery(String sql) {
        abrirConexion();
        try {
            ResultSet rs = this.statement.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
    }
}