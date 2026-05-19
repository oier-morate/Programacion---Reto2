package biblioteca;

public class Libro {
    public int     id;
    public String  titulo;
    public String  autor;
    public int     anio;
    public String  genero;
    public String  isbn;
    public boolean disponible;
    public String  fechaAlta;

    public Libro() {}

    public Libro(String titulo, String autor, int anio, String genero, String isbn) {
        this.titulo     = titulo;
        this.autor      = autor;
        this.anio       = anio;
        this.genero     = genero;
        this.isbn       = isbn;
        this.disponible = true;
        this.fechaAlta  = java.time.LocalDateTime.now()
                            .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
