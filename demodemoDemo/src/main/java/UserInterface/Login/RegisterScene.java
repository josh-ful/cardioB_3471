package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import UserInformation.*;

public class RegisterScene extends LR_Scenes {
    Boolean utButtonStatus = false;
    /**
     * Constructs a RegisterScene object
     *
     * @param frame which scene is created on
     */

    // TODO: change utStatus name to utButtonStatus

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

    private static JTextArea getSpecificationLabel() {
        JTextArea label = new JTextArea("Username must meet the following criteria:\n" +
                " - be at least 6 characters in length\n" +
                " - start with a letter\n" +
                " - not have special characters other than \".\", \"-\", \"_\n\n" +
                "Password must meet the following criteria:\n" +
                " - have at least one digit (0-9)\n" +
                " - have at least one lowercase letter (a-z)\n" +
                " - have at least one uppercase letter (A-Z)n" +
                " - have at least one special character (@#$%^&+!)\n" +
                " - have no whitespace characters anywhere\n" +
                " - have a minimum length of 8 characters.");

        label.setEditable(false);
        return label;
    }

    //TODO make sure one of the options are selected for user type before register can occur
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

                boolean valid = true;
                try {
                    ValidateLRInputs.validateRInputs(user, pass);
                    Register.registerLogic(user, pass, utButtonStatus);
                } catch (SQLException ex) {
                    valid = false;
                    JOptionPane.showMessageDialog(
                            panel, ex.getMessage(), "Please log-in", JOptionPane.ERROR_MESSAGE);
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

        trainerButton.addActionListener(e -> utButtonStatus = trainerButton.isSelected());

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

        userButton.addActionListener(e -> utButtonStatus = userButton.isSelected());

        return userButton;
    }
}
