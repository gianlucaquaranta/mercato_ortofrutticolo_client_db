package model.dao;

import exception.DAOException;
import model.domain.Contatto;
import model.domain.OrdineDiVendita;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.FormatterService;
import model.domain.VoceOrdine;

public class OrdineDiVenditaDAO {

    public void registraOrdineVendita(OrdineDiVendita ordine) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_ordine_vendita(?, ?, ?, ?, ?)}");

            cs.setString(1, ordine.getCliente());

            String stringaVociOrdine = FormatterService.formatVociOrdine(ordine.getProdotti());
            cs.setString(2, stringaVociOrdine);

            String stringaIndirizzo = FormatterService.formatIndirizzo(ordine.getIndirizzo());
            cs.setString(3, stringaIndirizzo);

            cs.setString(4, ordine.getRecapito().getValore());

            cs.setString(5, ordine.getRecapito().getTipo().name());

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella registrazione dell'ordine di vendita: " + e.getMessage());
        }
    }

    public List<OrdineDiVendita> listaOrdiniDiVendita() throws DAOException {
        List<OrdineDiVendita> ordini = new ArrayList<>();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call lista_ordini_vendita()}");

            if (cs.execute()) {
                // Primo result set: ordini
                ResultSet rsOrdini = cs.getResultSet();
                while (rsOrdini.next()) {
                    OrdineDiVendita ordine = new OrdineDiVendita(
                            rsOrdini.getInt("Numero"),
                            rsOrdini.getDate("Data"),
                            new Contatto(rsOrdini.getString("Recapito"), null),
                            rsOrdini.getString("Cliente"),
                            rsOrdini.getString("Stato"));
                    ordini.add(ordine);
                }

                // Result set successivi: prodotti per ogni ordine
                int ordineIndex = 0;
                boolean hasMoreResults = cs.getMoreResults();

                while (hasMoreResults && ordineIndex < ordini.size()) {
                    ResultSet rsProdotti = cs.getResultSet();
                    OrdineDiVendita ordineCorrente = ordini.get(ordineIndex);

                    while (rsProdotti.next()) {
                        VoceOrdine voce = new VoceOrdine(
                                rsProdotti.getString("CodiceProdotto"),
                                rsProdotti.getInt("Quantità")
                        );
                        ordineCorrente.addProdotto(voce);
                    }

                    ordineIndex++;
                    hasMoreResults = cs.getMoreResults();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nel recupero ordini vendita: " + e.getMessage());
        }

        return ordini;
    }

    public void marcaOrdinePronto(OrdineDiVendita o) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call marca_ordine_pronto(?)}");

            cs.setInt(1, o.getNumero());

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella modifica dell'ordine: " + e.getMessage());
        }
    }
}
