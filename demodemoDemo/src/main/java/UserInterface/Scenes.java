// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: Scenes.java is a parent class to
// all scenes in the CardioB App implementation
/*
 * this class is a parent class to all scenes in the CardioB App
 * implementation
 */
package UserInterface;

import main.Main;

import javax.swing.*;
import java.awt.*;

public class Scenes {
    protected static JPanel panel;

    /*
     * description: creates blank GUI to be overridden
     * by other scene classes
     * return: void
     * precondition: JFrame is passed in some condition
     * postcondition: JFrame is blank screen and panel
     * layout is set to BoxLayout
     */
    public static final Dimension FRAME_DIM = new Dimension(450, 800);
    public static final int FRAME_W = FRAME_DIM.width;
    public static final int FRAME_H = FRAME_DIM.height;

    protected void createAndShowGUI(JFrame frame) {
        panel = new JPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
}
