package model.domain;

public class Prodotto {
    private String codice;
    private String nome;
    private Categoria categoria;
    private float prezzoKg;
    private int disponibilita;

    public Prodotto(String codice, String nome, Categoria categoria, float prezzoKg, int disponibilita) {
        this.codice = codice;
        this.nome = nome;
        this.categoria = categoria;
        this.prezzoKg = prezzoKg;
        this.disponibilita = disponibilita;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public float getPrezzoKg() {
        return prezzoKg;
    }

    public void setPrezzoKg(float prezzoKg) {
        this.prezzoKg = prezzoKg;
    }

    public int getDisponibilita() {
        return disponibilita;
    }
}