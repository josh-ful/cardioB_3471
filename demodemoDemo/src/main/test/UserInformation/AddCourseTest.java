package UserInformation;

import Controller.UserController;
import main.DBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

import static main.DBConnection.addUser;
import static org.junit.jupiter.api.Assertions.*;

public class AddCourseTest {

    @BeforeEach
    void login() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Connection conn = DBConnection.getConnection();
        String insertUser = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertUser)) {
            addUser(ps, "TEST", "TESTPASSWORD123");
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Sorry, that username is already in use.!");
            throw new SQLException("User already exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void clean() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String removeUser = "DELETE FROM users WHERE username = 'TEST'";
        try(PreparedStatement ps = conn.prepareStatement(removeUser)) {
            ps.execute();
        }
    }

    @Test
    void addCourse() throws SQLException {
        String username = "TEST";
        CurrentUser.setName(username);
        UserController.addCourseRegistration("self", 999, "Test Course");


        String query = "SELECT course_id FROM course_registrations WHERE id = ?";
        Connection conn = DBConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, 999);
            ResultSet rs = stmt.executeQuery();

            assertNotNull(rs);
            int ans = 0;
            if(rs.next()) ans = rs.getInt("course_id");

            assertEquals(ans, 999);
        }
    }
}
