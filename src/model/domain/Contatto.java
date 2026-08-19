package model.domain;

public class Contatto {
    private String valore;
    private TipoContatto tipo;

    public Contatto(String valore, TipoContatto tipo) {
        this.valore = valore;
        this.tipo = tipo;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }

    public TipoContatto getTipo() {
        return tipo;
    }

    public void setTipo(TipoContatto tipo) {
        this.tipo = tipo;
    }
}
