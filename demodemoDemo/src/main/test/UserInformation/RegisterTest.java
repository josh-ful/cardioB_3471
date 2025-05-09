package UserInformationTest;

import Exceptions.AlreadyRegisteredException;
import UserInformation.Login;
import UserInformation.Register;
import main.DBConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest {
    @Test
    void registerSuccess() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Register.registerLogic("TEST", "password", "general");
        assertTrue(Login.loginLogic("TEST", "password"));

        Connection conn = DBConnection.getConnection();
        String removeUser = "DELETE FROM userInfo WHERE username = 'TEST'";
        try(PreparedStatement ps = conn.prepareStatement(removeUser)) {
            ps.execute();
        }
    }

    @Test
    void alreadyRegistered() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Register.registerLogic("TEST", "password", "general");
        assertTrue(Login.loginLogic("TEST", "password"));

        assertThrows(AlreadyRegisteredException.class, () -> {
            Register.registerLogic("TEST", "password", "general");
        });

        Connection conn = DBConnection.getConnection();
        String removeUser = "DELETE FROM userInfo WHERE username = 'TEST'";
        try(PreparedStatement ps = conn.prepareStatement(removeUser)) {
            ps.execute();
        }
    }
}
