// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: Scenes.java is a parent class to
// all scenes in the CardioB App implementation
/*
 * this class is a parent class to all scenes in the CardioB App
 * implementation
 */
package UserInterface;

import Controller.Controller;
import UserInformation.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

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
    /**
     * creates a new GUI by repainting and removing the content
     * to reset to its original state
     *
     * @param frame which scene is created on
     */
    protected void createAndShowGUI(JFrame frame) {
        panel = new JPanel();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();

        frame.setJMenuBar(createMenuBar(frame));

        frame.setSize(FRAME_DIM);
        frame.setResizable(false);

        frame.setBackground(Color.BLUE);
        frame.setVisible(true);
        panelLayout();
    }
    /**
     *sets panel layout to BoxLayout along the y-axis
     *
     */
    protected void panelLayout() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    protected JButton createBackButton(JFrame frame, Class<? extends Scenes> scene) throws RuntimeException {

        JButton button = new JButton("Back");
        button.addActionListener(e -> {
            try {
                scene.getDeclaredConstructor(JFrame.class).newInstance(frame);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        });

        return button;
    }
    /**
     * creates menu bar for app
     *
     * @param frame which menu is created on
     */
    protected JMenuBar createMenuBar(JFrame frame) throws RuntimeException {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = new JMenu(Controller.getUsername());
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("dashboard");
        JMenuItem menuItem2 = new JMenuItem("profile");
        JMenuItem menuItem3 = new JMenuItem("log-out");

        menuItem.addActionListener(e -> {
            try {
                CurrentUser.controller.createDashboard(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItem2.addActionListener(e -> {
            try {
                new Profile(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItem3.addActionListener(e -> {
            Controller.destroyCurrentUser();
            try {
                new HomeScreen(frame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        menu.add(menuItem);
        menu.add(menuItem2);
        menu.add(menuItem3);

        return menuBar;
    }
}
