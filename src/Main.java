import controller.GestoreMagazzinoController;
import controller.LoginController;
import controller.SegreteriaController;
import model.dao.ConnectionFactory;
import model.domain.Role;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LoginController lc = new LoginController();
        lc.start();
    }
}