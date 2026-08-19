package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.*;
import view.SegreteriaView;

import java.sql.SQLException;
import java.util.List;

public class SegreteriaController {

    public void start(){

        try {
            ConnectionFactory.changeRole(Role.SEGRETERIA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int choice;

        while(true) {
            choice = SegreteriaView.showMenu();

            switch (choice) {
                case 1:
                    registraCliente();
                    break;
                case 2:
                    registraProdotto();
                    break;
                case 3:
                    registraOrdineVendita();
                    break;
                case 4:
                    registraFornitore();
                    break;
                case 5:
                    listaOrdiniDiVendita();
                    break;
                case 6:
                    System.exit(0);
            }
        }

    }

    void registraCliente(){
        Cliente cliente;
        cliente = SegreteriaView.registrazioneCliente();

        ClienteDAO dao = new ClienteDAO();

        try {
            dao.registraCliente(cliente);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    void registraProdotto(){
        Prodotto prodotto;
        prodotto = SegreteriaView.registrazioneProdotto();

        GiacenzaDAO dao = new GiacenzaDAO();

        try {
            dao.registraProdotto(prodotto);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    void registraOrdineVendita(){
        OrdineDiVendita o;
        o = SegreteriaView.registrazioneOrdineVendita();

        OrdineDiVenditaDAO dao = new OrdineDiVenditaDAO();

        try {
            dao.registraOrdineVendita(o);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    void registraFornitore(){
        Fornitore fornitore;
        fornitore = SegreteriaView.registrazioneFornitore();

        FornitoreDAO dao = new FornitoreDAO();

        try {
            dao.registraFornitore(fornitore);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    public void listaOrdiniDiVendita(){
        OrdineDiVenditaDAO dao = new OrdineDiVenditaDAO();

        List<OrdineDiVendita> lo = null;

        try {
            lo = dao.listaOrdiniDiVendita();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        SegreteriaView.stampaListaOrdini(lo);
    }



}
