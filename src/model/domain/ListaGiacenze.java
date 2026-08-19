package model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaGiacenze {
    private List<Giacenza> giacenze = new ArrayList<>();

    public void addGiacenza(Giacenza giacenza) {
        this.giacenze.add(giacenza);
    }



    public void addLottoToGiacenze(String prodotto, Lotto lotto) {
        for(Giacenza g : giacenze){
            if(g.getProdotto().getCodice().equals(prodotto)){
                g.addLotto(lotto);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Giacenza giacenza : giacenze) {
            sb.append(giacenza);
        }
        return sb.toString();
    }

}
