// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: HomeScreen.java is an extension
// of Scenes.java and creates a scene with a title and
// options to register or log in
/**
 * this class is an extension of Scenes.java and creates a scene with a title and
 * options to register or log in
 */

package UserInterface;

import UserInterface.Login.LoginScene;
import UserInterface.Login.RegisterScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends Scenes{
    /**
     * Constructs a HomeScreen object
     *
     */
    public HomeScreen(JFrame frame) {
        createAndShowGUI(frame);
    }
    /**
     * creates a HomeScreen using the super's createAndShowGUI
     * method and adds 2 buttons and an icon
     *
     * @param frame which scenes are created on
     */

    // TODO: Put the GridBagConstraints inside the get<thing>Label method calls
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Trello Fellows®");
        frame.setSize(FRAME_DIM);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 100;
        frame.add(getIconLabel(), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 10;
        frame.add(getCompanyNameLabel(), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        frame.add(getLoginButton(frame), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 15;
        frame.add(getRegisterButton(frame), c);

        frame.setVisible(true);
    }
    /**
     * gets the label containing an image
     *
     * @return label containing icon
     */
    private static JLabel getIconLabel() {
        ImageIcon icon = new ImageIcon("src/resources/cardioB_logo.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(300, 400, Image.SCALE_SMOOTH);

        icon = new ImageIcon(image);
        return new JLabel(icon);
    }
    /**
     * gets positioned label of company name
     *
     * @return label containing company name
     */
    private static JLabel getCompanyNameLabel() {
        JLabel companyName = new JLabel("CardioB");

        companyName.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
        companyName.setForeground(Color.BLACK);
        companyName.setAlignmentY(Component.CENTER_ALIGNMENT);
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        companyName.setVerticalAlignment(SwingConstants.TOP);

        return companyName;
    }
    /**
     * creates a button that leads to login scene
     *
     * @param frame which scenes are created on
     * @return JButton that creates a login scene
     */
    private static JButton getLoginButton(JFrame frame) {
        JButton loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(100, 50));
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        loginButton.addActionListener(al -> new LoginScene(frame));

        return loginButton;
    }
    /**
     * creates a button that leads to registration scene
     *
     * @param frame which scenes are created on
     * @return JButton that creates registration scene
     */
    private static JButton getRegisterButton(JFrame frame) {
        JButton registerButton = new JButton("Register");

        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(100, 50));
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        registerButton.addActionListener(al -> new RegisterScene(frame));

        return registerButton;
    }
}
