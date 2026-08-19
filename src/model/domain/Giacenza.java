package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Giacenza {
    private Prodotto prodotto;
    private List<Lotto> lotti = new ArrayList<>();

    public Giacenza(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public void addLotto(Lotto lotto) {
        this.lotti.add(lotto);
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Prodotto
        sb.append(prodotto.getCodice()).append(" - ").append(prodotto.getNome()).append(" - ").append(prodotto.getCategoria()).append(" - Disponibilità: ").append(prodotto.getDisponibilita()).append(" kg\n");

        // Lista lotti di quel prodotto
        if (lotti.isEmpty()) {
            sb.append("  Nessun lotto disponibile\n");
        } else {
            sb.append("  Lotti:\n");
            for (Lotto lotto : lotti) {
                sb.append("  - ").append(lotto.getNumero()).append(": ").append(lotto.getPeso()).append(" kg (Scadenza: ").append(lotto.getScadenza()).append(")\n");
            }
        }

        sb.append("\n");

        return sb.toString();
    }
}
