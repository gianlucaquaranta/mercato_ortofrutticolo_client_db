package model.dao;

import exception.DAOException;
import model.domain.Lotto;
import model.domain.VoceReportScadenze;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {
    public void registraLotto(Lotto lotto) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_lotto(?, ?, ?, ?)}");

            cs.setString(1, lotto.getNumero());
            cs.setDate(2, lotto.getScadenza());
            cs.setInt(3, lotto.getPeso());
            cs.setString(4, lotto.getProdotto());

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella registrazione del lotto: " + e.getMessage());
        }
    }

    public void modificaLotto(Lotto lotto) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call modifica_peso_lotto(?, ?)}");

            cs.setString(1, lotto.getNumero());
            cs.setInt(2, lotto.getPeso());

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella modifica del lotto: " + e.getMessage());
        }
    }

    public List<VoceReportScadenze> reportLottiInScadenza() throws DAOException {
        List<VoceReportScadenze> report = new ArrayList<>();

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call report_lotti_in_scadenza()}");
            boolean status = cs.execute();

            if (status) {
                // Primo result set: date di scadenza
                ResultSet rsDate = cs.getResultSet();
                while (rsDate.next()) {
                    Date dataScadenza = rsDate.getDate("Giorno");
                    report.add(new VoceReportScadenze(dataScadenza));
                }

                // Result set successivi: lotti per ogni data
                int dataIndex = 0;
                status = cs.getMoreResults();

                while ((status || cs.getUpdateCount() != -1) && dataIndex < report.size()) {
                    if (status) {
                        ResultSet rsLotti = cs.getResultSet();
                        VoceReportScadenze voceCorrente = report.get(dataIndex);

                        while (rsLotti.next()) {
                            String numeroLotto = rsLotti.getString("Numero");
                            int peso = rsLotti.getInt("Peso");
                            String codiceProdotto = rsLotti.getString("Prodotto");

                            Lotto lotto = new Lotto(numeroLotto, null, peso, codiceProdotto);
                            voceCorrente.addLotto(lotto);
                        }
                        dataIndex++;
                    }
                    status = cs.getMoreResults();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore nel report scadenze: " + e.getMessage());
        }

        return report;
    }
}
