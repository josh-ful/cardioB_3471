package main.UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.UserInformation.*;
//import java.util.ArrayList;

public class RegisterScene extends LR_Scenes{
    public RegisterScene(JFrame frame) {
        super.createLR_SCENE(frame);

        panel.add(getConfirmRegisterButton(username, password));
        panel.add(getBackButton(frame));
    }

    private static JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(400, 30));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());

                boolean success = main.userInformation.Register.registerLogic(user, pass);
                new LR_Dialog(success);

                //TODO: What happens after this?? Back to login screen?

                // For testing only
                //ArrayList<String> loginList = new ArrayList<>(Register.logins.keySet());
                //loginList.forEach(System.out::println);
            }
        });
        return registerButton;
    }
}
