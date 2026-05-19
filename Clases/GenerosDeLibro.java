public class GenerosDeLibro {
    private int codGenero;
    private String nombreGenero;

    public GenerosDeLibro(){}

    public GenerosDeLibro(int codGenero, String nombreGenero)
    {
        this.codGenero = codGenero;
        this.nombreGenero = nombreGenero;
    }

    public int getCodGenero()
    {
        return this.codGenero;
    }

    public void setCodGenero(int codGenero)
    {
        this.codGenero = codGenero;
    }

    public String getNombreGenero()
    {
        return this.nombreGenero;
    }

    public void setNombnreGenero(String nombreGenero)
    {
        this.nombreGenero = nombreGenero;
    }

}
