package main.UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.UserInformation.*;
//import java.util.ArrayList;
<<<<<<< Updated upstream

public class RegisterScene extends LR_Scenes{
=======
/**
 * this class is an extension of Scenes that creates a
 * Scene with registration functionality
 */
public class RegisterScene extends LR_Scenes {
    Boolean utStatus = null;
    /**
     * Constructs a new 'RegisterScene' with the specified frame
     *
     * @param frame
     */
>>>>>>> Stashed changes
    public RegisterScene(JFrame frame) {
        super.createLR_SCENE(frame);
        JButton registerButton = getConfirmRegisterButton(username, password);
        panel.add(registerButton);
        addBackButton(frame);
    }
<<<<<<< Updated upstream

    private static JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
=======
    /**
     * creates a register button to confirm valid information
     *
     * @param username
     * @param password
     * @return JButton confirm register button
     */
    private JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
    /**
     *
     *
     * @return JRadioButton
     */
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
>>>>>>> Stashed changes
}
