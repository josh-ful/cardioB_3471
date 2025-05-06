package UserInterface.Admin;

import UserInformation.UserQuery;

import javax.swing.*;
import java.awt.*;


public class AdminResetPasswordDialog extends JDialog {
    public AdminResetPasswordDialog(JFrame parent) {
        super(parent, "Reset User Password", true);
        setLayout(new GridLayout(3, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();


        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("New Password:"));
        add(passwordField);

        add(getConfirmBtn(usernameField, passwordField));
        add(getCancelButton());

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private JButton getConfirmBtn(JTextField usernameField, JPasswordField passwordField) {
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty. \n Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            int updated = UserQuery.changePassword(username, password);
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Password was successfully changed.");
                dispose();
            }
        });

        return confirmBtn;
    }

    private JButton getCancelButton() {
        JButton cancelBtn = new JButton("Cancel");

        cancelBtn.addActionListener(e -> {
            dispose();
        });

        return cancelBtn;
    }
}
