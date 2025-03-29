package main.UserInterface.Login;

import main.UserInterface.Scenes;
import main.UserInterface.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

<<<<<<< Updated upstream
public class LR_Scenes extends Scenes {
    JTextField username = new JTextField(20);
    JPasswordField password = new JPasswordField(20);
    JButton backButton = new JButton("Back");
    JPanel panel = new JPanel();

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


=======
import main.UserInterface.*;
/**
 * this class is an extension of Scenes that creates a
 * scene with login functionality
 */
public class LR_Scenes extends Scenes {
    protected JTextField username;
    protected JPasswordField password;
    /**
     *
     *
     * @param frame
     */
>>>>>>> Stashed changes
    public void createLR_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);
        addUsernamePassword();
        frame.add(panel);
    }
<<<<<<< Updated upstream

    public void addUsernamePassword() {

        JLabel u = new JLabel("Username: ");
        JLabel p = new JLabel("Password: ");

        panel.add(u);
        panel.add(username);
        panel.add(p);
        panel.add(password);
    }

    public void addBackButton(JFrame frame) {
=======
    /**
     * returns Username label
     */
    private JLabel getUsernameLabel(){
        return new JLabel("Username: ");
    }
    /**
     * returns Password label
     */
    private JLabel getPasswordLabel(){
        return new JLabel("Password: ");
    }
    /**
     *returns back button with back button functionality
     *
     * @param frame
     * @return JButton back button
     */
    public JButton getBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");

>>>>>>> Stashed changes
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen(frame);
            }
        });
    }
}
