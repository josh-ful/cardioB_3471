package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LR_Scenes extends Scenes{
    JTextField username = new JTextField(20);
    JPasswordField password = new JPasswordField(20);
    JButton backButton = new JButton("Back");
    JPanel panel = new JPanel();

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


    public void createLoginScene(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);
        addUsernamePassword();
        frame.setTitle("Login");
        JButton loginButton = getConfirmLoginButton(username, password);
        panel.add(loginButton);
        addBackButton(frame);
        frame.add(panel);
    }

    public void createRegisterScene(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

    }

    public void addUsernamePassword() {
        panel.add(new JLabel("Username:"));
        panel.add(username);
        panel.add(new JLabel("Password:"));
        panel.add(password);
    }

    public void addBackButton(JFrame frame) {
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new homeScreen(frame);
            }
        });
    }


    private static JButton getConfirmLoginButton(JTextField username, JPasswordField password) {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(400, 30));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                boolean success = new Login().loginLogic(user, pass);
                LR_Dialog l_dialog = new LR_Dialog(success);
            }
        });
        return loginButton;
    }



}
