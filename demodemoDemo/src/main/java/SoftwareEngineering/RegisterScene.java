package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static SoftwareEngineering.Register.registerLogic;

public class RegisterScene extends Scenes{
    public RegisterScene(JFrame frame) {
        createAndShowGUI(frame);
    }

    public void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        //JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Register ™");
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED); // Set background color

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        JTextField username = new JTextField(20);
        JPasswordField password = new JPasswordField(20);

        JButton registerButton = getConfirmRegisterButton(username, password);

        panel.add(username);
        panel.add(password);
        panel.add(registerButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static JButton getConfirmRegisterButton(JTextField username, JPasswordField password) {
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                boolean success = registerLogic(user, pass);
                LR_Dialog r_dialog = new LR_Dialog(success);
            }
        });
        return registerButton;
    }
}
