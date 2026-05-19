package biblioteca;

public class Terminal {

    public static final String RESET    = "\033[0m";
    public static final String BOLD     = "\033[1m";
    public static final String DIM      = "\033[2m";
    public static final String ROJO     = "\033[31m";
    public static final String VERDE    = "\033[32m";
    public static final String AMARILLO = "\033[33m";
    public static final String AZUL     = "\033[34m";
    public static final String MAGENTA  = "\033[35m";
    public static final String CIAN     = "\033[36m";
    public static final String BLANCO   = "\033[97m";
    public static final String ROJO_B   = "\033[91m";
    public static final String VERDE_B  = "\033[92m";
    public static final String AMARILLO_B = "\033[93m";
    public static final String AZUL_B   = "\033[94m";
    public static final String MAGENTA_B = "\033[95m";
    public static final String CIAN_B   = "\033[96m";

    public static void linea() {
        System.out.println(DIM + "----------------------------------------------------------------" + RESET);
    }

    public static void lineaDoble() {
        System.out.println(DIM + "================================================================" + RESET);
    }

    public static void espacio() {
        System.out.println();
    }

    public static void titulo(String t) {
        espacio();
        System.out.println(CIAN_B + BOLD + "  ==>  " + t.toUpperCase() + RESET);
        linea();
    }

    public static void banner() {
        System.out.println(CIAN_B + BOLD);
        System.out.println("  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("  | B I B L I O T E K A - G U R E K I N |");
        System.out.println("  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(RESET);
    }

    public static void tablaLibrosHeader() {
        System.out.println(BOLD + CIAN +
            "  ID     TITULO                             AUTOR                   GENERO          ANIO  ESTADO"
            + RESET);
        linea();
    }

    public static void tablaLibroFila(Libro l) {
        String estado = l.disponible
            ? VERDE_B + "Disponible" + RESET
            : ROJO_B  + "Prestado  " + RESET;
        String titulo = truncar(l.titulo, 34);
        String autor  = truncar(l.autor,  22);
        String genero = truncar(l.genero, 14);
        System.out.printf(
            "  " + DIM + "#%-4d" + RESET +
            " %-34s " +
            MAGENTA + "%-22s " + RESET +
            AZUL + "%-14s " + RESET +
            AMARILLO + "%-4d  " + RESET +
            "%s%n",
            l.id, titulo, autor, genero, l.anio, estado
        );
    }

    public static void tablaPrestamosHeader(boolean conDevolucion) {
        if (conDevolucion) {
            System.out.println(BOLD + CIAN +
                "  ID     LIBRO                          LECTOR                PRESTAMO          DEVOLUCION        ESTADO"
                + RESET);
        } else {
            System.out.println(BOLD + CIAN +
                "  ID     LIBRO                              LECTOR                  FECHA PRESTAMO"
                + RESET);
        }
        linea();
    }

    public static void tablaPrestamoFila(Prestamo p, boolean conDevolucion) {
        String estado = p.devuelto
            ? VERDE + "Devuelto" + RESET
            : AMARILLO_B + "Activo  " + RESET;
        if (conDevolucion) {
            System.out.printf(
                "  " + DIM + "#%-4d" + RESET +
                " " + AMARILLO + "%-30s" + RESET +
                " " + MAGENTA + "%-20s" + RESET +
                "  %-16s  " +
                VERDE + "%-16s" + RESET +
                "  %s%n",
                p.id,
                truncar(p.tituloLibro, 30),
                truncar(p.lector, 20),
                p.fechaPrestamo != null ? p.fechaPrestamo : "-",
                p.fechaDevolucion != null ? p.fechaDevolucion : "-",
                estado
            );
        } else {
            System.out.printf(
                "  " + DIM + "#%-4d" + RESET +
                " " + AMARILLO + "%-34s" + RESET +
                " " + MAGENTA + "%-22s" + RESET +
                "  %-16s%n",
                p.id,
                truncar(p.tituloLibro, 34),
                truncar(p.lector, 22),
                p.fechaPrestamo
            );
        }
    }

    public static String barra(int valor, int total, int ancho) {
        if (total == 0) return DIM + "░░░░░░░░░░░░░░░░░░░░" + RESET;
        int lleno = (int) Math.round((double) valor / total * ancho);
        StringBuilder sb = new StringBuilder(CIAN);
        for (int i = 0; i < lleno; i++) sb.append("#");
        sb.append(DIM);
        for (int i = lleno; i < ancho; i++) sb.append("-");
        sb.append(RESET);
        return sb.toString();
    }

    private static String truncar(String s, int max) {
        if (s == null) return "";
        return s.length() > max ? s.substring(0, max - 1) + "." : s;
    }
}
