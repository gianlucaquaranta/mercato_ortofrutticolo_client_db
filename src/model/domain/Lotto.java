package model.domain;

import java.sql.Date;

public class Lotto {
    private String numero;
    private Date scadenza;
    private int peso;
    private String prodotto;

    public Lotto(String numero, Date scadenza, int peso, String prodotto) {
        this.numero = numero;
        this.scadenza = scadenza;
        this.peso = peso;
        this.prodotto = prodotto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getProdotto() {
        return prodotto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }
}
