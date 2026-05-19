public class Voto {

    private int idVoto;
    private Usuario idUsuario;

    // Constructor
    public Voto(int idVoto, Usuario idUsuario) {
        this.idVoto = idVoto;
        this.idUsuario = idUsuario;
    }

    // Getters
    public int getIdVoto() {
        return idVoto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    // Setters
    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
