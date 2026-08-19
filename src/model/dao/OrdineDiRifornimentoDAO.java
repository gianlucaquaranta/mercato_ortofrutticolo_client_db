package model.dao;

import exception.DAOException;
import model.FormatterService;
import model.domain.OrdineDiRifornimento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class OrdineDiRifornimentoDAO {

    public void registraOrdineDiRifornimento(OrdineDiRifornimento ordine) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_ordine_rifornimento(?, ?)}");

            cs.setString(1, ordine.getFornitore());

            String stringaVociOrdine = FormatterService.formatVociOrdine(ordine.getProdotti());
            cs.setString(2, stringaVociOrdine);

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella registrazione dell'ordine di rifornimento: " + e.getMessage());
        }
    }

}
