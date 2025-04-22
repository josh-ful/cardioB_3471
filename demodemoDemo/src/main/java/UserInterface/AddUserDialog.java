package UserInterface;

import main.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddUserDialog extends JDialog {
    public AddUserDialog(JFrame parent) {
        super(parent, "Add New User", true);
        setLayout(new GridLayout(4, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"general", "trainer", "admin"});
        JButton confirmBtn = new JButton("Confirm");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("User Type:"));
        add(roleBox);
        add(confirmBtn);

        confirmBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String role = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
                return;
            }

            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO users (username, password, type) VALUES (?, ?, ?)"
                );
                stmt.setString(1, username);
                stmt.setString(2, hashed);
                stmt.setString(3, role);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "User added successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to add user. Username may already exist.");
            }
        });

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
