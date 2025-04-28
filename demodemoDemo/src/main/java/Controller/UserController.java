package Controller;

import Exceptions.AlreadyRegisteredException;
import Exceptions.UserNotFoundException;
import FitnessCourse.Exercise;
import UserInformation.CurrentUser;
import UserInterface.UserMenuScene;
import UserInterface.addExercise.ExerciseLogHelper;
import UserInterface.addExercise.ExerciseLogHelperCSV;
import UserInterface.addExercise.ExerciseLogHelperSQL;
import main.DBConnection;
import main.DatabaseInfo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * this class serves as the general user type controller
 */
public class UserController implements Controller {

    public UserController() {
//        System.out.println("UserController");
        if (DatabaseInfo.states.get("SQL")) {
            // Establish SQL connection to ExerciseLogHelper
        } else {
            new ExerciseLogHelperCSV("src/resources/testCreateExercise.csv");
            CurrentUser.importExercises(ExerciseLogHelperCSV.readCSV());
        }
    }


    public static void enterWeight(int weight) {
        CurrentUser.setWeight(weight);
    }

    /**
     * enters exercise information
     *
     * @param name        of exercise
     * @param description of exercise
     */
    public static void addExercise(String name, String description) {
        Exercise e = new Exercise(name);
        e.setDescription(description);
//        System.out.println("Name:" + e.getName());
//        System.out.println("Description: " + e.getDescription());
        CurrentUser.addExercise(e);

        if (DatabaseInfo.states.get("SQL")) {
            // TODO SQL Implementation
            ExerciseLogHelperSQL.addExercise(name, description);
        } else {
            ExerciseLogHelperCSV.update();
        }
    }

    /**
     * adds exercise
     *
     * @param name        String name
     * @param description String description
     */
    public static void newExercise(String name, String description) {
        ExerciseLogHelper.addExercise(name, description);
    }

    /**
     *
     */
    public static void clearExercises() {
        CurrentUser.clearExercises();
    }

    public static ArrayList<Exercise> getExercises() {
        return CurrentUser.getExercises();
    }

    public static String[][] getTableMatrix() {
        return ExerciseLogHelper.getTableMatrix();
    }

    public static void addCourseRegistration(String courseType, int courseId, String courseName) throws SQLException {
        String username = CurrentUser.getName();

        Connection conn2 = DBConnection.getConnection();
        //get id from username
        //TODO make this something stored in UserStorage
        PreparedStatement getUserStmt = conn2.prepareStatement("SELECT id FROM users WHERE username = ?");
        getUserStmt.setString(1, username);
        ResultSet userRs = getUserStmt.executeQuery();

        if (!userRs.next()) {
            throw new UserNotFoundException("User not found in database.");
        }
        int userId = userRs.getInt("id");

        //check edge case already registered
        PreparedStatement checkStmt = conn2.prepareStatement(
                "SELECT * FROM course_registrations WHERE user_id = ? AND course_id = ? AND course_type = ?"
        );
        checkStmt.setInt(1, userId);
        checkStmt.setInt(2, courseId);
        checkStmt.setString(3, courseType);
        ResultSet checkRs = checkStmt.executeQuery();
        if (checkRs.next()) {
            throw new AlreadyRegisteredException("You're already registered for this class.");
        }

        //finally register
        PreparedStatement insertStmt = conn2.prepareStatement(
                "INSERT INTO course_registrations (user_id, course_id, course_type) VALUES (?, ?, ?)"
        );
        insertStmt.setInt(1, userId);
        insertStmt.setInt(2, courseId);
        insertStmt.setString(3, courseType);
        insertStmt.executeUpdate();
    }

    public void createDashboard(JFrame frame) {
        new UserMenuScene(frame);
    }
}
