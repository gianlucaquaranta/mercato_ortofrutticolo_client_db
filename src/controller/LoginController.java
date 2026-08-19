package controller;

import exception.DAOException;
import model.dao.LoginDAO;
import model.domain.Credentials;
import view.LoginView;

import java.io.IOException;

public class LoginController {

    public void start() {
        Credentials cred;

        try {
            cred = LoginView.authenticate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            cred = new LoginDAO().authenticate(cred.getUsername(), cred.getPassword());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        if(cred.getRole() == null) {
            throw new RuntimeException("Invalid credentials");
        }

        switch(cred.getRole()) {
            case SEGRETERIA -> new SegreteriaController().start();
            case GESTORE_MAGAZZINO -> new GestoreMagazzinoController().start();
            default -> throw new RuntimeException("Invalid credentials");
        }
    }

}