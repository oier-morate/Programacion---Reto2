public class Tiene {
    private GenerosDeLibro codGenero;
    private Libro codLibro;

    public Tiene(){}

    public Tiene(GenerosDeLibro codGenero, Libro codLibro)
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

    public Libro getCodLibro()
    {
        return this.codLibro;
    }

    public void setCodLibro(Libro codLibro)
    {
        this.codLibro = codLibro;
    }

    public boolean perteneceAGenero(int codGenero) {
        return this.codGenero != null && this.codGenero.getCodGenero() == codGenero;
    }
}
