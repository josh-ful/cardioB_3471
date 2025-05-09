package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import Exceptions.AlreadyRegisteredException;
import UserInformation.*;

public class RegisterScene extends LR_Scenes {
    Boolean utButtonStatus = false;
    Boolean trainer;
    Boolean user;
    /**
     * Constructs a RegisterScene object
     *
     * @param frame which scene is created on
     */

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
        panel.add(getSpecificationLabel());
    }

    /**
     * creates a text area with all the specifications for a new login/password
     *
     * @return JTextArea with specifications for registration
     */
    private static JTextArea getSpecificationLabel() {
        JTextArea label = new JTextArea(
                "Username must meet the following criteria:\n" +
                " - be at least 6 characters in length\n" +
                " - start with a letter\n" +
                " - not have special characters other than (.-_)\n\n" +
                "Password must meet the following criteria:\n" +
                " - be at least 8 characters in length\n" +
                " - have at least one digit (0-9)\n" +
                " - have at least one lowercase letter (a-z)\n" +
                " - have at least one uppercase letter (A-Z)\n" +
                " - have at least one special character (@#$%^&+!)\n" +
                " - have no whitespace characters anywhere");

        label.setEditable(false);
        return label;
    }

    /**
     * gets button with functionality confirming registration is valid
     *
     * @param username textfield
     * @param password textfield
     * @return button confirming registration validity
     */
    private JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(400, 30));

        registerButton.addActionListener(e -> {
            if (!utButtonStatus) {
                JOptionPane.showMessageDialog(null,
                        "Please select user type", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String user = username.getText();
                String pass = new String(password.getPassword());

                boolean valid;
                try {
                    ValidateLRInputs.validateRInputs(user, pass);
                    String type = "general";
                    if(trainer) {
                        type = "trainer";
                    }
                    valid = Register.registerLogic(user, pass, type);
                }
                catch (SQLException ex) {
                    valid = false;
                    JOptionPane.showMessageDialog(
                            panel, ex.getMessage(), "Please log-in", JOptionPane.ERROR_MESSAGE);
                }
                catch (IllegalArgumentException ex) {
                    valid = false;
                    JOptionPane.showMessageDialog(panel,
                            ex.getMessage(), "REGISTER ERROR", JOptionPane.ERROR_MESSAGE);
                }
                catch (AlreadyRegisteredException ex) {
                    valid = false;
                    JOptionPane.showMessageDialog(panel, "Please log in to existing account", ex.getMessage(), JOptionPane.PLAIN_MESSAGE);
                }

                if (valid) {
                    new OnboardingDialog(false);
                    JOptionPane.showMessageDialog(null,
                            "Congrats! You have been registered!",
                            "Registration Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        return registerButton;
    }
    /**
     * gets button that gets trainer
     *
     * @return RadioButton that selects trainer
     */
    private JRadioButton getSelectTrainerButton() {
        JRadioButton trainerButton = new JRadioButton("Trainer");
        trainerButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        trainerButton.addActionListener(e -> {
                    utButtonStatus = trainerButton.isSelected();
                    trainer = trainerButton.isSelected();
                    user = !trainer;
                });


        return trainerButton;
    }
    /**
     * gets button that selects user
     *
     * @return RadioButton that selects user
     */
    private JRadioButton getSelectUserButton() {
        JRadioButton userButton = new JRadioButton("User");
        userButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        userButton.addActionListener(e -> {
            utButtonStatus = userButton.isSelected();
            user = userButton.isSelected();
            trainer = !user;
        });

        return userButton;
    }
}
