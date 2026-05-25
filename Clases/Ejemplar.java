public class Ejemplar {
    private int codEjemplar;
    private Libros codLibro;
    private String estadoDeDevolucion;

    public Ejemplar(){}

    public Ejemplar(int codEjemplar, Libros codLibro, String estadoDeDevolucion)
    {
        this.codEjemplar = codEjemplar;
        this.codLibro = codLibro;
        this.estadoDeDevolucion = estadoDeDevolucion;
    }

    public int getCodEjemplar()
    {
        return this.codEjemplar;
    }

    public void setCodEjemplar(int codEjemplar)
    {
        this.codEjemplar = codEjemplar;
    }

    public Libros getCodLibro()
    {
        return this.codLibro;
    }

    public void setCodLibro(Libros codLibro)
    {
        this.codLibro = codLibro;
    }

    public String getEstadoDeDevolucion()
    {
        return this.estadoDeDevolucion;
    }

    public void setEstadoDeDevolucion(String estadoDeDevolucion)
    {
        this.estadoDeDevolucion = estadoDeDevolucion;
    }

    public boolean estaDisponible() {
        return this.estadoDeDevolucion != null && this.estadoDeDevolucion.equalsIgnoreCase("disponible");
    }
}
