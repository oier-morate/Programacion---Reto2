public class Escribieron {
    private Libros codLibro;
    private Autores codAutor;

    public Escribieron(){}

    public Escribieron(Libros codLibro, Autores codAutor)
    {
        this.codLibro = codLibro;
        this.codAutor = codAutor;
    }

    public Libros getCodLibro()
    {
        return this.codLibro;
    }

    public void setCodLibro(Libros codLibro)
    {
        this.codLibro = codLibro;
    }

    public Autores getCodAutor()
    {
        return this.codAutor;
    }

    public void setCodAutor(Autores codAutor)
    {
        this.codAutor = codAutor;
    }
    public String getResumen() {
        return codAutor.getNombreCompleto() + " escribió \"" + codLibro.getNombre() + "\"";
    }
}
