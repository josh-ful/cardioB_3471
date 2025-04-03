package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import UserInformation.*;
import UserInterface.HomeScreen;

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

    //TODO make sure one of the options are selected for user type before register can occur

    private JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(400, 30));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (utStatus == null) {
                    JOptionPane.showMessageDialog(null,
                            "Please select user type", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String user = username.getText();
                    String pass = new String(password.getPassword());

                    boolean valid = true;
                    try {
                        ValidateLRInputs.validateRInputs(user, pass);
                        Register.registerLogic(user, pass, utStatus);
                    } catch (SQLException ex) {
                        valid = false;
                        JOptionPane.showMessageDialog(
                                panel,
                                ex.getMessage(),
                                "Please log-in",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        valid = false;
                        JOptionPane.showMessageDialog(panel,
                                ex.getMessage(), "REGISTER ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    if (valid) {
                        JOptionPane.showMessageDialog(
                                panel,
                                "Congrats! You have been registered! \n" +
                                        "Return to main menu and login!",
                                "Registration Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });


        return registerButton;
    }

    private JRadioButton getSelectTrainerButton() {
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

    private JRadioButton getSelectUserButton() {
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
