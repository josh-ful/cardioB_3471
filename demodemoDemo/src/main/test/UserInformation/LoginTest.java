package UserInformation;

import Exceptions.IncorrectPasswordException;
import Exceptions.UserNotFoundException;

import java.sql.*;

import static main.DBConnection.addUser;

import main.DBConnection;
import org.junit.jupiter.api.*;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @BeforeAll
    static void init() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Connection conn = DBConnection.getConnection();
        String insertUser = "INSERT INTO userInfo (username, password, type) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertUser)) {
            addUser(ps, "TEST", "TESTPASSWORD123", "general");
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Sorry, that username is already in use.!");
            throw new SQLException("User already exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @AfterAll
    static void clean() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String removeUser = "DELETE FROM userInfo WHERE username = 'TEST'";
        try(PreparedStatement ps = conn.prepareStatement(removeUser)) {
            ps.execute();
        }
    }

    @Test
    @DisplayName("Test SQL connection")
    void logInTestUser() throws SQLException {
        String query = "SELECT password FROM userInfo WHERE username = ?";
        Connection conn = DBConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "TEST");
            ResultSet rs = stmt.executeQuery();

            assertNotNull(rs);
            rs.next();
            String hashedPassword = rs.getString("password");

            assertTrue(BCrypt.checkpw("TESTPASSWORD123", hashedPassword));
        }
    }

    @Test
    @DisplayName("Success")
    void successCaseLoginLogic() throws SQLException {
        assertTrue(Login.loginLogic("TEST", "TESTPASSWORD123"));
    }

    @Test
    @DisplayName("Password incorrect case")
    void incorrectCasePasswordLoginLogic() throws SQLException {
        assertThrows(IncorrectPasswordException.class, () -> {
            Login.loginLogic("TEST", "testpas");
        });
    }

    @Test
    @DisplayName("Incorrect username")
    void incorrectUserNameCase() {
        assertThrows(UserNotFoundException.class, () -> {
            Login.loginLogic("qwerty", "STRONGPASSWORD123");
        });
    }
}
