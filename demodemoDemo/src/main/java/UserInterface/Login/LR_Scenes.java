package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import UserInterface.*;

public class LR_Scenes extends Scenes {
    protected JTextField username;
    protected JPasswordField password;
//    protected String user;
//    protected String pass;
    /**
     * Constructs a RegisterScene object
     *
     */
    public void createLR_SCENE() {
        super.createAndShowGUI();

        username = new JTextField(20);
        password = new JPasswordField(20);

        panel.add(getUsernameLabel(), BorderLayout.CENTER);
        panel.add(username, BorderLayout.AFTER_LAST_LINE);
        panel.add(getPasswordLabel(), BorderLayout.CENTER);
        panel.add(password, BorderLayout.AFTER_LAST_LINE);


        frame.add(panel, BorderLayout.SOUTH);
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
     * @return button with back button functionality
     */
    public JButton getBackButton() {
        JButton backButton = createBackButton(HomeScreen.class);

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

        return backButton;
    }








}
