package view;

import model.domain.Credentials;

import java.io.IOException;
import java.util.Scanner;

public class LoginView {

    public static Credentials authenticate() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== LOGIN =====");
        System.out.print("username: ");
        String username = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();

        return new Credentials(username, password, null);
    }
}
