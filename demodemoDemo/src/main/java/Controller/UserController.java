package Controller;

import Exceptions.AlreadyRegisteredException;
import Exceptions.UserNotFoundException;
import FitnessCourse.Exercise;
import FitnessCourse.Course;
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
    }


    public static void setCurrentUserId() {
        if (DatabaseInfo.states.get("SQL")){
            try {
                Connection conn = main.DBConnection.getConnection();
                PreparedStatement getUserStmt = conn.prepareStatement("SELECT id FROM users WHERE username = ?");
                getUserStmt.setString(1, CurrentUser.getName());
                ResultSet userRs = getUserStmt.executeQuery();

                if (!userRs.next()) {
                    throw new UserNotFoundException("User not found in database.");
                }
                CurrentUser.setId(userRs.getInt("id"));
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
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

    public static Course getExercise(int courseId) throws SQLException {
        Course retExercise = null;
        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement selfStmt = conn.prepareStatement(
                    "SELECT name, description, time, type FROM courses WHERE id = ?"
            );
            selfStmt.setInt(1, courseId);
            ResultSet rs = selfStmt.executeQuery();
            if (rs.next()) {
                //System.out.println("Got exercise");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                String type = rs.getString("type");
                retExercise = new Course();
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

    public static ArrayList getAllUserExercises() {
        ArrayList<Course> courseList = new ArrayList<>();
        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement registrationStmt = conn.prepareStatement(
                    "SELECT c.id, c.name, c.description, c.type " +
                            "FROM courses c " +
                            "WHERE c.id IN ( " +
                            "SELECT cr.course_id " +
                            "FROM course_registrations cr " +
                            "WHERE cr.user_id = ?" +
                            ");"
            );
            registrationStmt.setInt(1, CurrentUser.getId());
            ResultSet registrationResults = registrationStmt.executeQuery();

            while (registrationResults.next()) {
                //int courseId = registrationResults.getInt("id");
                String name = registrationResults.getString("name");
                String desc = registrationResults.getString("description");
                String type = registrationResults.getString("type");
                Course retExercise = new Course();
                retExercise.setName(name);
                retExercise.setDescription(desc);
                retExercise.setType(type);
                courseList.add(retExercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //todo throw exception, or error message
        }
        return courseList;
    }

    public static boolean isRegistered(int courseId) throws SQLException{
        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT * FROM course_registrations WHERE user_id = ? AND course_id = ?"
            );
            checkStmt.setInt(1, getUserId());
            checkStmt.setInt(2, courseId);
            ResultSet checkRs = checkStmt.executeQuery();
            if (checkRs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error registering for classes");
        }

        return false;
    }

    public static boolean registerForClass(int courseId) {
        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO course_registrations (user_id, course_id) VALUES (?, ?)"
            );
            insertStmt.setInt(1, UserController.getUserId());
            insertStmt.setInt(2, courseId);
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //todo rethrow??
            return false;
        }
        return true;
    }

    public static ArrayList<Course> getAllExercises(String type, String query) throws SQLException{
        String sql = "SELECT id, name, description FROM courses WHERE type LIKE ? AND name LIKE ?";

        ArrayList<Course> courseList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseName = rs.getString("name");
                String courseDesc = rs.getString("description");
                Course exercise = new Course();
                exercise.setId(courseId);
                exercise.setName(courseName);
                exercise.setDescription(courseDesc);
                courseList.add(exercise);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Database error occurred");
        }
        return courseList;
    }

    //TODO probably remove this
    // todo def put this in a try block
    public static void addCourseRegistration( int courseId, String courseName) throws SQLException {
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
                "SELECT * FROM course_registrations WHERE user_id = ? AND course_id = ?"
        );
        checkStmt.setInt(1, userId);
        checkStmt.setInt(2, courseId);
        ResultSet checkRs = checkStmt.executeQuery();
        if (checkRs.next()) {
            throw new AlreadyRegisteredException("You're already registered for this class.");
        }

        //finally register
        PreparedStatement insertStmt = conn2.prepareStatement(
                "INSERT INTO course_registrations (user_id, course_id) VALUES (?, ?)"
        );
        insertStmt.setInt(1, userId);
        insertStmt.setInt(2, courseId);
        insertStmt.executeUpdate();
    }

    public void createDashboard(JFrame frame) {
        new UserMenuScene(frame);
    }
}
