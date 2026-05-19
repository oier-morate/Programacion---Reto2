package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public static Libro insertar(Libro l) throws SQLException {
        String sql = "INSERT INTO libros (titulo, autor, anio, genero, isbn, disponible, fecha_alta) VALUES (?,?,?,?,?,1,?)";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, l.titulo);
            ps.setString(2, l.autor);
            ps.setInt   (3, l.anio);
            ps.setString(4, l.genero);
            ps.setString(5, l.isbn);
            ps.setString(6, l.fechaAlta);
            ps.executeUpdate();
            ResultSet rk = ps.getGeneratedKeys();
            if (rk.next()) l.id = rk.getInt(1);
        }
        return l;
    }

    public static List<Libro> listarTodos() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros ORDER BY id";
        try (Statement st = BaseDeDatos.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public static List<Libro> listarDisponibles() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE disponible=1 ORDER BY titulo";
        try (Statement st = BaseDeDatos.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public static Libro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM libros WHERE id=?";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    public static List<Libro> buscar(String termino) throws SQLException {
        List<Libro> lista = new ArrayList<>();
        String like = "%" + termino.toLowerCase() + "%";
        String sql  = "SELECT * FROM libros WHERE LOWER(titulo) LIKE ? OR LOWER(autor) LIKE ? OR LOWER(genero) LIKE ? ORDER BY titulo";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public static void setDisponible(int id, boolean disponible) throws SQLException {
        String sql = "UPDATE libros SET disponible=? WHERE id=?";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, disponible ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public static void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM libros WHERE id=?";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static int contarTodos() throws SQLException {
        ResultSet rs = BaseDeDatos.getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM libros");
        return rs.next() ? rs.getInt(1) : 0;
    }

    public static int contarDisponibles() throws SQLException {
        ResultSet rs = BaseDeDatos.getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM libros WHERE disponible=1");
        return rs.next() ? rs.getInt(1) : 0;
    }

    private static Libro mapear(ResultSet rs) throws SQLException {
        Libro l = new Libro();
        l.id         = rs.getInt("id");
        l.titulo     = rs.getString("titulo");
        l.autor      = rs.getString("autor");
        l.anio       = rs.getInt("anio");
        l.genero     = rs.getString("genero");
        l.isbn       = rs.getString("isbn");
        l.disponible = rs.getInt("disponible") == 1;
        l.fechaAlta  = rs.getString("fecha_alta");
        return l;
    }
}
