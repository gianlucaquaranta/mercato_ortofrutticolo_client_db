package model.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VoceReportScadenze {
    private Date data;
    private List<Lotto> lottiInScadenza = new ArrayList<>();

    public VoceReportScadenze(Date data) {
        this.data = data;
    }

    public void addLotto(Lotto lotto) {
        lottiInScadenza.add(lotto);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("LOTTI IN SCADENZA PER IL GIORNO "+data.toString()+" :\n");
        for (Lotto lotto : lottiInScadenza) {
            sb.append("  - ").append(lotto.getNumero()).append(": ").append(lotto.getPeso()).append(" Kg (Prodotto: ").append(lotto.getProdotto()).append(")\n");
        }


        sb.append("\n");

        return sb.toString();
    }
}
