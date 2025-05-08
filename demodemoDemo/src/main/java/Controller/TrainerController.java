package Controller;
/*
* this class serves as the trainer user type controller
*/
import javax.swing.*;
import FitnessCourse.Course;
import FitnessCourse.CourseExercise;
import FitnessCourse.Exercise;
import UserInformation.CurrentUser;
import main.DBConnection;
import UserInterface.Trainer.TrainerMenuScene;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrainerController implements Controller {
    /**
     * creates dashboard on frame
     *
     * @param frame JFrame which dashboard is displayed on
     */
    public void createDashboard(JFrame frame) throws SQLException {
        new TrainerMenuScene(frame);
    }
    /**
     * gets current trainer's classes as a list
     *
     * @return List<Course> list of current trainers classes
     */
    //Fetches all courses records for the currently logged-in trainer.
    public static List<Course> getClassesForCurrentTrainer() {
        List<Course> classes = new ArrayList<>();
        String sql = "SELECT id, name, type, trainer_id, description, time FROM courses WHERE trainer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, CurrentUser.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                classes.add(new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("trainer_id"),
                        rs.getString("description"),
                        rs.getString("time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
    /**
     * creates a class by inserting information into the database
     *
     * @param name String name of course
     * @param description String description of course
     * @param schedule String schedule of course
     */
    public static void createClass(String name, String description, String schedule) {
        String sql = "INSERT INTO courses (name, type, trainer_id, description, time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String type;
            if(!schedule.isEmpty()) {
                type = "group";
            }
            else{
                type = "self";
            }
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setInt(3, CurrentUser.getId());
            stmt.setString(4, description);
            stmt.setString(5, schedule);
            stmt.executeUpdate();

//            JOptionPane.showMessageDialog(null, "Class created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating class: " + e.getMessage());
        }
    }
    /**
     * updates a class by modifying information in the database
     *
     * @param courseID int ID of course
     * @param name String name of course
     * @param description String description of course
     * @param schedule String schedule of course
     */
    public static void updateClass(int courseID, String name, String description, String schedule) {
        String sql = "UPDATE courses "
                + "SET name = ?, "
                + "    type = ?, "
                + "    description = ?, "
                + "    time = ? "
                + "WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String type;
            if(!schedule.isEmpty()) {
                type = "group";
            }
            else{
                type = "self";
            }
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, description);
            stmt.setString(4, schedule);
            stmt.setInt(5, courseID);

            stmt.executeUpdate();

//            JOptionPane.showMessageDialog(null, "Class edited successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing class: " + e.getMessage());
        }
    }

    /**
     * adds exercise to a course in the database
     *
     * @param courseId int ID of course
     * @param eName String description of exercise
     * @param eDesc String description of course
     * @param orderIndex int
     */
    public static void addExerciseToCourse(int courseId, String eName, String eDesc, int orderIndex) throws SQLException {
        String insertEx =
                "INSERT INTO exercises (name, description) VALUES (?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p1 = c.prepareStatement(insertEx, Statement.RETURN_GENERATED_KEYS)) {
            p1.setString(1, eName);
            p1.setString(2, eDesc);
            p1.executeUpdate();
            ResultSet keys = p1.getGeneratedKeys();
            if (!keys.next()) throw new SQLException("No exercise ID returned");
            int exId = keys.getInt(1);

            String linkSql =
                    "INSERT INTO course_exercises (course_id, exercise_id, exercise_order) VALUES (?, ?, ?)";
            try (PreparedStatement p2 = c.prepareStatement(linkSql)) {
                p2.setInt(1, courseId);
                p2.setInt(2, exId);
                p2.setInt(3, orderIndex);
                p2.executeUpdate();
            }
        }
    }

    //remove
    /**
     * Remove exactly the one course_exercises row (by its PK)
     * and resequence the remaining ones.
     *
     * @param linkId
     * @param courseId
     */
    public static void removeCourseExerciseByLinkId(int linkId, int courseId) {
        String deleteSql =
                "DELETE FROM course_exercises WHERE id = ?";
        String selectSql =
                "SELECT id FROM course_exercises WHERE course_id = ? ORDER BY exercise_order";
        String updateSql =
                "UPDATE course_exercises SET exercise_order = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            // 1) delete exactly that one link-row
            try (PreparedStatement del = conn.prepareStatement(deleteSql)) {
                del.setInt(1, linkId);
                del.executeUpdate();
            }

            // 2) resequence the remainder
            List<Integer> ids = new ArrayList<>();
            try (PreparedStatement sel = conn.prepareStatement(selectSql)) {
                sel.setInt(1, courseId);
                try (ResultSet rs = sel.executeQuery()) {
                    while (rs.next()) ids.add(rs.getInt("id"));
                }
            }
            try (PreparedStatement upd = conn.prepareStatement(updateSql)) {
                for (int i = 0; i < ids.size(); i++) {
                    upd.setInt(1, i + 1);
                    upd.setInt(2, ids.get(i));
                    upd.executeUpdate();
                }
            }

            conn.commit();
            JOptionPane.showMessageDialog(null, "Exercise removed from course.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error removing exercise: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            try {//innoDB don't corrupt or add half of data
                DBConnection.getConnection().rollback();
            } catch (Exception ignore) {}
        } finally {
            try {
                DBConnection.getConnection().setAutoCommit(true);
            } catch (Exception ignore) {}
        }
    }
    /**
     * gets list of course exercises from the course
     *
     * @param courseId int ID of course
     * @return List<CourseExercise> list of course exercises
     */
    //added query to get all exercises in a class
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
    /**
     * deals with existing exercises and links to course
     *
     * @param courseId int ID of course
     * @param exerciseId int ID of exercise
     * @param orderIndex int
     */
    //added diff func than addExercise that deals with existing exercises
    public static void linkExistingExerciseToCourse(int courseId, int exerciseId, int orderIndex) {
        String sql = "INSERT INTO course_exercises (course_id, exercise_id, exercise_order) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            stmt.setInt(2, exerciseId);
            stmt.setInt(3, orderIndex);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Exercise linked to course successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error linking exercise: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    /**
     * searches by querying database for list of exercises
     *
     * @param term String
     */

    //search by querying database for list of exercises
    public static List<Exercise> searchExercises(String term) {
        List<Exercise> list = new ArrayList<>();
        String sql =
                "SELECT id, name, description " +
                        "  FROM exercises " +
                        " WHERE LOWER(name) LIKE ? " +
                        "    OR LOWER(description) LIKE ? " +
                        " ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String wildcard = "%" + term.toLowerCase() + "%";
            stmt.setString(1, wildcard);
            stmt.setString(2, wildcard);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Exercise(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error searching exercises: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return list;
    }

    /**
     * searches by querying database for list of exercises
     *
     * @param courseId int id of course
     * @param joinable boolean if course is live
     */
    public static void setCourseJoinable(int courseId, boolean joinable) {
        String sql = "UPDATE courses SET joinable = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setBoolean(1, joinable);
            p.setInt(2, courseId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error updating course status: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    /**
     * searches by querying database for list of exercises
     *
     * @param courseId int id of course
     * @param initialExercise String current exercise to start on
     */
    //add entry to table
    public static int startCourseSession(int courseId, String initialExercise) {
        String sql = """
      INSERT INTO active_courses (course_id, current_exercise)
           VALUES (?, ?)
      """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            p.setInt(1, courseId);
            p.setString(2, initialExercise);
            p.executeUpdate();
            try (ResultSet rs = p.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error starting session: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    /**
     * updates current exer
     *
     * @param sessionId int id of session
     * @param newExercise String exercise to be added
     */
    //update current exercise
    public static void updateCourseSession(int sessionId, String newExercise) {
        String sql = """
      UPDATE active_courses
         SET current_exercise = ?
       WHERE session_id = ?
      """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, newExercise);
            p.setInt(2, sessionId);
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error updating session: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * ends the current course session by deleting entry from table
     *
     * @param sessionId int id of session
     */
    //delete entry from table when course session ends
    public static void endCourseSession(int sessionId) {
        String selectSql = "SELECT session_id, course_id, current_exercise, started_at, total_joined " +
                "FROM active_courses WHERE session_id = ?";
        String insertSql = "INSERT INTO inactive_courses(session_id, course_id, current_exercise, started_at, total_joined) " +
                "VALUES (?, ?, ?, ?, ?)";
        String deleteSql = "DELETE FROM active_courses WHERE session_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            // fetch active record
            try (PreparedStatement sel = conn.prepareStatement(selectSql)) {
                sel.setInt(1, sessionId);
                try (ResultSet rs = sel.executeQuery()) {
                    if (rs.next()) {
                        int sid = rs.getInt("session_id");
                        int cid = rs.getInt("course_id");
                        String exercise = rs.getString("current_exercise");
                        Timestamp started = rs.getTimestamp("started_at");
                        int joined = rs.getInt("total_joined");

                        // insert into inactive_courses
                        try (PreparedStatement ins = conn.prepareStatement(insertSql)) {
                            ins.setInt(1, sid);
                            ins.setInt(2, cid);
                            ins.setString(3, exercise);
                            ins.setTimestamp(4, started);
                            ins.setInt(5, joined);
                            ins.executeUpdate();
                        }
                    }
                }
            }

            // delete from active_courses
            try (PreparedStatement del = conn.prepareStatement(deleteSql)) {
                del.setInt(1, sessionId);
                del.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: rollback and logging
            JOptionPane.showMessageDialog(null,
                    "Error ending session: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * gets map total number of registrations to each day of a course
     *
     * @param courseId int id of course
     * @return Map<String, Integer> map of registrations to day
     */
    public static Map<String, Integer> getRegistrationCounts(int courseId) {
        String sql = "SELECT DATE(registered_at) AS day, COUNT(*) AS cnt " +
                "FROM course_registrations WHERE course_id = ? " +
                "GROUP BY day ORDER BY day";
        Map<String, Integer> counts = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String day = rs.getString("day");
                    int cnt = rs.getInt("cnt");
                    counts.put(day, cnt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: use proper logging
        }
        return counts;
    }
    /**
     * gets map total number of registrations to each time of a course
     *
     * @param courseId int id of course
     * @return Map<String, Integer> map of registrations to time
     */
    public static Map<String, Integer> getSessionJoinCounts(int courseId) {
        String sql = "SELECT started_at, total_joined FROM inactive_courses " +
                "WHERE course_id = ? ORDER BY started_at";
        Map<String, Integer> counts = new LinkedHashMap<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Timestamp ts = rs.getTimestamp("started_at");
                    String key = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(ts);
                    counts.put(key, rs.getInt("total_joined"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: use proper logging
        }
        return counts;
    }
}



