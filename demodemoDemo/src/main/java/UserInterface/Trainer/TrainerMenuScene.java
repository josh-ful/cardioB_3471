package UserInterface.Trainer;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;

import UserInformation.CurrentUser;
import UserInterface.Scenes;

public class TrainerMenuScene extends Scenes {

    public TrainerMenuScene(JFrame frame) throws SQLException {
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        //clear existing comps
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        //centered message
        JLabel greeting = new JLabel("Hello, " + CurrentUser.getName(), SwingConstants.CENTER);
        greeting.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(greeting, BorderLayout.CENTER);

        //nav bar with three buttons on the bottom
        JPanel navBar = new JPanel();
        navBar.setLayout(new GridLayout(1, 3));

        JButton createClassBtn = new JButton("View Classes");
        createClassBtn.addActionListener(e -> {
                    System.out.println("Clicking view classes");
                        new TrainerViewClassesScene(frame);
                }
        );

        JButton hostClassBtn = new JButton("Host Class");
        hostClassBtn.addActionListener(e -> {
                    System.out.println("Clicking host class");
                    try {
                        new TrainerHostClassScene(frame);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );

        JButton viewReportsBtn = new JButton("View Reports");
        viewReportsBtn.addActionListener(e -> {
                        System.out.println("Clicking view reports");
                new TrainerReportsScene(frame);
                }
        );

        navBar.add(createClassBtn);
        navBar.add(hostClassBtn);
        navBar.add(viewReportsBtn);

        panel.add(navBar, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }
}
