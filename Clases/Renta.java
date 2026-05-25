public class Renta {
    private Ejemplar codEjemplar;
    private Socios dniSocio;
    private String fechaDevolucion;
    private String fechaRenta;
    private int maxRenta;

    public Renta(){}

    public Renta(Ejemplar codEjemplar, Socios dniSocio, String fechaDevolucion,
        String fechaRenta, int maxRenta)
        {
            this.codEjemplar = codEjemplar;
            this.dniSocio = dniSocio;
            this.fechaDevolucion = fechaDevolucion;
            this.fechaRenta = fechaRenta;
            this.maxRenta = maxRenta;
        }
    
    public Ejemplar getCodEjemplar()
    {
        return this.codEjemplar;
    }

    public void setCodEjemplar(Ejemplar codEjemplar)
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

    public boolean rentaVencida() {
        // Compara fecha de devolución con la fecha límite (maxRenta días desde renta)
        if (this.fechaDevolucion == null || this.fechaRenta == null)
            return false;
        String[] partsRenta = this.fechaRenta.split("/");
        String[] partsDevolucion = this.fechaDevolucion.split("/");
        int diaRenta  = Integer.parseInt(partsRenta[0]);
        int diaDevolucion = Integer.parseInt(partsDevolucion[0]);
        int diasTranscurridos = diaDevolucion - diaRenta; // simplificado
        return diasTranscurridos > this.maxRenta;
    }
}
