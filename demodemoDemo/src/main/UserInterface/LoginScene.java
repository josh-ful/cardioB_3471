package main.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.userInformation.Login;

public class LoginScene extends LR_Scenes{

    public LoginScene(JFrame frame){
        super.createLR_SCENE(frame);
        JButton loginButton = getConfirmLoginButton(frame, username, password);
        panel.add(loginButton);
        addBackButton(frame);
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
                boolean success = Login.loginLogic(user, pass);
                if (success) {
                    // update frame to reflect profile page
                    new ProfileScreen(frame);
                }
                LR_Dialog l_dialog = new LR_Dialog(success);
                boolean success = new Login().loginLogic(user, pass);
                //LR_Dialog l_dialog = new LR_Dialog(success);
                userMenuScene umS = new userMenuScene(frame);
            }
        });
        return loginButton;
    }

}
