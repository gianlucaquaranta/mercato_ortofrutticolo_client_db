package model.dao;

import exception.DAOException;
import model.domain.*;

import java.sql.*;

public class GiacenzaDAO {

    public void registraProdotto(Prodotto prodotto) throws DAOException {

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{CALL registra_prodotto(?, ?, ?, ?)}");
            cs.setString(1, prodotto.getCodice());
            cs.setString(2, prodotto.getNome());
            cs.setString(3, prodotto.getCategoria().name());
            cs.setFloat(4, prodotto.getPrezzoKg());

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella registrazione del prodotto: " + e.getMessage());
        }
    }

    public ListaGiacenze listaGiacenze() throws DAOException {
        ListaGiacenze listaGiacenze = new ListaGiacenze();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call lista_giacenze()}");
            boolean status = cs.execute();

            if (status) {
                // Primo result set: lista prodotti
                ResultSet rsProdotti = cs.getResultSet();
                while (rsProdotti.next()) {
                    String codice = rsProdotti.getString("CodiceProdotto");
                    String nome = rsProdotti.getString("Nome");
                    String catstring = rsProdotti.getString("Categoria");
                    Categoria categoria;
                    if(catstring.equalsIgnoreCase("frutta")) categoria = Categoria.FRUTTA;
                    else categoria = Categoria.VERDURA;
                    int disponibilita = rsProdotti.getInt("Disponibilità");

                    Prodotto prodotto = new Prodotto(codice, nome, categoria, 0.0f, disponibilita);
                    Giacenza giacenza = new Giacenza(prodotto);
                    listaGiacenze.addGiacenza(giacenza);
                }

                // Result set successivi: lotti per ogni prodotto

                status = cs.getMoreResults();

                while (status || cs.getUpdateCount() != -1) {
                    if (status) {
                        ResultSet rsLotti = cs.getResultSet();
                        while (rsLotti.next()) {
                            String numeroLotto = rsLotti.getString("NumeroLotto");
                            Date dataScadenza = rsLotti.getDate("DataScadenza");
                            int peso = rsLotti.getInt("PesoLotto");
                            String codiceProdotto = rsLotti.getString("CodiceProdotto");

                            Lotto lotto = new Lotto(numeroLotto, dataScadenza, peso, codiceProdotto);
                            listaGiacenze.addLottoToGiacenze(codiceProdotto, lotto);
                        }
                    }
                    status = cs.getMoreResults();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nel tracciamento delle giacenze: " + e.getMessage());
        }

        return listaGiacenze;
    }


}

