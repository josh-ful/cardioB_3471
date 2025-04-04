package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import UserInterface.*;

public class LR_Scenes extends Scenes {
    protected JTextField username;
    protected JPasswordField password;
//    protected String user;
//    protected String pass;

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

    private JLabel getUsernameLabel(){
        return new JLabel("Username: ");
    }

    private JLabel getPasswordLabel(){
        return new JLabel("Password: ");
    }

    public JButton getBackButton(JFrame frame) {
        JButton backButton = createBackButton(frame, HomeScreen.class);

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

        return backButton;
    }








}
