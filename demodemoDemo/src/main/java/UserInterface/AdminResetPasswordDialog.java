package UserInterface;

import main.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


//TODO move the logic elsewhere
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
                JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
                return;
            }

            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE users SET password = ? WHERE username = ?"
                );
                stmt.setString(1, hashed);
                stmt.setString(2, username);
                int updated = stmt.executeUpdate();

                if (updated > 0) {
                    JOptionPane.showMessageDialog(this, "Password updated successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update password.");
            }
        });

        return confirmBtn;
    }
}
