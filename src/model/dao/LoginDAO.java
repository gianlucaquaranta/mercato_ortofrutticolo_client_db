package model.dao;

import exception.DAOException;
import model.domain.Credentials;
import model.domain.Role;

import java.sql.*;

public class LoginDAO {
    public Credentials authenticate(String username, String password) throws DAOException {
        int role = 3; // default = "invalid"

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement cs = conn.prepareCall("{call login(?,?)}")) {

            cs.setString(1, username);
            cs.setString(2, password);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    role = rs.getInt("role");
                }
            }
        }catch (SQLException e) {
            throw new DAOException("Errore durante il login: " + e.getMessage());
        }

        return new Credentials(username, password, Role.fromInt(role));
    }
}
