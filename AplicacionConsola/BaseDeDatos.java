package biblioteca;

import java.sql.*;

public class BaseDeDatos {

    private static final String URL = "jdbc:mysql://datos.somorrostro.com:3306/2526DAMEquipo02";
    private static final String user = "2526DAMEquipo02";
    private static final String pass = "2526DAMEquipo02";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, user, pass);
            connection.setAutoCommit(true);
        }
        return connection;
    }

    public static void init() throws SQLException {
        try (Statement st = getConnection().createStatement()) {
            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS libros (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "titulo VARCHAR(255) NOT NULL," +
                "autor VARCHAR(255) NOT NULL," +
                "anio INT NOT NULL," +
                "genero VARCHAR(100) NOT NULL," +
                "isbn VARCHAR(50)," +
                "disponible INT NOT NULL DEFAULT 1," +
                "fecha_alta VARCHAR(20) NOT NULL)"
            );
            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS prestamos (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "libro_id INT NOT NULL," +
                "lector VARCHAR(255) NOT NULL," +
                "fecha_prestamo VARCHAR(20) NOT NULL," +
                "fecha_devolucion VARCHAR(20)," +
                "devuelto INT NOT NULL DEFAULT 0," +
                "FOREIGN KEY (libro_id) REFERENCES libros(id))"
            );
            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS votos (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "libro_id INT NOT NULL," +
                "correo VARCHAR(100) NOT NULL," +
                "fecha VARCHAR(20) NOT NULL," +
                "UNIQUE KEY voto_unico (libro_id, correo)," +
                "FOREIGN KEY (libro_id) REFERENCES libros(id))"
            );
        }
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException ignored) {}
    }
}