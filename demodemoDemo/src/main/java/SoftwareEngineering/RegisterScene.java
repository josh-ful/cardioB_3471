package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterScene extends LR_Scenes{
    public RegisterScene(JFrame frame) {
        super.createLR_SCENE(frame);
        JButton registerButton = getConfirmRegisterButton(username, password);
        panel.add(registerButton);
        addBackButton(frame);
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
                boolean success = Register.registerLogic(user, pass);
                LR_Dialog r_dialog = new LR_Dialog(success);

                // For testing only
                //ArrayList<String> loginList = new ArrayList<>(Register.logins.keySet());
                //loginList.stream().forEach(System.out::println);
            }
        });
        return registerButton;
    }
}
