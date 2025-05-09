package UserInterface;

import Controller.UserController;
import FitnessCourse.*;
import UserInformation.CurrentUser;
import main.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * this class represents a CourseSearch object
 * containing information about CourseSearches
 */
public class CourseSearch extends Scenes {
    private JComboBox<String> courseTypeCombo;
    private JTextField searchField;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    /**
     * constructs a CourseSearch object
     *
     * @param frame JFrame which CourseSearch is displayed
     */
    public CourseSearch(JFrame frame) throws SQLException {
        createAndShowGUI(frame);
    }
    /**
     * creates and displays gui on frame
     *
     * @param frame JFrame which gui is displayed
     */
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

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        panel.add(scrollPane);

        // Back button
        panel.add(createBackButton(frame, ClassListScene.class));

        // Action Listeners
        searchBtn.addActionListener(e ->
            performSearch((String) courseTypeCombo.getSelectedItem(), searchField.getText()));
            courseTypeCombo.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    performSearch((String) courseTypeCombo.getSelectedItem(), ""); // empty query
                }
        });

        frame.setContentPane(panel);
        performSearch("self", "");
    }

    //todo change logic so perform search doesn't happen here?
    //todo check if you can register for a class before searching?
    private void performSearch(String type, String query) {
        resultsPanel.removeAll();  // Clear previous results

        try {
            ArrayList<Course> classes;
            classes = UserController.getAllCourses(type, query);

            for (Course exerciseClass : classes) {
                JPanel courseItem = getCoursePanel();
                JPanel textPanel = getTextPanel(exerciseClass);
                JButton registerBtn = getRegisterButton(exerciseClass);

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
    /**
     * adds action listener to register button to register for class
     *
     * @param course course to be registered for
     */
    private static JButton getRegisterButton(Course course) {
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> {
            try {
                if (UserController.registerForClass(course.getId())) {
                    JOptionPane.showMessageDialog(panel, "Successfully registered for: " +
                            course.getName());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error with database during registration.");
                throw new RuntimeException(ex);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });
        return registerBtn;
    }
    /**
     * gets course panel
     *
     * @return JPanel which the course is displayed
     */
    private static JPanel getCoursePanel() {
        JPanel courseItem = new JPanel(new BorderLayout());
        courseItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        courseItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        return courseItem;
    }
    /**
     * gets text panel from class
     *
     * @param exerciseClass Course to get text from
     */
    private static JPanel getTextPanel(Course exerciseClass) {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(exerciseClass.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        //for no overlap
        JLabel descLabel = new JLabel("<html><body style='width: 400px'>" + exerciseClass.getDescription() + "</body></html>");
        descLabel.setPreferredSize(new Dimension(100, 30));

        textPanel.add(nameLabel);
        textPanel.add(descLabel);
        return textPanel;
    }
}
