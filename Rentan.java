public class Rentan {
    private Ejemplares codEjemplar;
    private Socios dniSocio;
    private String fechaDevolucion;
    private String fechaRenta;
    private int maxRenta;

    public Rentan(){}

    public Rentan(Ejemplares codEjemplar, Socios dniSocio, String fechaDevolucion,
        String fechaRenta, int maxRenta)
        {
            this.codEjemplar = codEjemplar;
            this.dniSocio = dniSocio;
            this.fechaDevolucion = fechaDevolucion;
            this.fechaRenta = fechaRenta;
            this.maxRenta = maxRenta;
        }
    
    public Ejemplares getCodEjemplar()
    {
        return this.codEjemplar;
    }

    public void setCodEjemplar(Ejemplares codEjemplar)
    {
        this.codEjemplar = codEjemplar;
    }

    public Socios getDniSocio()
    {
        return this.dniSocio;
    }

    public void setDniSocio(Socios dniSocio)
    {
        this.dniSocio = dniSocio;
    }

    public String getFechaDeDevolucion()
    {
        return this.fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion)
    {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getFechaRenta()
    {
        return this.fechaRenta;
    }

    public void setFechaRenta(String fechaRenta)
    {
        this.fechaRenta = fechaRenta;
    }

    public int getMaxRenta()
    {
        return this.maxRenta;
    }

    public void setMaxRenta(int maxRenta)
    {
        this.maxRenta = maxRenta;
    }
}
