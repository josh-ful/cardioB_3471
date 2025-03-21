package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Component.LEFT_ALIGNMENT;

public class LR_Scenes extends Scenes{
    JTextField username = new JTextField(20);
    JPasswordField password = new JPasswordField(20);
    JButton backButton = new JButton("Back");
    JPanel panel = new JPanel();

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


    public void createLR_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);
        addUsernamePassword();
        frame.add(panel);
    }

    public void addUsernamePassword() {

        JLabel u = new JLabel("Username: ");
        JLabel p = new JLabel("Password: ");

        panel.add(u);
        panel.add(username);
        panel.add(p);
        panel.add(password);
    }

    public void addBackButton(JFrame frame) {
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(frame.getWidth(), 50));
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen(frame);
            }
        });
    }







}
