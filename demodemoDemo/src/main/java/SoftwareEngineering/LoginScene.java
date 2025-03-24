package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScene extends LR_Scenes{

    public LoginScene(JFrame frame){
        super.createLR_SCENE(frame);
        JButton loginButton = getConfirmLoginButton(username, password, frame);
        panel.add(loginButton);
        addBackButton(frame);
    }



    private static JButton getConfirmLoginButton(JTextField username, JPasswordField password, JFrame frame) {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(400, 30));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                boolean success = new Login().loginLogic(user, pass);
                LR_Dialog l_dialog = new LR_Dialog(success);
                userMenuScene umS = new userMenuScene(frame);
            }
        });
        return loginButton;
    }

}
