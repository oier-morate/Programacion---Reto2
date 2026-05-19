package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VotoDAO {

    public static void crearTabla() throws SQLException {
        String sql =
            "CREATE TABLE IF NOT EXISTS votos (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "libro_id INT NOT NULL," +
            "correo VARCHAR(255) NOT NULL," +
            "fecha VARCHAR(20) NOT NULL," +
            "UNIQUE KEY voto_unico (libro_id, correo)," +
            "FOREIGN KEY (libro_id) REFERENCES libros(id))";
        try (Statement st = BaseDeDatos.getConnection().createStatement()) {
            st.executeUpdate(sql);
        }
    }

    public static boolean yaVoto(int libroId, String correo) throws SQLException {
        String sql = "SELECT id FROM votos WHERE libro_id=? AND correo=?";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ps.setString(2, correo.toLowerCase().trim());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public static void votar(int libroId, String correo) throws SQLException {
        String fecha = java.time.LocalDateTime.now()
            .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String sql = "INSERT INTO votos (libro_id, correo, fecha) VALUES (?,?,?)";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ps.setString(2, correo.toLowerCase().trim());
            ps.setString(3, fecha);
            ps.executeUpdate();
        }
    }

    public static void cancelarVoto(int libroId, String correo) throws SQLException {
        String sql = "DELETE FROM votos WHERE libro_id=? AND correo=?";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ps.setString(2, correo.toLowerCase().trim());
            ps.executeUpdate();
        }
    }

    public static int contarVotos(int libroId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM votos WHERE libro_id=?";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public static List<String[]> rankingVotos() throws SQLException {
        List<String[]> lista = new ArrayList<>();
        String sql =
            "SELECT l.id, l.titulo, l.autor, COUNT(v.id) as votos " +
            "FROM libros l LEFT JOIN votos v ON v.libro_id = l.id " +
            "GROUP BY l.id, l.titulo, l.autor " +
            "ORDER BY votos DESC";
        try (Statement st = BaseDeDatos.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    String.valueOf(rs.getInt("votos"))
                });
            }
        }
        return lista;
    }
}