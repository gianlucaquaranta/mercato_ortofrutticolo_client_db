package model.domain;

public class VoceOrdine {
    private String codiceProd;
    private int quantita;

    public VoceOrdine(String codiceProd, int quantita) {
        this.codiceProd = codiceProd;
        this.quantita = quantita;
    }

    public String getCodiceProd() {
        return codiceProd;
    }

    public void setCodiceProd(String codiceProd) {
        this.codiceProd = codiceProd;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
