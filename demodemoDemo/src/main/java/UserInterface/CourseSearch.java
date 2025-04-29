package UserInterface;

import Controller.UserController;
import FitnessCourse.ExerciseClass;
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

        //todo change panelLayout(JPanel)
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
//        frame.revalidate();
//        frame.repaint();

        // updates resultsPanel
        performSearch("self", "");
    }

    private void performSearch(String type, String query) {
        resultsPanel.removeAll();  // Clear previous results

        try {
            ArrayList<ExerciseClass> classes;
            classes = UserController.getAllExercises(type, query);

            for (ExerciseClass exerciseClass : classes) {

                // Each course item panel
                JPanel courseItem = getCoursePanel();

                // Course name + description panel
                JPanel textPanel = getTextPanel(exerciseClass);

                // Register button
                JButton registerBtn = new JButton("Register");
                registerBtn.addActionListener(e -> {
                    try {
                        UserController.addCourseRegistration((String) courseTypeCombo.getSelectedItem(), exerciseClass.getId(), exerciseClass.getName());
                        JOptionPane.showMessageDialog(panel, "Successfully registered for: " + exerciseClass.getName());
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage());
                    } catch (SQLException ex) {
                        //todo change to SQLException?
                        throw new RuntimeException(ex);
                    }

//                    String courseType = (String) courseTypeCombo.getSelectedItem();  // "self" or "group"
//
//                    try {
//                        //get id from username
//                        //TODO make this something stored in UserStorage
//                        int userId = UserController.getUserId();
//                        if (userId == 0) {
//                            JOptionPane.showMessageDialog(panel, "User not found in database.");
//                            return;
//                        }
//                        //if not already registered, then register
//                        if (!UserController.isRegistered(userId, exerciseClass.getId())) {
//                            UserController.registerForClass(exerciseClass.getId());
//                            JOptionPane.showMessageDialog(panel, "Successfully registered for: " + exerciseClass.getName());
//                        } else {
//                            JOptionPane.showMessageDialog(panel, "You're already registered for this class.");
//                        }
//
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                        JOptionPane.showMessageDialog(panel, "Error during registration.");
//                    }
                });


            courseItem.add(textPanel, BorderLayout.CENTER);
            courseItem.add(registerBtn, BorderLayout.EAST);
            resultsPanel.add(courseItem);
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
       // panelLayout();
            // huh?

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Database error occurred.");
        }
    }

    private static JPanel getCoursePanel() {
        JPanel courseItem = new JPanel(new BorderLayout());
        courseItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        courseItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        return courseItem;
    }

    private static JPanel getTextPanel(ExerciseClass exerciseClass) {
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
