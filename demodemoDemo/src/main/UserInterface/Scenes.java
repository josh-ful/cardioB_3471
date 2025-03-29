// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: Scenes.java is a parent class to
// all scenes in the CardioB App implementation
/**
 * this class is a parent class to all scenes in the CardioB App
 * implementation
 */
package main.UserInterface;

import javax.swing.*;
<<<<<<< Updated upstream

=======
import java.awt.*;
/**
 * this class defines a simple 'Scene' with a panel and
 * specified dimensions
 */
>>>>>>> Stashed changes
public class Scenes {
    JPanel panel = new JPanel();

    /*
     * description: creates blank GUI to be overridden
     * by other scene classes
     * return: void
     * precondition: JFrame is passed in some condition
     * postcondition: JFrame is blank screen and panel
     * layout is set to BoxLayout
     */
<<<<<<< Updated upstream
=======
    public static final Dimension FRAME_DIM = new Dimension(450, 800);
    public static final int FRAME_W = FRAME_DIM.width;
    public static final int FRAME_H = FRAME_DIM.height;
    /**
     *
     *
     * @param frame
     */
>>>>>>> Stashed changes
    public void createAndShowGUI(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();
        frame.setSize(450, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
<<<<<<< Updated upstream
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
=======

        panelLayout();
    }
    /**
     * sets panel layout to BoxLayout
     */
    private void panelLayout() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
>>>>>>> Stashed changes
    }
}
