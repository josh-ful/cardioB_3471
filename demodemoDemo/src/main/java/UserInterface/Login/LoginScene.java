package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import UserInformation.Login;
import UserInformation.UserStorage;
import UserInformation.ValidateLRInputs;
import UserInterface.AdminDashboardScene;
import UserInterface.UserMenuScene;
import main.Main;


public class LoginScene extends LR_Scenes {
    /**
     * Constructs a LoginScene object
     *
     * @param frame which scene is created on
     */
    public LoginScene(JFrame frame) {
        super.createLR_SCENE(frame);

        panel.add(getConfirmLoginButton(frame, username, password));
        panel.add(getBackButton(frame));
    }

    /**
     * Constructs a LoginScene object
     *
     * @param frame JFrame frame which scene is created on
     * @param username JTextField field to input username
     * @param password JPasswordField field to input password
     */
    private static JButton getConfirmLoginButton(JFrame frame, JTextField username, JPasswordField password) {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(400, 30));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                // give something else the information and allow it to make the screen
                boolean success = false;
                try {
                    success = Login.loginLogic(user, pass);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel,
                            ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
                if (success && UserStorage.getTypeInt() == 0) {
                    new UserMenuScene(frame);
                    System.out.println("Loading User Dashboard");
                }
                else if (success && UserStorage.getTypeInt() == 1) {
                    //new TrainerDashboard(frame);
                    System.out.println("Loading Trainer Dashboard");
                }
                else if (success && UserStorage.getTypeInt() == 2) {
                    new AdminDashboardScene(frame);
                    System.out.println("Loading Admin Dashboard");
                }
                else {
                    new LR_Dialog(success);
                }
            }
        });
        return loginButton;
    }
}
