public class Libros {
    private int codLibro;
    private String nombre;
    private int numeroEjemplares;
    private String isbn;

    public Libros() {}

    public Libros(int codLibro, String nombre, int numeroEjemplares, String isbn)
    {
        this.codLibro = codLibro;
        this.nombre = nombre;
        this.numeroEjemplares = numeroEjemplares;
        this.isbn = isbn;
    }

    public int getCodLibro()
    {
        return this.codLibro;
    }

    public void setCodLibro(int codLibro)
    {
        this.codLibro = codLibro;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getNumeroEjemplares()
    {
        return this.numeroEjemplares;
    }

    public void setNumeroEjemplares(int numeroEjemplares)
    {
        this.numeroEjemplares = numeroEjemplares;
    }

    public String getIsbn()
    {
        return this.isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
}
