/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts
 */

package UserInterface;

import UserInformation.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ClassListScene extends Scenes{
    //Replacing
    //JTextArea textArea = new JTextArea(10, 30);
    /**
     * Constructs a ClassListScene object
     *
     * @param frame which scene is created on
     */
    public ClassListScene(JFrame frame){
        createAndShowGUI(frame);
    }
    /**
     * creates a ClassListScene using the super's createAndShowGUI
     * method and adds on a menu and text
     *
     * @param frame which scene is created on
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        panel.add(addTextELog());
        panel.add(addScrollClassList());
        panel.add(addWorkoutButton(frame));
        panel.add(addBackButton(frame));

        frame.add(panel);
    }
    /**
     * adds text to scene
     *
     * @return JLabel with title and font
     */
    private JLabel addTextELog() {
        JLabel exerciseText = new JLabel("Classes!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exerciseText;
    }
    /**
     * creates scroll pane for classes
     *
     * @return JScrollPane for classes
     */
    private JScrollPane addScrollClassList() {
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        String username = CurrentUser.getName();

        try (Connection conn = main.DBConnection.getConnection()) {
            //get id
            //TODO add to UserStorage
            PreparedStatement userStmt = conn.prepareStatement("SELECT id FROM users WHERE username = ?");
            userStmt.setString(1, username);
            ResultSet userRs = userStmt.executeQuery();

            if (!userRs.next()) {
                JLabel error = new JLabel("User not found.");
                resultPanel.add(error);
                return new JScrollPane(resultPanel);
            }

            int userId = userRs.getInt("id");

            // request all registered classes
            PreparedStatement regStmt = conn.prepareStatement(
                    "SELECT course_id, course_type FROM course_registrations WHERE user_id = ?"
            );
            regStmt.setInt(1, userId);
            ResultSet regRs = regStmt.executeQuery();

            while (regRs.next()) {
                int courseId = regRs.getInt("course_id");
                String courseType = regRs.getString("course_type");

                if (courseType.equals("self")) {
                    PreparedStatement selfStmt = conn.prepareStatement(
                            "SELECT name, description FROM self_paced_courses WHERE id = ?"
                    );
                    selfStmt.setInt(1, courseId);
                    ResultSet rs = selfStmt.executeQuery();
                    if (rs.next()) {
                        String name = rs.getString("name");
                        String desc = rs.getString("description");
                        resultPanel.add(createCoursePanel(name, desc, "Continue"));
                    }
                } else if (courseType.equals("group")) {
                    PreparedStatement groupStmt = conn.prepareStatement(
                            "SELECT name, description, schedule FROM group_courses WHERE id = ?"
                    );
                    groupStmt.setInt(1, courseId);
                    ResultSet rs = groupStmt.executeQuery();
                    if (rs.next()) {
                        String name = rs.getString("name");
                        String desc = rs.getString("description");
                        String sched = rs.getString("schedule");
                        String fullDesc = desc + " | Meeting Time: " + sched;
                        resultPanel.add(createCoursePanel(name, fullDesc, "Join"));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JLabel error = new JLabel("Error loading your registered classes.");
            resultPanel.add(error);
        }

        JScrollPane scroll = new JScrollPane(resultPanel);
        scroll.setPreferredSize(new Dimension(600, 400));
        return scroll;
    }

    private JPanel createCoursePanel(String name, String desc, String buttonLabel) {
        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        coursePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));//no wrapping/overlapping
        JLabel descLabel = new JLabel("<html><body style='width: 400px'>" + desc + "</body></html>");

        textPanel.add(nameLabel);
        textPanel.add(descLabel);

        JButton actionBtn = new JButton(buttonLabel);
        actionBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, buttonLabel + " " + name);
        });

        coursePanel.add(textPanel, BorderLayout.CENTER);
        coursePanel.add(actionBtn, BorderLayout.EAST);

        return coursePanel;
    }


    /**
     * create a button leading to add workout dialog page
     *
     * @return JButton to create add workout dialog scene
     */
    private JButton addWorkoutButton(JFrame frame) {
        JButton button = new JButton("Register for New Class");
        button.addActionListener(e -> {
            new CourseSearch(frame);
        });
        return button;
    }

    /**
     * adds button leading to previous scene
     *
     * @param frame which scene is created on
     * @return button with back button functionality
     */
    private JButton addBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new UserMenuScene(frame);
        });

        return backButton;
    }

}

