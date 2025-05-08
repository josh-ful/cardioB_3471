package Controller;

import FitnessCourse.CourseExercise;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void addExerciseToCourseTest() throws SQLException {
        int n = TrainerController.getCourseExercisesForCourse(35).size();
        TrainerController.addExerciseToCourse(35, "New", "desc", n+1);
        assertEquals(n+1, TrainerController.getCourseExercisesForCourse(35).size());
    }

    @Test
    void removeCourseExerciseByLinkIdTest() {
        CourseExercise c = TrainerController.getCourseExercisesForCourse(35).get(0);
        int n = TrainerController.getCourseExercisesForCourse(35).size();
        TrainerController.removeCourseExerciseByLinkId(c.getLinkId(), 35);
        assertEquals(n-1, TrainerController.getCourseExercisesForCourse(35).size());
    }

    @Test
    void linkExistingExerciseToCourseTest() {
        int n = TrainerController.getCourseExercisesForCourse(35).size();
        TrainerController.linkExistingExerciseToCourse(35, 1, 9);
        assertEquals(n+1, TrainerController.getCourseExercisesForCourse(35).size());
    }

    @Test
    void searchExercisesTest() {
        int n = TrainerController.searchExercises("Test").size();
        assertEquals(4, n);
    }

    @Test
    void setCourseJoinableTest() {
        TrainerController.setCourseJoinable(35, true);
        TrainerController.setCourseJoinable(35, false);
    }

    @Test
    void startCourseSessionTest() {
        int n = TrainerController.startCourseSession(35, TrainerController.getCourseExercisesForCourse(35).get(0).getExercise().getName());
        assertEquals(9, n);
        TrainerController.endCourseSession(35);
    }

    @Test
    void updateCourseSessionTest() {
    }

    @Test
    void endCourseSessionTest() {
        TrainerController.startCourseSession(35, "TestExercise1");
        TrainerController.endCourseSession(35);
    }

    @Test
    void getRegistrationCountsTest() {
        assertEquals(TrainerController.getRegistrationCounts(35).size(), 0);
    }

    @Test
    void getSessionJoinCountsTest() {
        assertEquals(TrainerController.getSessionJoinCounts(35).size(), 0);
    }
}
