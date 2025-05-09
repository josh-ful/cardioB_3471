package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import UserInformation.Login;
import UserInformation.CurrentUser;
import UserInterface.UserResetPasswordDialog;


public class LoginScene extends LR_Scenes {
    /**
     * Constructs a LoginScene object
     *
     * @param frame which scene is created on
     */
    public LoginScene(JFrame frame) {
        super.createLR_SCENE(frame);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(getConfirmLoginButton(frame, username, password));
        loginPanel.add(Box.createVerticalStrut(10));

        loginPanel.add(getResetPasswordBtn());
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(getBackButton(frame));

        panel.add(loginPanel);

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
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
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
                    try {
                        CurrentUser.controller.createDashboard(frame);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        return loginButton;
    }

    private static JButton getResetPasswordBtn() {
        JButton btnResetPassword = new JButton("Reset Password");
        btnResetPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnResetPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnResetPassword.setMaximumSize(new Dimension(400, 30));

        btnResetPassword.addActionListener(e->{
            new UserResetPasswordDialog();
        });

        return btnResetPassword;
    }
}
