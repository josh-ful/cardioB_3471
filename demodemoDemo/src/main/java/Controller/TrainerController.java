package Controller;
/*
* this class serves as the trainer user type controller
*/
import javax.swing.*;
import FitnessCourse.Course;
import UserInterface.*;
import UserInformation.CurrentUser;
import main.DBConnection;
import UserInterface.TrainerMenuScene;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerController implements Controller {

    public void createDashboard(JFrame frame) {
        new UserInterface.TrainerMenuScene(frame);
    }

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

            JOptionPane.showMessageDialog(null, "Class created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating class: " + e.getMessage());
        }
    }

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

            JOptionPane.showMessageDialog(null, "Class edited successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error editing class: " + e.getMessage());
        }
    }


    //TODO add a host class functionality
        //should set class state to joinable
        //trainer should be able to select an exercise
        //currently selected exercise will be displayed to users with a timer
        //add a visible clock
        //hook duration up on user side with exercise log

    //TODO add a remove class functionality



}



