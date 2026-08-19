package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.*;
import view.GestoreMagazzinoView;

import java.sql.SQLException;
import java.util.List;

public class GestoreMagazzinoController {
    public void start(){

        try {
            ConnectionFactory.changeRole(Role.GESTORE_MAGAZZINO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int choice;

        while(true) {
            choice = GestoreMagazzinoView.showMenu();

            switch (choice) {
                case 1:
                    registraLotto();
                    break;
                case 2:
                    registraOrdineRifornimento();
                    break;
                case 3:
                    listaGiacenze();
                    break;
                case 4:
                    reportLottiScadenza();
                    break;
                case 5:
                    modificaLotto();
                    break;
                case 6:
                    listaOrdiniDiVendita();
                    break;
                case 7:
                    marcaOrdinePronto();
                    break;
                case 8:
                    System.exit(0);
            }
        }

    }

    public void registraLotto(){
        Lotto lotto;
        lotto = GestoreMagazzinoView.registrazioneLotto();

        LottoDAO dao = new LottoDAO();

        try {
            dao.registraLotto(lotto);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registraOrdineRifornimento(){
        OrdineDiRifornimento o;
        o = GestoreMagazzinoView.registrazioneOrdineRifornimento();

        OrdineDiRifornimentoDAO dao = new OrdineDiRifornimentoDAO();

        try {
            dao.registraOrdineDiRifornimento(o);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listaGiacenze(){
        GiacenzaDAO dao = new GiacenzaDAO();

        ListaGiacenze lg = null;
        try {
            lg = dao.listaGiacenze();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        GestoreMagazzinoView.stampaListaGiacenze(lg);
     }

    public void reportLottiScadenza(){
        LottoDAO dao = new LottoDAO();

        List<VoceReportScadenze> report = null;

        try {
            report = dao.reportLottiInScadenza();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        GestoreMagazzinoView.stampaReport(report);

    }

    public void listaOrdiniDiVendita(){
        OrdineDiVenditaDAO dao = new OrdineDiVenditaDAO();

        List<OrdineDiVendita> lo = null;

        try {
            lo = dao.listaOrdiniDiVendita();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        GestoreMagazzinoView.stampaListaOrdini(lo);
    }

    public void modificaLotto(){
        Lotto nuovoLotto;
        nuovoLotto = GestoreMagazzinoView.modificaLotto();

        LottoDAO dao = new LottoDAO();

        try {
            dao.modificaLotto(nuovoLotto);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public void marcaOrdinePronto(){
        OrdineDiVendita o;
        o = GestoreMagazzinoView.marcaOrdinePronto();

        OrdineDiVenditaDAO dao = new OrdineDiVenditaDAO();

        try {
            dao.marcaOrdinePronto(o);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }


}
