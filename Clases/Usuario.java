public class Usuario {

    private int idUsuario;
    private String correoElectronico;
    private String contrasena;

    // Constructor
    public Usuario(int idUsuario, String correoElectronico, String contrasena) {
        this.idUsuario = idUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Setters
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean verificarContrasena(String intento) {
        return this.contrasena != null && this.contrasena.equals(intento);
    }
}
