package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterScene extends Scenes{
    public RegisterScene(JFrame frame) {
        createAndShowGUI(frame);
    }

    public void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        super.addUsernamePassword();

        frame.setTitle("Register ™");
        JButton registerButton = getConfirmRegisterButton(username, password);
        panel.add(registerButton);
        super.addBackButton(frame);


        frame.add(panel);
    }

    private static JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                boolean success = new Register().registerLogic(user, pass);
                LR_Dialog r_dialog = new LR_Dialog(success);
            }
        });
        return registerButton;
    }
}
