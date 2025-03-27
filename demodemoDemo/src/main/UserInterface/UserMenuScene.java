package main.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenuScene extends Scenes{
    
    public UserMenuScene(JFrame frame){
        createUM_SCENE(frame);
    }

    private void createUM_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(addProfileButton(frame));
        panel.add(addLogButton(frame));
        panel.add(addClassButton(frame));

        panel.add(addWelcomeText());
        panel.add(addPromptText());
        frame.add(panel);
    }

    private JLabel addPromptText (){
        JLabel promptText = new JLabel("Ready to get your CardioB in today?");
        promptText.setForeground(Color.BLACK);
        promptText.setAlignmentX(Component.CENTER_ALIGNMENT);

        // promptText.setAlignmentY(Component.TOP_ALIGNMENT);
        //promptText.setHorizontalAlignment(SwingConstants.CENTER);
        //promptText.setVerticalAlignment(SwingConstants.TOP);

        return promptText;
    }

    private JLabel addWelcomeText() {
        JLabel welcomeText = new JLabel("Welcome!");
        welcomeText.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        welcomeText.setForeground(Color.BLACK);
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //welcomeText.setAlignmentY(Component.TOP_ALIGNMENT);
        //welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        //welcomeText.setVerticalAlignment(SwingConstants.TOP);

        return welcomeText;
    }

    private JButton addProfileButton(JFrame frame) {
        JButton profileButton = new JButton("Profile");
        
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));
        
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileInfoScene(frame);
            }
        });
        
        return profileButton;
    }

    private JButton addLogButton(JFrame frame) {
        JButton logButton = new JButton("Log Exercise");
        
        logButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));
       
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExerciseLogScene(frame);
            }
        });
        
        return logButton;
    }

    private JButton addClassButton(JFrame frame) {
        JButton classButton = new JButton("Classes");

        classButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        classButton.setMaximumSize(new Dimension(Scenes.FRAME_W, 50));

        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassListScene(frame);
            }
        });
        
        return classButton;
    }

}