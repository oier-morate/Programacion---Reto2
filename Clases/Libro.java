public class Libro {
    private int codLibro;
    private String titulo;
    private int numeroEjemplares;
    private String isbn;
    private String urlWiki;
    private int numVotos;
    private String descripcion;
    private String rutaIMG;
    private int idAutor;

    public Libro() {}

    public Libro(int codLibro, String titulo, int numeroEjemplares, String isbn, String urlWiki, int numVotos, String descripcion, String rutaIMG, int idAutor)
    {
        this.codLibro = codLibro;
        this.titulo = titulo;
        this.numeroEjemplares = numeroEjemplares;
        this.isbn = isbn;
        this.urlWiki = urlWiki;
        this.numVotos = numVotos;
        this.descripcion = descripcion;
        this.rutaIMG = rutaIMG;
        this.idAutor = idAutor;
    }

    public int getCodLibro()
    {
        return this.codLibro;
    }

    public void setCodLibro(int codLibro)
    {
        this.codLibro = codLibro;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public String getUrlWiki(){
        return this.urlWiki;
    }

    public int getnumVotos(){
        return this.isbn;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    public String getRutaIMG(){
        return this.rutaIMG;
    }

    public String getIdAutor(){
        return this.idAutor;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public int getNumeroEjemplares()
    {
        return this.numeroEjemplares;
    }

    public void setNumeroEjemplares(int numeroEjemplares)
    {
        this.numeroEjemplares = numeroEjemplares;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    
    public void setUrlWiki(String urlWiki){
        this.urlWiki = urlWiki;
    }

    public void setnumVotos(int numVotos){
        this.numVotos = numVotos;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setRutaIMG(String rutaIMG){
        this.rutaIMG = rutaIMG;
    }

    public void setIdAutor(int idAutor){
        this.idAutor = idAutor;
    }
}
