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
     * @param frame which scene is created on
     */
    public void createLR_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);

        username = new JTextField(20);
        password = new JPasswordField(20);
    }
    public JButton getBackButton(JFrame frame) {
        JButton backButton = createBackButton(frame, HomeScreen.class);

        return backButton;
    }

}
