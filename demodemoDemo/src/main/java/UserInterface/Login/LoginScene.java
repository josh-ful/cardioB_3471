package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserInformation.Login;
import UserInformation.ValidateLRInputs;
import UserInterface.UserMenuScene;
import main.Main;

public class LoginScene extends LR_Scenes {

    public LoginScene(JFrame frame){
        super.createLR_SCENE(frame);

        panel.add(getConfirmLoginButton(frame, username, password));
        panel.add(getBackButton(frame));
    }

    private static JButton getConfirmLoginButton(JFrame frame, JTextField username, JPasswordField password) {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(400, 30));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());

                boolean valid = true;
                if (!ValidateLRInputs.usernameFormatValidation(user)){
                    JOptionPane.showMessageDialog(
                            panel,
                            "Sorry, \"" + user + "\" "
                                    + "isn't a valid username.\n",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    username.setText("");
                    valid = false;
                }
                if (!ValidateLRInputs.passwordFormatValidation(pass)){
                    JOptionPane.showMessageDialog(
                            panel,
                            "Sorry, \"" + pass + "\" "
                                    + "isn't a valid password.\n",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                    password.setText("");
                    valid = false;
                }

                if (valid){
                    boolean success = Login.loginLogic(user, pass);
                    LR_Dialog l_dialog = new LR_Dialog(success);

                    if (success) {
                        UserMenuScene umS = new UserMenuScene(frame);
                    }
                }
            }
        });

        return loginButton;
    }

}
