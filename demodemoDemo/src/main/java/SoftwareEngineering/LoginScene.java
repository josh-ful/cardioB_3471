package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScene extends Scenes{

    public LoginScene(JFrame frame){
        createAndShowGUI(frame);
    }

    public void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        //JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED); // Set background color

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        JTextField username = new JTextField(20);
        JPasswordField password = new JPasswordField(20);

        JButton loginButton = getConfirmLoginButton(username, password);

        panel.add(username);
        panel.add(password);
        panel.add(loginButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static JButton getConfirmLoginButton(JTextField username, JPasswordField password) {
        JButton loginButton = new JButton("Login");
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
