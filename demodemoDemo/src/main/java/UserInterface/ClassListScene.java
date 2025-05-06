/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts
 */

package UserInterface;

import FitnessCourse.Course;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import Controller.*;

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
        frame.setLayout(new BorderLayout());

        System.out.println(panel.getLayout().getClass().getSimpleName());


        panel.add(addTextELog());
        panel.add(addScrollClassList(frame));
        panel.add(addWorkoutButton(frame));
        panel.add(createBackButton(frame, UserMenuScene.class));

        panel.add(Box.createVerticalGlue());

        frame.add(panel, BorderLayout.CENTER);
    }
    /**
     * adds text to scene
     *
     * @return JLabel with title and font
     */
    private JLabel addTextELog() {
        JLabel exerciseText = new JLabel("Classes!");
        exerciseText.setFont(new Font("Roboto", Font.BOLD, 40));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return exerciseText;
    }
    /**
     * creates scroll pane for classes
     *
     * @return JScrollPane for classes
     */
    private JScrollPane addScrollClassList(JFrame frame) {
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        int exerciseQuantity = 0;

        try {
            // get user id
            int userId = UserController.getUserId();

            // request all registered classes
            ArrayList<Course> exerciseList = UserController.getAllUserCourses();
            exerciseQuantity = exerciseList.size();

            if (exerciseList.isEmpty()) {
                JLabel error = new JLabel("Error: Classes are empty");
                resultPanel.add(error);
            }
            for (Course course : exerciseList) {
                resultPanel.add(createCoursePanel(course, frame));
            }
        }catch (SQLException e) {
            JLabel error = new JLabel(e.getMessage());
            resultPanel.add(error);
        }

        resultPanel.setPreferredSize(new Dimension(400, exerciseQuantity*80));
        JScrollPane scroll = new JScrollPane(resultPanel);
        scroll.setPreferredSize(new Dimension(FRAME_W, 400));

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scroll;
    }

    private JPanel createCoursePanel(Course course, JFrame frame) {
        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        coursePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(course.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));//no wrapping/overlapping
        JLabel descLabel = new JLabel("<html>" + course.getDescription() + "</html>");
        descLabel.setMinimumSize(new Dimension(100, 30));
        descLabel.setPreferredSize(new Dimension(100, 30));

        textPanel.add(nameLabel);
        textPanel.add(descLabel);

        String buttonLabel = switch (course.getType()) {
            case "self" -> "Continue";
            case "group" -> "Join";
            default -> "Error";
        };

        JButton actionBtn = new JButton(buttonLabel);
        actionBtn.addActionListener(e -> {
            if(buttonLabel.equals("Continue")) {
                new  UserSelfPacedClassScene(frame, course);
            }
            else if(buttonLabel.equals("Join")) {
                System.out.println(course.getName() + " " + course.getId());
                if(UserController.isCourseJoinable(course.getId())) {
                    new UserActiveClassScene(frame, course);
                }
                else{//class is not joinable
                    JOptionPane.showMessageDialog(frame, course.getName() + " is not joinable currently");
                }
            }
            //JOptionPane.showMessageDialog(panel, buttonLabel + " " + course.getName());
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

