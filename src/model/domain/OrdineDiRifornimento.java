package model.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdineDiRifornimento {
    private int numero;
    private Date data;
    private String fornitore;
    private List<VoceOrdine> prodotti = new ArrayList<>();

    public OrdineDiRifornimento(String fornitore) {
        this.fornitore = fornitore;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFornitore() {
        return fornitore;
    }

    public void setFornitore(String fornitore) {
        this.fornitore = fornitore;
    }

    public List<VoceOrdine> getProdotti() {
        return prodotti;
    }

    public void addProdotto(VoceOrdine prodotto) {
        prodotti.add(prodotto);
    }
}
