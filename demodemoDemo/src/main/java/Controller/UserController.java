package Controller;

import Exceptions.AlreadyRegisteredException;
import Exceptions.UserNotFoundException;
import FitnessCourse.Exercise;
import FitnessCourse.ExerciseClass;
import UserInformation.CurrentUser;
import UserInterface.UserMenuScene;
import UserInterface.addExercise.ExerciseLogHelper;
import UserInterface.addExercise.ExerciseLogHelperCSV;
import UserInterface.addExercise.ExerciseLogHelperSQL;
import main.DBConnection;
import UserInterface.addExercise.ExerciseLogHelper;

import main.DBConnection;
import main.DatabaseInfo;
import org.junit.jupiter.api.Disabled;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.sql.*;
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

    public static ExerciseClass getExercise(int courseId) throws SQLException {
        ExerciseClass retExercise = null;
        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement selfStmt = conn.prepareStatement(
                    "SELECT name, description, time, type FROM courses WHERE id = ?"
            );
            selfStmt.setInt(1, courseId);
            ResultSet rs = selfStmt.executeQuery();
            if (rs.next()) {
                System.out.println("Got exercise");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                String type = rs.getString("type");
                retExercise = new ExerciseClass();
                retExercise.setName(name);
                retExercise.setDescription(desc);
                retExercise.setType(type);
                //retExercise.setTrainerId(trainerId);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            System.out.println("Exception with getting exercise");
            //throw new SQLException("Error getting exercise");
        }
        return retExercise;
    }

    public static int getUserId() throws SQLException {
        return CurrentUser.getId();
    }

    public static ArrayList getAllExercises(int userId) {
        ArrayList<ExerciseClass> exerciseClassList = new ArrayList<>();
        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement registrationStmt = conn.prepareStatement(
                    "SELECT course_id FROM course_registrations WHERE user_id = ?"
            );
            registrationStmt.setInt(1, userId);
            ResultSet registrationResults = registrationStmt.executeQuery();

            while (registrationResults.next()) {
                int courseId = registrationResults.getInt("course_id");
                ExerciseClass exercise = UserController.getExercise(courseId);
                exerciseClassList.add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exerciseClassList;
    }

    public static void isRegistered(int courseId) throws SQLException{
        Connection conn = main.DBConnection.getConnection();
        PreparedStatement checkStmt = conn.prepareStatement(
        "SELECT * FROM course_registrations WHERE user_id = ? AND course_id = ?"
        );
        checkStmt.setInt(1, getUserId());
        checkStmt.setInt(2, courseId);
        ResultSet checkRs = checkStmt.executeQuery();
        if (checkRs.next()) {
            throw new AlreadyRegisteredException("You're already registered for this class.");
        }
    }

    public static void registerForClass(String courseType, int courseId, String courseName) throws SQLException {
        isRegistered(courseId);

        Connection conn = main.DBConnection.getConnection();
        PreparedStatement insertStmt = conn.prepareStatement(
        "INSERT INTO course_registrations (user_id, course_id, course_type) VALUES (?, ?, ?)"
        );
        insertStmt.setInt(1, UserController.getUserId());
        insertStmt.setInt(2, courseId);
        insertStmt.setString(3, courseType);
        insertStmt.executeUpdate();
    }

    public static ArrayList<ExerciseClass> getAllExercises(String type, String query) throws SQLException{
        String sql = "SELECT id, name, description FROM courses WHERE type LIKE ? AND name LIKE ?";

        ArrayList<ExerciseClass> exerciseClassList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseName = rs.getString("name");
                String courseDesc = rs.getString("description");
                ExerciseClass exercise = new ExerciseClass();
                exercise.setId(courseId);
                exercise.setName(courseName);
                exercise.setDescription(courseDesc);
                exerciseClassList.add(exercise);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Database error occurred");
        }
        return exerciseClassList;
    }

    public void createDashboard(JFrame frame) {
        new UserMenuScene(frame);
    }
}
