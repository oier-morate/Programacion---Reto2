public class Socio {
    private String dniSocio;
    private String tarjetaDeCredito;
    private String usuario;
    private String correoElectronico;
    private String nombreCompleto;
    private String fechaPenalizacion;
    private String telefono;
    private String contraseña;
    private String numeroSeguridadSocial;
    private TipoDePenalizacion codPenalizacion;

     public Socio() {}

     public Socio(String dniSocio, String nombreCompleto, String correoElectronico, String telefono) {
        this.dniSocio = dniSocio;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public Socio(String dniSocio, String tarjetaDeCredito, String usuario, String correoElectronico,
                  String nombreCompleto, String fechaPenalizacion, String telefono, String contraseña,
                  String numeroSeguridadSocial, TipoDePenalizacion codPenalizacion) {

        this.dniSocio = dniSocio;
        this.tarjetaDeCredito = tarjetaDeCredito;
        this.usuario = usuario;
        this.correoElectronico = correoElectronico;
        this.nombreCompleto = nombreCompleto;
        this.fechaPenalizacion = fechaPenalizacion;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.codPenalizacion = codPenalizacion;
    }

      public String getDniSocio() {
        return dniSocio;
    }

    public void setDniSocio(String dniSocio) {
        this.dniSocio = dniSocio;
    }

    public String getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    public void setTarjetaDeCredito(String tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaPenalizacion() {
        return fechaPenalizacion;
    }

    public void setFechaPenalizacion(String fechaPenalizacion) {
        this.fechaPenalizacion = fechaPenalizacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }

    public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    public TipoDePenalizacion getCodPenalizacion() {
        return codPenalizacion;
    }

    public void setCodPenalizacion(TipoDePenalizacion codPenalizacion) {
        this.codPenalizacion = codPenalizacion;
    }
}
