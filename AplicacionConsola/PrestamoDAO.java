package biblioteca;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static Prestamo registrarPrestamo(int libroId, String lector) throws SQLException {
        String sql = "INSERT INTO prestamos (libro_id, lector, fecha_prestamo, devuelto) VALUES (?,?,?,0)";
        String fecha = LocalDateTime.now().format(FMT);
        Prestamo p = new Prestamo();
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1, libroId);
            ps.setString(2, lector);
            ps.setString(3, fecha);
            ps.executeUpdate();
            ResultSet rk = ps.getGeneratedKeys();
            if (rk.next()) p.id = rk.getInt(1);
        }
        p.libroId       = libroId;
        p.lector        = lector;
        p.fechaPrestamo = fecha;
        p.devuelto      = false;
        LibroDAO.setDisponible(libroId, false);
        return p;
    }

    public static boolean registrarDevolucion(int prestamoId) throws SQLException {
        String sql = "UPDATE prestamos SET devuelto=1, fecha_devolucion=? WHERE id=? AND devuelto=0";
        String fecha = LocalDateTime.now().format(FMT);
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(sql)) {
            ps.setString(1, fecha);
            ps.setInt   (2, prestamoId);
            int rows = ps.executeUpdate();
            if (rows == 0) return false;
        }
        // mark book available again
        String q = "SELECT libro_id FROM prestamos WHERE id=?";
        try (PreparedStatement ps = BaseDeDatos.getConnection().prepareStatement(q)) {
            ps.setInt(1, prestamoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) LibroDAO.setDisponible(rs.getInt(1), true);
        }
        return true;
    }

    public static List<Prestamo> listarActivos() throws SQLException {
        return listar("WHERE p.devuelto=0");
    }

    public static List<Prestamo> listarTodos() throws SQLException {
        return listar("");
    }

    private static List<Prestamo> listar(String where) throws SQLException {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT p.*, l.titulo FROM prestamos p JOIN libros l ON l.id=p.libro_id " + where + " ORDER BY p.id DESC";
        try (Statement st = BaseDeDatos.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.id              = rs.getInt("id");
                p.libroId         = rs.getInt("libro_id");
                p.tituloLibro     = rs.getString("titulo");
                p.lector          = rs.getString("lector");
                p.fechaPrestamo   = rs.getString("fecha_prestamo");
                p.fechaDevolucion = rs.getString("fecha_devolucion");
                p.devuelto        = rs.getInt("devuelto") == 1;
                lista.add(p);
            }
        }
        return lista;
    }

    public static int contarActivos() throws SQLException {
        ResultSet rs = BaseDeDatos.getConnection().createStatement()
            .executeQuery("SELECT COUNT(*) FROM prestamos WHERE devuelto=0");
        return rs.next() ? rs.getInt(1) : 0;
    }

    public static int contarTodos() throws SQLException {
        ResultSet rs = BaseDeDatos.getConnection().createStatement()
            .executeQuery("SELECT COUNT(*) FROM prestamos");
        return rs.next() ? rs.getInt(1) : 0;
    }
}
