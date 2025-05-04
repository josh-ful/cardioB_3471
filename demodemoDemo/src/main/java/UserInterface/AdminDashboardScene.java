package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminDashboardScene extends Scenes {

    public AdminDashboardScene(JFrame frame) {
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Welcome, Admin!");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(subtitle);

        JButton addUserBtn = new JButton("Add User");
        JButton resetPassBtn = new JButton("Reset User Password");

        addUserBtn.addActionListener(e -> {
            new AddUserDialog(frame);
        });

//        resetPassBtn.addActionListener(e -> {
//            new ResetPasswordDialog(frame);
//        });

        //list title
        JLabel userListTitle = new JLabel("Registered Users");
        userListTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        userListTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(userListTitle);

        // ist of users
        JPanel userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));

        try (Connection conn = main.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT username, type FROM users ORDER BY type, username");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String type = rs.getString("type");

                JPanel userPanel = new JPanel(new BorderLayout());
                userPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                userPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JLabel userLabel = new JLabel("Username: " + username + "   |   Type: " + type);
                userLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                userPanel.add(userLabel, BorderLayout.WEST);

                userListPanel.add(userPanel);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            userListPanel.add(new JLabel("Error loading users."));
        }

        JScrollPane userScroll = new JScrollPane(userListPanel);
        userScroll.setPreferredSize(new Dimension(500, 300));
        panel.add(userScroll);




        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(addUserBtn);
        panel.add(resetPassBtn);

        frame.setContentPane(panel);
        frame.revalidate();
    }
}
