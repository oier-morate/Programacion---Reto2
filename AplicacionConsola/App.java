package biblioteca;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            BaseDeDatos.init();
        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo inicializar la base de datos: " + e.getMessage());
            System.exit(1);
        }

        Terminal.banner();
        System.out.println(Terminal.BLANCO + Terminal.BOLD + "  Sistema de Gestion Bibliotecaria  " + Terminal.RESET + Terminal.DIM + "v1.0 - SQLite" + Terminal.RESET);
        Terminal.lineaDoble();
        System.out.println(Terminal.DIM + "  Base de datos: biblioteca.db" + Terminal.RESET);
        Terminal.espacio();

        boolean running = true;
        while (running) {
            mostrarMenu();
            String opcion = leerLinea("Selecciona una opcion").trim().toLowerCase();
            switch (opcion) {
                case "1": listarLibros();     break;
                case "2": aniadirLibro();     break;
                case "3": buscarLibro();      break;
                case "4": eliminarLibro();    break;
                case "5": prestarLibro();     break;
                case "6": devolverLibro();    break;
                case "7": verPrestamos();     break;
                case "8": verEstadisticas();  break;
                case "9": cargarDatosPrueba(); break;
                case "v": votarLibro();        break;
                case "0": running = false;    break;
                default:
                    System.out.println(Terminal.AMARILLO + "  [!] Opcion no reconocida. Elige un numero del 0 al 9." + Terminal.RESET);
            }
            if (running) {
                Terminal.espacio();
                System.out.print(Terminal.DIM + "  Pulsa ENTER para continuar..." + Terminal.RESET);
                sc.nextLine();
            }
        }

        BaseDeDatos.close();
        System.out.println(Terminal.CIAN_B + Terminal.BOLD + "\n  Hasta pronto. Biblioteca Gurekin cerrado.\n" + Terminal.RESET);
    }

    // =============================================
    //  MENU PRINCIPAL
    // =============================================
    private static void mostrarMenu() {
        try {
            int totalLibros  = LibroDAO.contarTodos();
            int disponibles  = LibroDAO.contarDisponibles();
            int prestados    = totalLibros - disponibles;
            int prestActivos = PrestamoDAO.contarActivos();

            Terminal.espacio();
            System.out.println(Terminal.CIAN_B + Terminal.BOLD + "  *** BIBLIOTECA GUREKIN - MENU PRINCIPAL ***" + Terminal.RESET);
            Terminal.lineaDoble();
            System.out.println(
                "  " + Terminal.DIM + "Libros: " + Terminal.RESET + Terminal.AMARILLO + totalLibros +
                Terminal.RESET + Terminal.DIM + "   Disponibles: " + Terminal.RESET + Terminal.VERDE_B + disponibles +
                Terminal.RESET + Terminal.DIM + "   Prestados: " + Terminal.RESET + Terminal.ROJO_B + prestados +
                Terminal.RESET + Terminal.DIM + "   Prestamos activos: " + Terminal.RESET + Terminal.AZUL_B + prestActivos + Terminal.RESET
            );
            Terminal.linea();
            Terminal.espacio();

            System.out.println(Terminal.VERDE_B + "  [1]" + Terminal.RESET + " Listar libros             " + Terminal.AZUL_B + "  [5]" + Terminal.RESET + " Prestar un libro");
            System.out.println(Terminal.VERDE_B + "  [2]" + Terminal.RESET + " Aniadir libro             " + Terminal.AZUL_B + "  [6]" + Terminal.RESET + " Registrar devolucion");
            System.out.println(Terminal.VERDE_B + "  [3]" + Terminal.RESET + " Buscar libro              " + Terminal.AZUL_B + "  [7]" + Terminal.RESET + " Ver prestamos");
            System.out.println(Terminal.VERDE_B + "  [4]" + Terminal.RESET + " Eliminar libro            " + Terminal.MAGENTA_B + "  [8]" + Terminal.RESET + " Estadisticas");
            System.out.println("                                " + Terminal.MAGENTA_B + "  [9]" + Terminal.RESET + " Cargar datos prueba");
            System.out.println(Terminal.ROJO_B  + "  [0]" + Terminal.RESET + " Salir                     " + Terminal.AMARILLO_B + "  [V]" + Terminal.RESET + " Votar un libro");

            Terminal.espacio();
            Terminal.linea();
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  1 - LISTAR LIBROS
    // =============================================
    private static void listarLibros() {
        try {
            List<Libro> libros = LibroDAO.listarTodos();
            Terminal.titulo("CATALOGO COMPLETO - " + libros.size() + " libro(s)");
            if (libros.isEmpty()) {
                System.out.println(Terminal.AMARILLO + "  [!] No hay libros. Usa [2] para aniadir o [9] para datos de prueba." + Terminal.RESET);
                return;
            }
            Terminal.tablaLibrosHeader();
            for (Libro l : libros) Terminal.tablaLibroFila(l);
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  2 - ANIADIR LIBRO
    // =============================================
    private static void aniadirLibro() {
        Terminal.titulo("ANIADIR NUEVO LIBRO");

        String titulo = leerLinea("Titulo del libro");
        if (titulo.trim().isEmpty()) { System.out.println(Terminal.ROJO + "  [X] El titulo no puede estar vacio." + Terminal.RESET); return; }

        String autor = leerLinea("Autor");
        if (autor.trim().isEmpty()) { System.out.println(Terminal.ROJO + "  [X] El autor no puede estar vacio." + Terminal.RESET); return; }

        String anioStr = leerLinea("Anio de publicacion");
        int anio;
        try {
            anio = Integer.parseInt(anioStr.trim());
        } catch (NumberFormatException e) {
            System.out.println(Terminal.ROJO + "  [X] El anio debe ser un numero." + Terminal.RESET);
            return;
        }
        if (anio < 1000 || anio > 2099) {
            System.out.println(Terminal.ROJO + "  [X] Anio fuera de rango (1000-2099)." + Terminal.RESET);
            return;
        }

        Terminal.espacio();
        System.out.println(Terminal.CIAN + "  Generos disponibles:" + Terminal.RESET);
        String[] generos = {"Ficcion", "No ficcion", "Ciencia ficcion", "Fantasia", "Misterio",
                            "Romance", "Historia", "Ciencia", "Filosofia", "Poesia", "Biografia", "Otro"};
        for (int i = 0; i < generos.length; i++) {
            System.out.println("  " + Terminal.DIM + "[" + (i+1) + "]" + Terminal.RESET + " " + generos[i]);
        }
        String generoStr = leerLinea("Elige genero (numero)");
        String genero;
        try {
            int gi = Integer.parseInt(generoStr.trim()) - 1;
            genero = (gi >= 0 && gi < generos.length) ? generos[gi] : "Otro";
        } catch (NumberFormatException e) {
            genero = generoStr.trim().isEmpty() ? "Otro" : generoStr;
        }

        String isbn = leerLinea("ISBN (opcional, ENTER para omitir)");

        try {
            Libro l = LibroDAO.insertar(new Libro(titulo, autor, anio, genero, isbn));
            Terminal.espacio();
            System.out.println(Terminal.VERDE_B + "  [OK] Libro registrado con exito." + Terminal.RESET);
            System.out.println(Terminal.MAGENTA + "  ID:     " + Terminal.RESET + Terminal.AMARILLO + "#" + String.format("%03d", l.id) + Terminal.RESET);
            System.out.println(Terminal.MAGENTA + "  Titulo: " + Terminal.RESET + l.titulo);
            System.out.println(Terminal.MAGENTA + "  Autor:  " + Terminal.RESET + l.autor);
            System.out.println(Terminal.MAGENTA + "  Anio:   " + Terminal.RESET + l.anio);
            System.out.println(Terminal.MAGENTA + "  Genero: " + Terminal.RESET + l.genero);
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  3 - BUSCAR LIBRO
    // =============================================
    private static void buscarLibro() {
        Terminal.titulo("BUSCAR LIBRO");
        String termino = leerLinea("Termino de busqueda (titulo, autor o genero)");
        if (termino.trim().isEmpty()) { System.out.println(Terminal.ROJO + "  [X] Introduce al menos un caracter." + Terminal.RESET); return; }
        try {
            List<Libro> res = LibroDAO.buscar(termino);
            Terminal.espacio();
            System.out.println(Terminal.CIAN + "  Resultados para \"" + termino + "\": " + res.size() + " encontrado(s)." + Terminal.RESET);
            if (res.isEmpty()) { System.out.println(Terminal.AMARILLO + "  [!] Sin coincidencias." + Terminal.RESET); return; }
            Terminal.tablaLibrosHeader();
            for (Libro l : res) Terminal.tablaLibroFila(l);
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  4 - ELIMINAR LIBRO
    // =============================================
    private static void eliminarLibro() {
        Terminal.titulo("ELIMINAR LIBRO");
        System.out.println(Terminal.DIM + "  Solo se pueden eliminar libros disponibles (no prestados)." + Terminal.RESET);
        Terminal.espacio();

        String idStr = leerLinea("ID del libro a eliminar");
        int id;
        try { id = Integer.parseInt(idStr.trim()); }
        catch (NumberFormatException e) { System.out.println(Terminal.ROJO + "  [X] ID invalido." + Terminal.RESET); return; }

        try {
            Libro l = LibroDAO.buscarPorId(id);
            if (l == null) { System.out.println(Terminal.ROJO + "  [X] No existe ningun libro con ese ID." + Terminal.RESET); return; }
            if (!l.disponible) { System.out.println(Terminal.ROJO + "  [X] El libro \"" + l.titulo + "\" esta prestado. Devuelvelo primero." + Terminal.RESET); return; }

            Terminal.espacio();
            System.out.println(Terminal.MAGENTA + "  Titulo: " + Terminal.RESET + l.titulo);
            System.out.println(Terminal.MAGENTA + "  Autor:  " + Terminal.RESET + l.autor);
            Terminal.espacio();
            String confirm = leerLinea(Terminal.ROJO_B + "Confirmar eliminacion? (s/N)" + Terminal.RESET);
            if (!confirm.trim().equalsIgnoreCase("s")) { System.out.println(Terminal.AMARILLO + "  [!] Operacion cancelada." + Terminal.RESET); return; }

            LibroDAO.eliminar(id);
            System.out.println(Terminal.VERDE_B + "  [OK] Libro \"" + l.titulo + "\" eliminado del catalogo." + Terminal.RESET);
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  5 - PRESTAR LIBRO
    // =============================================
    private static void prestarLibro() {
        Terminal.titulo("REGISTRAR PRESTAMO");
        try {
            List<Libro> disponibles = LibroDAO.listarDisponibles();
            if (disponibles.isEmpty()) { System.out.println(Terminal.AMARILLO + "  [!] No hay libros disponibles para prestar." + Terminal.RESET); return; }

            Terminal.tablaLibrosHeader();
            for (Libro l : disponibles) Terminal.tablaLibroFila(l);
            Terminal.espacio();

            String idStr = leerLinea("ID del libro a prestar");
            int id;
            try { id = Integer.parseInt(idStr.trim()); }
            catch (NumberFormatException e) { System.out.println(Terminal.ROJO + "  [X] ID invalido." + Terminal.RESET); return; }

            Libro l = LibroDAO.buscarPorId(id);
            if (l == null) { System.out.println(Terminal.ROJO + "  [X] No existe ese libro." + Terminal.RESET); return; }
            if (!l.disponible) { System.out.println(Terminal.ROJO + "  [X] Ese libro no esta disponible." + Terminal.RESET); return; }

            String lector = leerLinea("Nombre del lector");
            if (lector.trim().isEmpty()) { System.out.println(Terminal.ROJO + "  [X] El nombre del lector es obligatorio." + Terminal.RESET); return; }

            Prestamo p = PrestamoDAO.registrarPrestamo(id, lector);
            Terminal.espacio();
            System.out.println(Terminal.VERDE_B + "  [OK] Prestamo registrado." + Terminal.RESET);
            System.out.println(Terminal.MAGENTA + "  Prestamo #: " + Terminal.RESET + Terminal.AMARILLO + String.format("%03d", p.id) + Terminal.RESET);
            System.out.println(Terminal.MAGENTA + "  Libro:      " + Terminal.RESET + l.titulo);
            System.out.println(Terminal.MAGENTA + "  Lector:     " + Terminal.RESET + lector);
            System.out.println(Terminal.MAGENTA + "  Fecha:      " + Terminal.RESET + p.fechaPrestamo);
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  6 - DEVOLVER LIBRO
    // =============================================
    private static void devolverLibro() {
        Terminal.titulo("REGISTRAR DEVOLUCION");
        try {
            List<Prestamo> activos = PrestamoDAO.listarActivos();
            if (activos.isEmpty()) { System.out.println(Terminal.AMARILLO + "  [!] No hay prestamos activos en este momento." + Terminal.RESET); return; }

            Terminal.tablaPrestamosHeader(false);
            for (Prestamo p : activos) Terminal.tablaPrestamoFila(p, false);
            Terminal.espacio();

            String idStr = leerLinea("ID del prestamo a cerrar");
            int id;
            try { id = Integer.parseInt(idStr.trim()); }
            catch (NumberFormatException e) { System.out.println(Terminal.ROJO + "  [X] ID invalido." + Terminal.RESET); return; }

            boolean ok = PrestamoDAO.registrarDevolucion(id);
            if (!ok) { System.out.println(Terminal.ROJO + "  [X] No se encontro un prestamo activo con ese ID." + Terminal.RESET); return; }
            Terminal.espacio();
            System.out.println(Terminal.VERDE_B + "  [OK] Devolucion registrada. El libro ya esta disponible de nuevo." + Terminal.RESET);
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  7 - VER PRESTAMOS
    // =============================================
    private static void verPrestamos() {
        Terminal.titulo("HISTORIAL DE PRESTAMOS");
        try {
            List<Prestamo> todos = PrestamoDAO.listarTodos();
            if (todos.isEmpty()) { System.out.println(Terminal.AMARILLO + "  [!] No hay prestamos registrados." + Terminal.RESET); return; }
            Terminal.tablaPrestamosHeader(true);
            for (Prestamo p : todos) Terminal.tablaPrestamoFila(p, true);
            Terminal.espacio();
            long activos   = 0;
            long devueltos = 0;
            for (Prestamo p : todos) { if (p.devuelto) devueltos++; else activos++; }
            System.out.println(
                "  Total: " + Terminal.AMARILLO + todos.size() + Terminal.RESET +
                "   Activos: " + Terminal.AMARILLO_B + activos + Terminal.RESET +
                "   Devueltos: " + Terminal.VERDE + devueltos + Terminal.RESET
            );
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  8 - ESTADISTICAS
    // =============================================
    private static void verEstadisticas() {
        Terminal.titulo("ESTADISTICAS DE LA BIBLIOTECA");
        try {
            int totalLibros = LibroDAO.contarTodos();
            int disponibles = LibroDAO.contarDisponibles();
            int prestados   = totalLibros - disponibles;
            int totalPrests = PrestamoDAO.contarTodos();
            int activosP    = PrestamoDAO.contarActivos();

            System.out.println(Terminal.MAGENTA + "  Total de libros:   " + Terminal.RESET + Terminal.AMARILLO + totalLibros + Terminal.RESET);
            System.out.println(Terminal.VERDE_B  + "  Disponibles:       " + Terminal.RESET + Terminal.VERDE_B + disponibles + "  " + Terminal.barra(disponibles, totalLibros, 20) + Terminal.RESET);
            System.out.println(Terminal.ROJO_B   + "  Prestados:         " + Terminal.RESET + Terminal.ROJO_B  + prestados   + "  " + Terminal.barra(prestados, totalLibros, 20) + Terminal.RESET);
            Terminal.espacio();
            System.out.println(Terminal.MAGENTA + "  Prestamos totales: " + Terminal.RESET + Terminal.AMARILLO + totalPrests + Terminal.RESET);
            System.out.println(Terminal.AMARILLO_B + "  Activos:           " + Terminal.RESET + Terminal.AMARILLO_B + activosP + "  " + Terminal.barra(activosP, totalPrests, 20) + Terminal.RESET);
            System.out.println(Terminal.VERDE   + "  Devueltos:         " + Terminal.RESET + Terminal.VERDE + (totalPrests - activosP) + "  " + Terminal.barra(totalPrests - activosP, totalPrests, 20) + Terminal.RESET);

            if (totalLibros > 0) {
                Terminal.espacio();
                System.out.println(Terminal.CIAN + "  Distribucion por genero:" + Terminal.RESET);
                Terminal.linea();
                List<Libro> libros = LibroDAO.listarTodos();
                java.util.Map<String, Integer> genres = new java.util.LinkedHashMap<>();
                for (Libro l : libros) {
                    genres.put(l.genero, genres.getOrDefault(l.genero, 0) + 1);
                }
                for (java.util.Map.Entry<String, Integer> e : genres.entrySet()) {
                    System.out.printf("  " + Terminal.AZUL + "%-18s" + Terminal.RESET + "  %s  " + Terminal.AMARILLO + "%d%n" + Terminal.RESET,
                        e.getKey(), Terminal.barra(e.getValue(), totalLibros, 20), e.getValue());
                }
            }
        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    // =============================================
    //  9 - DATOS DE PRUEBA
    // =============================================
    private static void cargarDatosPrueba() {
        Terminal.titulo("CARGAR DATOS DE PRUEBA");
        System.out.println(Terminal.AMARILLO + "  [!] Se aniadiran 10 libros de ejemplo al catalogo." + Terminal.RESET);
        String confirm = leerLinea("Continuar? (s/N)");
        if (!confirm.trim().equalsIgnoreCase("s")) { System.out.println(Terminal.AMARILLO + "  Cancelado." + Terminal.RESET); return; }

        String[][] datos = {
            {"Cien anios de soledad",      "Gabriel Garcia Marquez",   "1967", "Ficcion"},
            {"El nombre de la rosa",       "Umberto Eco",              "1980", "Misterio"},
            {"Dune",                       "Frank Herbert",            "1965", "Ciencia ficcion"},
            {"El segnior de los anillos",  "J.R.R. Tolkien",           "1954", "Fantasia"},
            {"Sapiens",                    "Yuval Noah Harari",        "2011", "Historia"},
            {"1984",                       "George Orwell",            "1949", "Ficcion"},
            {"El principito",              "Antoine de Saint-Exupery", "1943", "Ficcion"},
            {"Cosmos",                     "Carl Sagan",               "1980", "Ciencia"},
            {"Don Quijote de la Mancha",   "Miguel de Cervantes",      "1605", "Ficcion"},
            {"Ficciones",                  "Jorge Luis Borges",        "1944", "Ficcion"},
        };

        int ok = 0;
        for (String[] d : datos) {
            try {
                Libro l = LibroDAO.insertar(new Libro(d[0], d[1], Integer.parseInt(d[2]), d[3], ""));
                System.out.println("  " + Terminal.VERDE + "[+]" + Terminal.RESET + " #" + String.format("%03d", l.id) + "  " + Terminal.AMARILLO + d[0] + Terminal.RESET);
                ok++;
            } catch (SQLException e) {
                System.out.println(Terminal.ROJO + "  [ERROR] " + d[0] + ": " + e.getMessage() + Terminal.RESET);
            }
        }
        Terminal.espacio();
        System.out.println(Terminal.VERDE_B + "  [OK] Se aniadieron " + ok + " libros de ejemplo." + Terminal.RESET);
    }
    // =============================================
    //  V - VOTAR LIBRO
    // =============================================
    private static void votarLibro() {
        Terminal.titulo("VOTAR UN LIBRO");
        try {
            java.util.List<String[]> ranking = VotoDAO.rankingVotos();
            if (ranking.isEmpty()) {
                System.out.println(Terminal.AMARILLO + "  [!] No hay libros en el catalogo." + Terminal.RESET);
                return;
            }
            System.out.println(Terminal.BOLD + Terminal.CIAN_B + "  ID     TITULO                             AUTOR                   VOTOS" + Terminal.RESET);
            Terminal.linea();
            for (String[] r : ranking) {
                System.out.printf("  " + Terminal.DIM + "#%-4s" + Terminal.RESET + " " + Terminal.AMARILLO + "%-34s" + Terminal.RESET + " " + Terminal.MAGENTA + "%-22s" + Terminal.RESET + " " + Terminal.VERDE_B + "%s" + Terminal.RESET + "%n",
                    String.format("%03d", Integer.parseInt(r[0])), truncarApp(r[1], 34), truncarApp(r[2], 22), r[3] + " voto(s)");
            }
            Terminal.espacio();

            String correo = leerLinea("Introduce tu correo electronico");
            if (correo.trim().isEmpty()) { System.out.println(Terminal.ROJO + "  [X] El correo no puede estar vacio." + Terminal.RESET); return; }
            if (!correo.contains("@")) { System.out.println(Terminal.ROJO + "  [X] Correo invalido." + Terminal.RESET); return; }

            String idStr = leerLinea("ID del libro al que quieres votar");
            int id;
            try { id = Integer.parseInt(idStr.trim()); }
            catch (NumberFormatException e) { System.out.println(Terminal.ROJO + "  [X] ID invalido." + Terminal.RESET); return; }

            Libro libro = LibroDAO.buscarPorId(id);
            if (libro == null) { System.out.println(Terminal.ROJO + "  [X] No existe ese libro." + Terminal.RESET); return; }

            if (VotoDAO.yaVoto(id, correo)) {
                Terminal.espacio();
                System.out.println(Terminal.AMARILLO + "  [!] El correo " + Terminal.CIAN_B + correo + Terminal.AMARILLO + " ya ha votado por \"" + libro.titulo + "\"." + Terminal.RESET);
                Terminal.espacio();
                System.out.println(Terminal.DIM + "  Opciones:" + Terminal.RESET);
                System.out.println(Terminal.ROJO_B + "  [1]" + Terminal.RESET + " Cancelar mi voto");
                System.out.println(Terminal.VERDE_B + "  [2]" + Terminal.RESET + " Salir sin cambios");
                String op = leerLinea("Elige una opcion");
                if (op.trim().equals("1")) {
                    VotoDAO.cancelarVoto(id, correo);
                    System.out.println(Terminal.VERDE_B + "  [OK] Voto cancelado para \"" + libro.titulo + "\"." + Terminal.RESET);
                } else {
                    System.out.println(Terminal.DIM + "  Sin cambios." + Terminal.RESET);
                }
                return;
            }

            VotoDAO.votar(id, correo);
            int totalVotos = VotoDAO.contarVotos(id);
            Terminal.espacio();
            System.out.println(Terminal.VERDE_B + "  [OK] Voto registrado." + Terminal.RESET);
            System.out.println(Terminal.MAGENTA + "  Libro:  " + Terminal.RESET + libro.titulo);
            System.out.println(Terminal.MAGENTA + "  Correo: " + Terminal.RESET + correo);
            System.out.println(Terminal.MAGENTA + "  Votos totales del libro: " + Terminal.RESET + Terminal.AMARILLO_B + totalVotos + Terminal.RESET);

        } catch (SQLException e) {
            System.out.println(Terminal.ROJO + "  [ERROR] " + e.getMessage() + Terminal.RESET);
        }
    }

    private static String truncarApp(String s, int max) {
        if (s == null) return "";
        return s.length() > max ? s.substring(0, max - 1) + "." : s;
    }

    // =============================================
    //  UTILIDADES
    // =============================================
    private static String leerLinea(String prompt) {
        System.out.print(Terminal.CIAN + "\n  " + prompt + Terminal.RESET + " > ");
        return sc.nextLine();
    }
}