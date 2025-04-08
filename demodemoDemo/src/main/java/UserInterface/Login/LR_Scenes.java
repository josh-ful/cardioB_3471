package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserInformation.ValidateLRInputs;
import UserInterface.*;

public class LR_Scenes extends Scenes {
    protected JTextField username;
    protected JPasswordField password;
//    protected String user;
//    protected String pass;
    /**
     * Constructs a RegisterScene object
     *
     * @param frame which scene is created on
     */
    public void createLR_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);

        username = new JTextField(20);
        password = new JPasswordField(20);

        panel.add(getUsernameLabel());
        panel.add(username);
        panel.add(getPasswordLabel());
        panel.add(password);

        frame.add(panel);
    }
    /**
     * gets label titled username
     *
     * @return JLabel with title username
     */
    private JLabel getUsernameLabel(){
        return new JLabel("Username: ");
    }
    /**
     * gets label titled password
     *
     * @return JLabel with title password
     */
    private JLabel getPasswordLabel(){
        return new JLabel("Password: ");
    }
    /**
     * adds button leading to previous scene
     *
     * @param frame which scene is created on
     * @return button with back button functionality
     */
    public JButton getBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen(frame);
            }
        });

        return backButton;
    }








}
