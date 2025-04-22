package UserInterface;

import main.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseSearch extends Scenes {
    private JComboBox<String> courseTypeCombo;
    private JTextField searchField;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;

    public CourseSearch(JFrame frame) {
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        JLabel title = new JLabel("Find a Class");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        // Filter panel
        JPanel filterPanel = new JPanel(new FlowLayout());
        courseTypeCombo = new JComboBox<>(new String[]{"self", "group"});
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");

        filterPanel.add(courseTypeCombo);
        filterPanel.add(searchField);
        filterPanel.add(searchBtn);
        panel.add(filterPanel);

        // Results panel inside scroll
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        panel.add(scrollPane);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> new ClassListScene(frame));
        panel.add(backButton);

        // Action Listeners
        searchBtn.addActionListener(e -> performSearch((String) courseTypeCombo.getSelectedItem(), searchField.getText()));

        courseTypeCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                performSearch((String) courseTypeCombo.getSelectedItem(), ""); // empty query
            }
        });

        frame.setContentPane(panel);
        frame.revalidate();

        // Run initial search
        performSearch("self", "");
    }

    private void performSearch(String type, String query) {
        resultsPanel.removeAll();  // Clear previous results

        String sql = "SELECT id, name, description FROM " +
                ("self".equals(type) ? "self_paced_courses" : "group_courses") +
                " WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseName = rs.getString("name");
                String courseDesc = rs.getString("description");

                // Each course item panel
                JPanel courseItem = new JPanel(new BorderLayout());
                courseItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                courseItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

                // Course name + description panel
                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                JLabel nameLabel = new JLabel(courseName);
                nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                //for no overlap
                JLabel descLabel = new JLabel("<html><body style='width: 400px'>" + courseDesc + "</body></html>");

                textPanel.add(nameLabel);
                textPanel.add(descLabel);

                // Register button
                JButton registerBtn = new JButton("Register");
                registerBtn.addActionListener(e -> {
                    String username = UserInformation.UserStorage.getName();
                    String courseType = (String) courseTypeCombo.getSelectedItem();  // "self" or "group"

                    try (Connection conn2 = DBConnection.getConnection()) {
                        //get id from username
                        //TODO make this something stored in UserStorage
                        PreparedStatement getUserStmt = conn2.prepareStatement("SELECT id FROM users WHERE username = ?");
                        getUserStmt.setString(1, username);
                        ResultSet userRs = getUserStmt.executeQuery();

                        if (!userRs.next()) {
                            JOptionPane.showMessageDialog(panel, "User not found in database.");
                            return;
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
                            JOptionPane.showMessageDialog(panel, "You're already registered for this class.");
                            return;
                        }

                        //finally register
                        PreparedStatement insertStmt = conn2.prepareStatement(
                                "INSERT INTO course_registrations (user_id, course_id, course_type) VALUES (?, ?, ?)"
                        );
                        insertStmt.setInt(1, userId);
                        insertStmt.setInt(2, courseId);
                        insertStmt.setString(3, courseType);
                        insertStmt.executeUpdate();
                        JOptionPane.showMessageDialog(panel, "Successfully registered for: " + courseName);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Error during registration.");
                    }
                });



                courseItem.add(textPanel, BorderLayout.CENTER);
                courseItem.add(registerBtn, BorderLayout.EAST);

                resultsPanel.add(courseItem);
            }

            resultsPanel.revalidate();
            resultsPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Database error occurred.");
        }
    }
}
