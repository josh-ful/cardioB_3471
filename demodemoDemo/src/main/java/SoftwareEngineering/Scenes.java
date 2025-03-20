package SoftwareEngineering;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scenes {
    JTextField username = new JTextField(20);
    JPasswordField password = new JPasswordField(20);
    JButton backButton = new JButton("Back");
    JPanel panel = new JPanel();
    JFrame oldFrame = null;

    public void createAndShowGUI(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();
        frame.setSize(450, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    }

    public void addUsernamePassword() {
        panel.add(new JLabel("Username:"));
        panel.add(username);
        panel.add(new JLabel("Password:"));
        panel.add(password);
    }

    public void addBackButton(JFrame frame) {
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldFrame = frame;
                new homeScreen(frame);
            }
        });
    }
}
