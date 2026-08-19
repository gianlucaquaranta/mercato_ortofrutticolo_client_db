package model.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdineDiVendita {
    private int numero;
    private Date data;
    private Contatto recapito;
    private String cliente;
    private Indirizzo indirizzo;
    private String stato;
    private List<VoceOrdine> prodotti = new ArrayList<>();

    public OrdineDiVendita() {}

    public OrdineDiVendita(int numero, Date data, Contatto recapito, String cliente, String stato) {
        this.numero = numero;
        this.data = data;
        this.recapito = recapito;
        this.cliente = cliente;
        this.stato = stato;
    }

    public OrdineDiVendita(Contatto recapito, String cliente, Indirizzo indirizzo) {
        this.recapito = recapito;
        this.cliente = cliente;
        this.indirizzo = indirizzo;
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

    public Contatto getRecapito() {
        return recapito;
    }

    public void setRecapito(Contatto recapito) {
        this.recapito = recapito;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<VoceOrdine> getProdotti() {
        return prodotti;
    }

    public void addProdotto(VoceOrdine prodotto) {
        prodotti.add(prodotto);
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Informazioni ordine - una per riga
        sb.append("Ordine: ").append(numero).append("\n");
        sb.append("Data: ").append(data).append("\n");
        sb.append("Recapito: ").append(recapito != null ? recapito.getValore() : "N/A").append("\n");
        if (indirizzo != null) {
            sb.append("Indirizzo: ");
            sb.append(indirizzo.getVia()).append(" ")
                    .append(indirizzo.getCivico()).append(", ")
                    .append(indirizzo.getCap()).append(" ")
                    .append(indirizzo.getCitta()).append(" (")
                    .append(indirizzo.getProvincia()).append(")");
        }
        sb.append("\n");
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("Stato: ").append(stato != null ? stato : "N/A").append("\n");

        // Prodotti
        sb.append("Prodotti: ");
        if (prodotti.isEmpty()) {
            sb.append("nessuno\n");
        } else {
            sb.append("\n");
            for (VoceOrdine voce : prodotti) {
                sb.append("  - ").append(voce.getCodiceProd()).append(": ").append(voce.getQuantita()).append(" kg\n");
            }
        }

        // Separatore tra ordini
        sb.append("----------------------------------------\n");

        return sb.toString();
    }
}

