/**
 * this class creates a scene with a list of workouts
 * and a way for a user to log more workouts
 */

package main.UserInterface;

import javax.swing.*;
import java.awt.*;
<<<<<<< Updated upstream
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

=======
/**
 * this class is an extension of Scenes.java that creates a
 * scene with a list of classes
 */
>>>>>>> Stashed changes
public class ClassListScene extends Scenes{
    JButton addWorkoutButton = new JButton("Add Workout!");
    JPanel panel = new JPanel();
    JTextArea textArea = new JTextArea(10, 30);
<<<<<<< Updated upstream
    JScrollPane scrollPane = new JScrollPane(textArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
=======
    /**
     * Constructs a new 'ClassListScene' with the specified frame
     *
     * @param frame
     */
>>>>>>> Stashed changes
    public ClassListScene(JFrame frame){
        createCL_SCENE(frame);
        addScrollClassList(frame);
    }
<<<<<<< Updated upstream

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


=======
    /**
     *
     *
     * @param frame
     */
>>>>>>> Stashed changes
    public void createCL_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        addTextELog();

        frame.add(panel);
    }
<<<<<<< Updated upstream

    public void addTextELog() {
=======
    /**
     *
     *
     * @return JLabel
     */
    private JLabel addTextELog() {
>>>>>>> Stashed changes
        JLabel exerciseText = new JLabel("Classes!");
        exerciseText.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        exerciseText.setForeground(Color.BLACK);
        exerciseText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(exerciseText);
    }
<<<<<<< Updated upstream

    public void addScrollClassList(JFrame frame) {
=======
    /**
     *
     *
     * @return classes scroll pane
     */
    private JScrollPane addScrollClassList() {
        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

>>>>>>> Stashed changes
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(scrollPane);
    }
<<<<<<< Updated upstream
=======
    /**
     *
     *
     * @return JButton
     */
    private JButton addWorkoutButton() {
       return new JButton("Add Workout!");
    }

>>>>>>> Stashed changes
}

