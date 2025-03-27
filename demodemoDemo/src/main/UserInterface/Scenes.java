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
