package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Fornitore {
    private String codice;
    private String cf;
    private String nome;
    private List<Indirizzo> sedi = new ArrayList<>();
    private List<String> prodForniti = new ArrayList<>();


    public Fornitore(String codice, String cf, String nome) {
        this.codice = codice;
        this.cf = cf;
        this.nome = nome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getProdForniti() {
        return prodForniti;
    }

    public List<Indirizzo> getSedi() {
        return sedi;
    }

    public void addSede(Indirizzo sede) {
        this.sedi.add(sede);
    }

    public void addProdFornito(String fornitura) {
        this.prodForniti.add(fornitura);
    }
}
