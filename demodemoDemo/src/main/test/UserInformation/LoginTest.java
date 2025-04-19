package UserInformation;

import main.DatabaseInfo;

import java.sql.*;

import static UserInformation.UserStorage.*;
import static main.DBConnection.addUser;

import main.DBConnection;
import org.junit.jupiter.api.*;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @BeforeAll
    static void init() throws SQLException {
        DBConnection db = new DBConnection("3312");
        boolean success = false;
        Connection conn = DBConnection.getConnection();
        String insertUser = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertUser)) {
            addUser(ps, "TEST", "TESTPASSWORD123");
            success = ps.executeUpdate() > 0;

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
        String removeUser = "DELETE FROM users WHERE username = 'TEST'";
        try(PreparedStatement ps = conn.prepareStatement(removeUser)) {
            ps.execute();
        }
    }

    @Test
    @DisplayName("Log in to TEST user")
    void logInTestUser() throws SQLException {
        String query = "SELECT password FROM users WHERE username = ?";
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
}
