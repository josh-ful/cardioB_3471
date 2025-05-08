package Controller;

import Exceptions.AlreadyRegisteredException;
import Exceptions.UserNotFoundException;
import FitnessCourse.CourseExercise;
import FitnessCourse.Exercise;
import FitnessCourse.Course;
import UserInformation.CurrentUser;
import UserInterface.UserMainDash;
import UserInterface.addExercise.ExerciseLogHelper;
import UserInterface.addExercise.ExerciseLogHelperCSV;
import UserInterface.addExercise.ExerciseLogHelperSQL;
import UserInformation.DailyMetrics.*;
import main.DBConnection;

import main.DatabaseInfo;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * this class serves as the general user type controller
 */
public class UserController implements Controller {
    /**
     * constructs object of type UserController
     *
     */
    public UserController() {
//        System.out.println("UserController");
    }
    /**
     * inputs weight and sets it to current weight
     *
     * @param weight int current weight
     */
    public static void enterWeight(int weight) {
        CurrentUser.setWeight(weight);
    }

    /**
     * enters exercise information
     *
     * @param name        of exercise
     * @param description of exercise
     */
    public static void addExercise(String name, String description, Integer duration) {
        Exercise e = new Exercise(name);
        e.setDescription(description);
        e.setDuration(duration);
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
     * clears exercises from current user
     *
     */
    public static void clearExercises() {
        CurrentUser.clearExercises();
    }
    /**
     * gets exercises from current user
     *
     * @return ArrayList<Exercise> list of current user's exercises
     */
    public static ArrayList<Exercise> getExercises() {
        return CurrentUser.getExercises();
    }
    /**
     * gets matrix of current user's exercises
     *
     * @return String[][] matrix of current user's exercises
     */
    public static String[][] getTableMatrix() {
        return ExerciseLogHelper.getTableMatrix();
    }
    /**
     * gets current user's id
     *
     * @return int id of user
     */
    public static int getUserId() throws SQLException {
        return CurrentUser.getId();
    }
    /**
     * gets classes that the current user is registered for
     *
     * @return ArrayList<Course> list of current user's courses
     */
    public static ArrayList<Course> getAllUserClasses() {
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
                int courseId = registrationResults.getInt("id");
                String name = registrationResults.getString("name");
                String desc = registrationResults.getString("description");
                String type = registrationResults.getString("type");
                Course retExercise = new Course();
                retExercise.setId(courseId);
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
    /**
     * registers user for a class by inserting into their course_registrations
     * in the database
     *
     * @return boolean if successfully registered
     */
    //todo not a boolean?
    public static boolean registerForClass(int courseId) throws SQLException{
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
    /**
     * gets list of courses user is registered for
     *
     * @return ArrayList<Course> list of courses of user
     */
    public static ArrayList<Course> getAllCourses(String type, String query) throws SQLException{
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
    /**
     * gets list of courses user is registered for
     *
     * @return ArrayList<Course> list of courses of user
     */
    public static int getSessionId(Course course) {
        String sql = "SELECT session_id FROM active_courses WHERE course_id = ?";
        int sessionID = 0;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, course.getId());
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    // getString of current exercise
                    sessionID = rs.getInt("session_id");
                } else {//no current exercise
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error getting current exercise: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return sessionID;
    }
    /**
     * displays user dashboard to frame
     *
     * @param frame JFrame dashboard is displayed to
     */
    public void createDashboard(JFrame frame) throws SQLException {
        new UserMainDash(frame);
    }
    /**
     * user's state is set to joined
     *
     * @param sessionId id of session
     */
    public static void setUserAsJoined(int sessionId) {
        String sql = "UPDATE active_courses SET total_joined = total_joined + 1 WHERE session_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                System.err.println("Warning: updated " + rows + " rows for session_id=" + sessionId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error incrementing total join amount: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * determines if a course is joinable
     *
     * @return boolean if a course is joinable
     */
    public static boolean isCourseJoinable(int courseId) {
        String sql = "SELECT joinable FROM courses WHERE id = ?";
        boolean join = false;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, courseId);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    // getBoolean will map 0=false, 1=true
                    return rs.getBoolean("joinable");
                } else {
                    //no such course, treat as not joinable (or throw)
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error checking course status: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }
    /**
     * gets the current exercise name
     *
     * @param courseId id of course
     * @return String name of exercise
     */
    public static String getCurrentExerciseName(int courseId) {
        String sql = "SELECT current_exercise FROM active_courses WHERE course_id = ?";
        String exerciseName = "";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, courseId);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    // getString of current exercise
                    exerciseName = rs.getString("current_exercise");
                } else {//no current exercise
                    return "";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error getting current exercise: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return exerciseName;
    }
    /**
     * updates user's goals by taking in new values and updating them in the database
     *
     * @param weightGoal double goal weight
     * @param sleepGoal double goal sleep
     * @param caloriesGoal double goal cal
     * @param workoutGoal double goal workout
     */
    public static void updateUserGoals(double weightGoal, double sleepGoal, double caloriesGoal, double workoutGoal) {
        String sql = """
    INSERT INTO user_goals
      (user_id, weight_goal, avg_sleep_goal, avg_calories_goal, avg_workout_goal)
    VALUES (?, ?, ?, ?, ?)
    ON DUPLICATE KEY UPDATE
      weight_goal        = VALUES(weight_goal),
      avg_sleep_goal     = VALUES(avg_sleep_goal),
      avg_calories_goal  = VALUES(avg_calories_goal),
      avg_workout_goal   = VALUES(avg_workout_goal)
    """;
        CurrentUser.setCurrentWeight(weightGoal);
        CurrentUser.setAvgSleep(sleepGoal);
        CurrentUser.setAvgCalories(caloriesGoal);
        CurrentUser.setAvgWorkout(workoutGoal);

        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, getUserId());
            p.setDouble(2, weightGoal);
            p.setDouble(3, sleepGoal);
            p.setDouble(4, caloriesGoal);
            p.setDouble(5, workoutGoal);

            p.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error saving goals: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static double getUserGoal(String goalType) {
        String column;
        switch (goalType.toLowerCase()) {
            case "weight":
                column = "weight_goal";
                break;
            case "sleep":
                column = "avg_sleep_goal";
                break;
            case "calories":
                column = "avg_calories_goal";
                break;
            case "workout":
                column = "avg_workout_goal";
                break;
            default:
                throw new IllegalArgumentException("Unknown goal type: " + goalType);
        }

        String sql = "SELECT " + column + " FROM user_goals WHERE user_id = ?";
        try ( Connection conn = DBConnection.getConnection();
              PreparedStatement ps = conn.prepareStatement(sql) ) {

            ps.setInt(1, getUserId());
            try ( ResultSet rs = ps.executeQuery() ) {
                if (rs.next()) {
                    return rs.getDouble(column);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error loading " + goalType + " goal: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // no row, or error → treat as zero
        return 0.0;
    }
    /**
     * gets the current exercise name
     *
     * @param date date of daily metric
     * @param w desired weight to input
     * @param s hours of sleep to input
     * @param c calories to input
     * @param wkt workout time
     */
    public static void addDailyMetric(LocalDate date, Double w, Double s, Double c, Double wkt) throws SQLException {
        DailyMetric dm = new DailyMetric( w, s, c, wkt, date);

        DailyMetricDAO.updateDailyMetrics(dm);
    }
}