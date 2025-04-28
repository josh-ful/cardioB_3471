package UserInformation;

import Controller.UserController;
import Exceptions.AlreadyRegisteredException;
import Exceptions.UserNotFoundException;
import main.DBConnection;
import org.junit.jupiter.api.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

import static main.DBConnection.addUser;
import static org.junit.jupiter.api.Assertions.*;

public class AddCourseTest {

    @BeforeAll
    static void addTestUser() throws SQLException {
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
    @AfterAll
    static void removeTestUser() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Connection conn = DBConnection.getConnection();



        //Remove TEST user
        String removeUser = "DELETE FROM users WHERE username = 'TEST'";
        try(PreparedStatement ps = conn.prepareStatement(removeUser)) {
            ps.execute();
        }
    }

    @AfterEach
    void removeTestCourse() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Connection conn = DBConnection.getConnection();
        //Delete courses associated with TEST user
        String deleteCourse = "DELETE FROM course_registrations WHERE course_id = 999";
        try(PreparedStatement delete = conn.prepareStatement(deleteCourse)) {
            delete.execute();
        }
    }

    @Test
    void addCourse() throws SQLException {
        String username = "TEST";
        CurrentUser.setName(username);
        UserController.addCourseRegistration("self", 999, "Test Course");

        Connection conn2 = DBConnection.getConnection();
        PreparedStatement getUserStmt = conn2.prepareStatement("SELECT id FROM users WHERE username = ?");
        getUserStmt.setString(1, username);
        ResultSet userRs = getUserStmt.executeQuery();

        if (!userRs.next()) {
            throw new UserNotFoundException("User not found in database.");
        }
        int userId = userRs.getInt("id");


        String query = "SELECT * FROM course_registrations WHERE course_id = ?";
        Connection conn = DBConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, 999);
            ResultSet rs = stmt.executeQuery();

            assertNotNull(rs);
            int ans = 0;
            if(rs.next())
                ans = rs.getInt("course_id");

            assertEquals(ans, 999);
        }
    }

    @Test
    void duplicateCourseRegistration() throws SQLException {
        String username = "TEST";
        CurrentUser.setName(username);
        UserController.addCourseRegistration("self", 999, "Test Course");

        assertThrows(AlreadyRegisteredException.class, () -> {
            UserController.addCourseRegistration("self", 999, "Test Course");
        });
    }
}
