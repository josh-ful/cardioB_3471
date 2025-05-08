package Controller;

import UserInformation.Login;
import UserInformation.Register;
import main.DBConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainerControllerTest {
    @BeforeAll
    static void logInToTestTrainerAccount() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Register.registerLogic("TestTrainer", "TestTrainerPassword", "trainer");
        Login.loginLogic("TestTrainer", "TestTrainerPassword");
        TrainerController.createClass("Class 1", "first class", "");
    }

    @AfterAll
    static void cleanUp() throws SQLException{
        Connection conn = DBConnection.getConnection();

        //Remove TEST user
        String removeUser = "DELETE FROM userInfo WHERE username = 'TestTrainer'";
        try(PreparedStatement ps = conn.prepareStatement(removeUser)) {
            ps.execute();
        }
        String removeClass = "DELETE FROM courses WHERE name = 'TestClass'";
        try(PreparedStatement ps = conn.prepareStatement(removeClass)) {
            ps.execute();
        }
        removeClass = "DELETE FROM courses WHERE name = 'Class 1'";
        try(PreparedStatement ps = conn.prepareStatement(removeClass)) {
            ps.execute();
        }
    }

    @Test
    void getClassesForCurrentTrainerTest() {
        assertEquals(TrainerController.getClassesForCurrentTrainer().size(), 1);
    }

    @Test
    void createClassTest() throws SQLException{
        Connection conn = DBConnection.getConnection();
        TrainerController.createClass("TestClass", "TestClass", "");
        String removeClass = "DELETE FROM courses WHERE name = 'TestClass'";
        try(PreparedStatement ps = conn.prepareStatement(removeClass)) {
            ps.execute();
        }
    }

    @Test
    void updateClassTest() {
        TrainerController.updateClass(35, "CODING", "5 sets of infinite", "");
    }
}
