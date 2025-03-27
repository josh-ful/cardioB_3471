package main.UserInterface;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


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
        panel.add(initMenu());
    }
    private JMenuBar initMenu() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem header, menuRemove;

        menuBar = new JMenuBar();

        ImageIcon icon = new ImageIcon("src/main/resources/menuIcon.png");
        Image image = icon.getImage();
        int newWidth = 50; // Desired width
        int newHeight = 50; // Desired height
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        icon = new ImageIcon(scaledImage);

        menu = new JMenu();
        menu.setIcon(icon);
        header = new JMenuItem("Options:");
        header.setEnabled(false);

        menuRemove = new JMenuItem("Profile");
        menuRemove.addActionListener(null);
        menu.addMouseListener(new MouseAdapter() {
            //TODO: Profile info
        });
        menu.add(header);

        JMenuItem menuCSV = new JMenuItem("Classes");
        menu.add(menuCSV);
        JMenuItem workoutLog = new JMenuItem("Personal Workout Log");
        menu.add(workoutLog);

        menuBar.add(menu);
        menu.add(menuRemove);
        menu.addSeparator();

        return menuBar;
    }
}
