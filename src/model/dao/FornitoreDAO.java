package model.dao;

import exception.DAOException;
import model.domain.Fornitore;
import model.domain.Indirizzo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class FornitoreDAO {

    public void registraFornitore(Fornitore fornitore) throws DAOException {

        StringBuilder sbIndirizzi = new StringBuilder();
        for (Indirizzo sede : fornitore.getSedi()) {
            if (sbIndirizzi.length() > 0) sbIndirizzi.append("$"); // separatore tra indirizzi

            sbIndirizzi.append(sede.getVia())
                    .append("#").append(sede.getCivico()) // separatore tra componenti dell'indirizzo
                    .append("#").append(sede.getCap())
                    .append("#").append(sede.getCitta())
                    .append("#").append(sede.getProvincia());
        }

        StringBuilder sbProdotti = new StringBuilder();
        for (String codProd : fornitore.getProdForniti()) {
            if (sbProdotti.length() > 0) sbProdotti.append("$"); // separatore tra prodotti

            sbProdotti.append(codProd);
        }

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{CALL registra_fornitore(?, ?, ?, ?, ?)}");
            cs.setString(1, fornitore.getCodice());
            cs.setString(2, fornitore.getCf());
            cs.setString(3, fornitore.getNome());
            cs.setString(4, sbIndirizzi.toString());
            cs.setString(5, sbProdotti.toString());

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella registrazione del fornitore: " + e.getMessage());
        }
    }

}
