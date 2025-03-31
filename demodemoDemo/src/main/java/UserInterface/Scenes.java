package UserInterface;

import javax.swing.*;


public class Scenes {
    JPanel panel = new JPanel();


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
}
