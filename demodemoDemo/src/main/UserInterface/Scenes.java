package main.UserInterface;

import javax.swing.*;
import java.awt.*;


public class Scenes {
    JPanel panel = new JPanel();

    public static final Dimension FRAME_DIM = new Dimension(450, 800);
    public static final int FRAME_W = FRAME_DIM.width;
    public static final int FRAME_H = FRAME_DIM.height;

    public void createAndShowGUI(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();

        frame.setSize(FRAME_DIM);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panelLayout();
    }

    private void panelLayout() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    }
}
