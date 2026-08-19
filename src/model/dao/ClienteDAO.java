package model.dao;

import exception.DAOException;
import model.domain.Cliente;
import model.domain.Indirizzo;
import model.FormatterService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void registraCliente(Cliente cliente) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_cliente(?, ?, ?, ?, ?)}");

            // Preparazione parametri base
            cs.setString(1, cliente.getPartitaIva());
            cs.setString(2, cliente.getNome());
            cs.setString(3, cliente.getCognome());

            // Preparazione indirizzi usando model.FormatterService
            List<Indirizzo> indirizziCliente = new ArrayList<>();

            indirizziCliente.add(cliente.getIndirizzoResidenza());
            indirizziCliente.add(cliente.getIndirizzoFatturazione());

            String stringaIndirizzi = FormatterService.formatIndirizzi(indirizziCliente);
            cs.setString(4, stringaIndirizzi);

            // Preparazione contatti usando model.FormatterService
            String stringaContatti = FormatterService.formatContatti(cliente.getContatti());
            cs.setString(5, stringaContatti);

            cs.execute();

        } catch (SQLException e) {
            throw new DAOException("Errore nella registrazione del cliente: " + e.getMessage());
        }
    }

}
