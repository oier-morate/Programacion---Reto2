public class Autores {
    private int codAutor;
    private String dniAutor;
    private String nombre;
    private String apellidos;
    
    public Autores() {}

    public Autores(int codAutor, String dniAutor, String nombre, String apellidos)
    {
        this.codAutor = codAutor;
        this.dniAutor = dniAutor;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getCodAutor()
    {
        return this.codAutor;
    }

    public void setCodAutor(int codAutor)
    {
        this.codAutor = codAutor;
    }

    public String getDniAutor()
    {
        return this.dniAutor;
    }

    public void setDniAutor(String dniAutor)
    {
        this.dniAutor = dniAutor;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidos()
    {
        return this.apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }
}
