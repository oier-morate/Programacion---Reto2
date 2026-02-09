public class TipoDePenalizaciones {
    private int codPenalizacion;
    private String motivo;
    private int numeroDiasPenalizado;

    public TipoDePenalizaciones(){}

    public TipoDePenalizaciones(int codPenalizacion, String motivo, int numeroDiasPenalizado)
    {
        this.codPenalizacion = codPenalizacion;
        this.motivo = motivo;
        this.numeroDiasPenalizado = numeroDiasPenalizado;
    }

    public int getCodPenalizacion()
    {
        return this.codPenalizacion;
    }

    public void setCodPenalizacion(int codPenalizacion)
    {
        this.codPenalizacion = codPenalizacion;
    }

    public String getMotivo()
    {
        return this.motivo;
    }

    public void setMotivo(String motivo)
    {
        this.motivo = motivo;
    }

    public int getNumeroDiasPenalizado()
    {
        return this.numeroDiasPenalizado;
    }

    public void setNumeroDiasPenalizado(int numerDiasPenalizado)
    {
        this.numeroDiasPenalizado = numerDiasPenalizado;
    }
}
