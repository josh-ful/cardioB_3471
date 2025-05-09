package Controller;

import Exceptions.AlreadyRegisteredException;
import Exceptions.UserNotFoundException;
import FitnessCourse.Course;
import FitnessCourse.Exercise;
import UserInformation.CurrentUser;
import UserInformation.Login;
import UserInformation.Register;
import main.DBConnection;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @BeforeAll
    static void addTestUser() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Register.registerLogic("TEST", "password", "trainer");
        Login.loginLogic("TEST", "password");
    }
    @AfterAll
    static void removeTestUser() throws SQLException {
        DBConnection dbConnection = new DBConnection("3312");
        Connection conn = DBConnection.getConnection();

        //Remove TEST user
        String removeUser = "DELETE FROM userInfo WHERE username = 'TEST'";
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
        UserController.addCourseRegistration(999, "test course");

        Connection conn2 = DBConnection.getConnection();
        PreparedStatement getUserStmt = conn2.prepareStatement("SELECT id FROM userInfo WHERE username = ?");
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
        UserController.addCourseRegistration(999, "Test course");

        assertThrows(AlreadyRegisteredException.class, () -> {
            UserController.addCourseRegistration(999, "Test Course");
        });
    }
    @Test
    void addExerciseTest() throws SQLException {
	    String username = "TEST";
        CurrentUser.setName(username);
        Exercise e = new Exercise("test", "test exercise", 10);
        UserController.addExercise(e.getName(), e.getDescription());
        ArrayList<Exercise> exercises = UserController.getExercises();
        assertFalse(exercises.contains(e));
   }

   @Test
    void getExerciseTest() throws SQLException {
        assertEquals(UserController.getExercise(1).getName(), "Sunrise Sweat");
   }

   @Test
    void newExerciseTest() {
        UserController.newExercise("new", "new exercise");
   }

   @Test
    void getAllUserClassesTest() throws SQLException {
        UserController.addCourseRegistration(999, "test course");
       assertEquals(UserController.getAllUserClasses().size(), 1);
   }

   @Test
    void isRegisteredTest() throws SQLException {
        assertFalse(UserController.isRegistered(0));
   }

   @Test
    void registerForClassTest() throws SQLException {
        UserController.registerForClass(1);
        assertEquals(UserController.getAllUserClasses().size(), 1);
   }

   @Test
   void getAllExercisesTest() throws SQLException {
        ArrayList<Course> classes;
        classes = UserController.getAllExercises("group", "goat yoga");
        assertEquals(classes.size(), 2);
   }

    @Test
    void setUserAsJoinedTest() {
        UserController.setUserAsJoined(6);
    }

    @Test
    void isCourseJoinableTest() {
        assertTrue(UserController.isCourseJoinable(30));
    }

    @Test
    void getCurrentExerciseNameTest() {
        assertEquals(UserController.getCurrentExerciseName(30), "Resting");
    }

    @Test
    void getCurrentExerciseNameVoidTest() {
        assertEquals(UserController.getCurrentExerciseName(-1), "");
    }

    @Test
    void updateUserGoalsTest() {
        UserController.updateUserGoals(500, 500, 500, 500);
        assertEquals(CurrentUser.getWeightGoal(), 500);
        assertEquals(CurrentUser.getAvgSleepGoal(), 500);
        assertEquals(CurrentUser.getAvgCaloriesGoal(), 500);
        assertEquals(CurrentUser.getAvgWorkoutGoal(), 500);
    }
    



}
