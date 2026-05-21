public class Voto {

    private int idVoto;
    private int idUsuario;
    private int idLibro;

    // Constructor
    public Voto(int idVoto, int idUsuario, int idLibro) {
        this.idVoto = idVoto;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    // Getters
    public int getIdVoto() {
        return idVoto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getidLibro() {
        return idLibro;
    }

    // Setters
    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdUsuario(int idLibro) {
        this.idLibro = idLibro;
    }
}
