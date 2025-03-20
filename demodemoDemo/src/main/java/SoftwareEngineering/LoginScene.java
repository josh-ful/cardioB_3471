package SoftwareEngineering;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static SoftwareEngineering.Login.loginLogic;

public class LoginScene extends Scenes{

    public LoginScene(JFrame frame){
        createAndShowGUI(frame);
    }

    public void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        super.addUsernamePassword();


        frame.setTitle("Login");
        JButton loginButton = getConfirmLoginButton(username, password);
        panel.add(loginButton);
        super.addBackButton(frame);

        frame.add(panel);
    }

    private static JButton getConfirmLoginButton(JTextField username, JPasswordField password) {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                boolean success = loginLogic(user, pass);
                LR_Dialog l_dialog = new LR_Dialog(success);
            }
        });
        return loginButton;
    }

}
