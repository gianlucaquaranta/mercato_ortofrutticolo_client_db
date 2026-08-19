package model;

import model.domain.Contatto;
import model.domain.Indirizzo;
import model.domain.VoceOrdine;

import java.util.List;

public class FormatterService {

    public static String formatIndirizzo(Indirizzo indirizzo) {
        if (indirizzo == null) {
            throw new IllegalArgumentException("Indirizzo non può essere null");
        }

        return indirizzo.getVia() + "#" +
                indirizzo.getCivico() + "#" +
                indirizzo.getCap() + "#" +
                indirizzo.getCitta() + "#" +
                indirizzo.getProvincia();
    }


    public static String formatIndirizzi(List<Indirizzo> indirizzi) {
        if (indirizzi == null || indirizzi.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indirizzi.size(); i++) {
            sb.append(formatIndirizzo(indirizzi.get(i)));

            if (i < indirizzi.size() - 1) {
                sb.append("$");
            }
        }

        return sb.toString();
    }

    public static String formatContatti(List<Contatto> contatti) {
        if (contatti == null || contatti.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contatti.size(); i++) {
            Contatto contatto = contatti.get(i);
            sb.append(contatto.getValore())
                    .append("#")
                    .append(contatto.getTipo().name());

            if (i < contatti.size() - 1) {
                sb.append("$");
            }
        }

        return sb.toString();
    }

    public static String formatVociOrdine(List<VoceOrdine> vociOrdine) {
        if (vociOrdine == null || vociOrdine.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vociOrdine.size(); i++) {
            VoceOrdine voce = vociOrdine.get(i);
            sb.append(voce.getCodiceProd())
                    .append("#")
                    .append(voce.getQuantita());

            if (i < vociOrdine.size() - 1) {
                sb.append("$");
            }
        }

        return sb.toString();
    }

}