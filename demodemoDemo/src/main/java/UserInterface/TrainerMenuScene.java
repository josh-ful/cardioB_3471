package UserInterface;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import UserInformation.CurrentUser;

public class TrainerMenuScene extends Scenes {

    public TrainerMenuScene(JFrame frame) {
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
                    new HostClassScene(frame);
                }
        );

        JButton viewReportsBtn = new JButton("View Reports");
        viewReportsBtn.addActionListener(e ->
                        System.out.println("Clicking view reports")
                /* TODO: open ReportsScene or dialog here */
        );

        navBar.add(createClassBtn);
        navBar.add(hostClassBtn);
        navBar.add(viewReportsBtn);

        panel.add(navBar, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
    }
}
