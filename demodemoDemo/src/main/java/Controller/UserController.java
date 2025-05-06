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

    public UserController() {
//        System.out.println("UserController");
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
        PreparedStatement getUserStmt = conn2.prepareStatement("SELECT id FROM usersInfo WHERE username = ?");
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


    public void createDashboard(JFrame frame) throws SQLException {
        new UserMainDash(frame);
    }

    public static List<CourseExercise> getCourseExercisesForCourse(int courseId) {
        List<CourseExercise> list = new ArrayList<>();
        String sql = """
        SELECT 
          ce.id           AS link_id,
          e.id            AS exercise_id,
          e.name,
          e.description,
          ce.exercise_order
        FROM course_exercises ce
        JOIN exercises e 
          ON e.id = ce.exercise_id
        WHERE ce.course_id = ?
        ORDER BY ce.exercise_order
        """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, courseId);
            try (ResultSet rs = p.executeQuery()) {
                while (rs.next()) {
                    Exercise ex = new Exercise(
                            rs.getInt("exercise_id"),
                            rs.getString("name"),
                            rs.getString("description")
                    );
                    list.add(new CourseExercise(
                            rs.getInt("link_id"),
                            ex,
                            rs.getInt("exercise_order")
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error loading exercises: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }


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

    private static final String GET_LATEST = ""
            + "SELECT metric_value "
            + "  FROM daily_metrics "
            + " WHERE user_id = ? "
            + "   AND metric_type = ? "
            + " ORDER BY date DESC "
            + " LIMIT 1";

    private static final String GET_AVG = ""
            + "SELECT AVG(metric_value) AS avg_val "
            + "  FROM daily_metrics "
            + " WHERE user_id = ? "
            + "   AND metric_type = ? "
            + "   AND date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";





    public static void updateDailyMetrics(int userId, LocalDate date, Double weight, Double sleep, Double calories, Double wktDuration) {
        String sql = """
            INSERT INTO daily_metrics
               (user_id, metric_date, weight, sleep, calories, wktduration)
            VALUES (?, ?, ?,     ?,     ?,        ?)
            ON DUPLICATE KEY UPDATE
              weight      = VALUES(weight),
              sleep       = VALUES(sleep),
              calories    = VALUES(calories),
              wktduration = VALUES(wktduration)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, userId);
            p.setDate(2, Date.valueOf(date));
            if (weight   != null) p.setDouble(3, weight);   else p.setNull(3, java.sql.Types.DOUBLE);
            if (sleep    != null) p.setDouble(4, sleep);    else p.setNull(4, java.sql.Types.DOUBLE);
            if (calories != null) p.setDouble(5, calories); else p.setNull(5, java.sql.Types.DOUBLE);
            if (wktDuration != null) p.setDouble(6, wktDuration); else p.setNull(6, java.sql.Types.DOUBLE);

            p.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error updating daily metrics: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void updateUserGoals(int userId, double weightGoal, double sleepGoal, double caloriesGoal, double workoutGoal) {
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

            p.setInt(1, userId);
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

    public static double getUserGoal(int userId, String goalType) {
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

            ps.setInt(1, userId);
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

}
