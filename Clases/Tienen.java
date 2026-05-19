public class Tienen {
    private GenerosDeLibro codGenero;
    private Libros codLibro;

    public Tienen(){}

    public Tienen(GenerosDeLibro codGenero, Libros codLibro)
    {
        this.codGenero = codGenero;
        this.codLibro = codLibro;
    }

    public GenerosDeLibro getCodGenero()
    {
        return this.codGenero;
    }

    public void setCodGenero(GenerosDeLibro codGenero)
    {
        this.codGenero = codGenero;
    }

    public Libros getCodLibro()
    {
        return this.codLibro;
    }

    public void setCodLibro(Libros codLibro)
    {
        this.codLibro = codLibro;
    }
}
