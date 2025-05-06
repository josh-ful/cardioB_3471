package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Controller.Controller;
import UserInformation.Login;
import UserInformation.CurrentUser;
import UserInterface.AdminDashboardScene;
import UserInterface.UserMenuScene;
import UserInterface.UserResetPasswordDialog;


public class LoginScene extends LR_Scenes {
    /**
     * Constructs a LoginScene object
     *
     * @param frame which scene is created on
     */
    public LoginScene(JFrame frame) {
        super.createLR_SCENE(frame);

        panel.add(getConfirmLoginButton(frame, username, password), BorderLayout.SOUTH);
        panel.add(getResetPasswordBtn());
        panel.add(getBackButton(frame), BorderLayout.AFTER_LAST_LINE);
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
                } catch (RuntimeException | SQLException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (success){
                    CurrentUser.controller.createDashboard(frame);
                }
            }
        });
        return loginButton;
    }

    private static JButton getResetPasswordBtn() {
        JButton btnResetPassword = new JButton("Reset Password");

        btnResetPassword.addActionListener(e->{
            // TODO change confirm dialog to inside reset dialog?
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to reset your password?");
            if (confirm == JOptionPane.YES_OPTION) {
                new UserResetPasswordDialog();
            }
        });

        return btnResetPassword;
    }
}
