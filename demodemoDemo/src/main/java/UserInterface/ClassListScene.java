/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts
 */

package UserInterface;

import FitnessCourse.Exercise;
import FitnessCourse.ExerciseClass;
import UserInformation.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Controller.*;
import main.DBConnection;

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
        //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.setLayout(new BorderLayout());
        System.out.println(panel.getLayout().getClass().getSimpleName());

        panel.add(addTextELog());
        panel.add(addScrollClassList());
        panel.add(addWorkoutButton(frame));
        panel.add(createBackButton(frame, UserMenuScene.class));

        panel.add(Box.createVerticalGlue());

        frame.add(panel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
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

        try {
            // get user id
            int userId = UserController.getUserId();

            // request all registered classes
            List<ExerciseClass> exerciseList = UserController.getAllExercises(userId);

            if (exerciseList.isEmpty()) {
                JLabel error = new JLabel("Error: Classes are empty");
                resultPanel.add(error);
            }
            for (ExerciseClass exerciseClass : exerciseList) {
                resultPanel.add(createCoursePanel(exerciseClass));
            }
        }catch (SQLException e) {
            JLabel error = new JLabel(e.getMessage());
            resultPanel.add(error);
        }

        JScrollPane scroll = new JScrollPane(resultPanel);
        scroll.setPreferredSize(new Dimension(600, 400));
        return scroll;
    }

    private JPanel createCoursePanel(ExerciseClass exercise) {
        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        coursePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(exercise.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));//no wrapping/overlapping
        JLabel descLabel = new JLabel("<html>" + exercise.getDescription() + "</html>");
        descLabel.setPreferredSize(new Dimension(100, 30));

        textPanel.add(nameLabel);
        textPanel.add(descLabel);

        String buttonLabel = switch (exercise.getType()) {
            case "self" -> "Continue";
            case "group" -> "Join";
            default -> "Error";
        };

        JButton actionBtn = new JButton(buttonLabel);
        actionBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel, buttonLabel + " " + exercise.getName());
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

}

