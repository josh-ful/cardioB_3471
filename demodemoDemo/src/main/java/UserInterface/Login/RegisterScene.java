package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UserInformation.*;
//import java.util.ArrayList;

public class RegisterScene extends LR_Scenes {
    Boolean utStatus = null;

    public RegisterScene(JFrame frame) {
        super.createLR_SCENE(frame);

        JRadioButton trainerButton = getSelectTrainerButton();
        JRadioButton userButton = getSelectUserButton();

        ButtonGroup group = new ButtonGroup();
        group.add(userButton);
        group.add(trainerButton);

        panel.add(userButton);
        panel.add(trainerButton);

        panel.add(getConfirmRegisterButton(username, password));
        panel.add(getBackButton(frame));
    }

    private JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(400, 30));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());

                boolean success = Register.registerLogic(user, pass, utStatus);
                new LR_Dialog(success);

                //TODO: What happens after this?? Back to login screen?

                // For testing only
                //ArrayList<String> loginList = new ArrayList<>(Register.logins.keySet());
                //loginList.forEach(System.out::println);
            }
        });

        return registerButton;
    }

    private JRadioButton getSelectTrainerButton(){
        JRadioButton trainerButton = new JRadioButton("Trainer");
        trainerButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        trainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utStatus = trainerButton.isSelected();
            }
        });

        return trainerButton;
    }

    private JRadioButton getSelectUserButton(){
        JRadioButton userButton = new JRadioButton("User");
        userButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utStatus = !(userButton.isSelected());
            }
        });

        return userButton;
    }
}
