package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

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
        try {
            super.createAndShowGUI(frame);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //Panels for layout
        JPanel usernamePanel = new JPanel();
        usernamePanel.setSize(new Dimension(600, 300));
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.PAGE_AXIS));
        usernamePanel.setBorder(BorderFactory.createTitledBorder("Username"));

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.PAGE_AXIS));
        passwordPanel.setBorder(BorderFactory.createTitledBorder("Password"));

        username = new JTextField(20);
        username.setPreferredSize(new Dimension(300, 30));
        username.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        password = new JPasswordField(20);
        password.setPreferredSize(new Dimension(300, 30));
        password.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));


        usernamePanel.add(username);

        usernamePanel.add(username);
        passwordPanel.add(password);

        JPanel spacer = new JPanel();
        spacer.setLayout(new BoxLayout(spacer, BoxLayout.PAGE_AXIS));
        spacer.add(Box.createVerticalStrut(200));
        panel.add(spacer);

        panel.add(usernamePanel);
        panel.add(passwordPanel);

        /*
        panel.add(getUsernameLabel(), BorderLayout.CENTER);
        panel.add(username, BorderLayout.AFTER_LAST_LINE);
        panel.add(getPasswordLabel(), BorderLayout.CENTER);
        panel.add(password, BorderLayout.AFTER_LAST_LINE);
*/

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
     * @param frame which scene is created on
     * @return button with back button functionality
     */
    public JButton getBackButton(JFrame frame) {
        JButton backButton = createBackButton(frame, HomeScreen.class);

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

        return backButton;
    }








}
