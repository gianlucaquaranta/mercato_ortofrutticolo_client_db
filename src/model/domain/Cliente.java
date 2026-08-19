package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String partitaIva;
    private String nome;
    private String cognome;
    private Indirizzo indirizzoResidenza;
    private Indirizzo indirizzoFatturazione;
    private List<Contatto> contatti = new ArrayList<>();

    public Cliente(String partitaIva, String nome, String cognome, Indirizzo indirizzoResidenza, Indirizzo indirizzoFatturazione) {
        this.partitaIva = partitaIva;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzoResidenza = indirizzoResidenza;
        this.indirizzoFatturazione = indirizzoFatturazione;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Indirizzo getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    public void setIndirizzoResidenza(Indirizzo indirizzoResidenza) {
        this.indirizzoResidenza = indirizzoResidenza;
    }

    public Indirizzo getIndirizzoFatturazione() {
        return indirizzoFatturazione;
    }

    public void setIndirizzoFatturazione(Indirizzo indirizzoFatturazione) {
        this.indirizzoFatturazione = indirizzoFatturazione;
    }

    public List<Contatto> getContatti() {
        return contatti;
    }

    public void setContatti(List<Contatto> contatti) {
        this.contatti = contatti;
    }

    public void addContatto(Contatto contatto) {
        this.contatti.add(contatto);
    }


}
