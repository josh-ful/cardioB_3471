package UserInterface.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Controller.Controller;
import UserInformation.Login;
import UserInformation.CurrentUser;
import UserInterface.AdminDashboardScene;
import UserInterface.UserMenuScene;
import UserInterface.UserResetPasswordDialog;


public class LoginScene extends LR_Scenes {
    GridBagConstraints c;
    /**
     * Constructs a LoginScene object
     *
     * @param frame which scene is created on
     */
    public LoginScene(JFrame frame) {
        super.createLR_SCENE(frame);
        createLR_SCENE(frame);
    }
    protected void panelLayout() {
        //panel.setSize(600, 600);
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }
    @Override
    public void createLR_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();
        c = new GridBagConstraints();

        int row = 0;
        addRow(panel, new JLabel("Username: "), row, 0 );
        addRow(panel, username, row++, 1 );
        addRow(panel, new JLabel("Password: "), row, 0 );
        addRow(panel, password, row++, 1 );

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(getConfirmLoginButton(frame, username, password));
        buttonPanel.add(getResetPasswordBtn());
        buttonPanel.add(getBackButton(frame));
        c.gridx = 0;
        c.gridy = row++;
        c.gridwidth = 3;
        panel.add(buttonPanel, c);
        frame.add(panel);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Constructs a LoginScene object
     *
     * @param frame JFrame frame which scene is created on
     * @param username JTextField field to input username
     * @param password JPasswordField field to input password
     */
    private static JButton getConfirmLoginButton(JFrame frame, JTextField username, JPasswordField password) {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(400, 30));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = new String(password.getPassword());
                // give something else the information and allow it to make the screen
                boolean success = false;

                try {
                    success = Login.loginLogic(user, pass);
                } catch (RuntimeException | SQLException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (success){
                    CurrentUser.controller.createDashboard(frame);
                }
            }
        });
        return loginButton;
    }

    private static JButton getResetPasswordBtn() {
        JButton btnResetPassword = new JButton("Reset Password");

        btnResetPassword.addActionListener(e->{
            new UserResetPasswordDialog();
        });

        return btnResetPassword;
    }
    public void addRow(JPanel panel, Component comp, int row, int col) {
        c = new GridBagConstraints();
        c.gridx = col;
        c.gridy = row;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        if(col == 1){
            c.weightx = 1.0;
        }
        else{
            c.weightx = 0.0;
        }
        panel.add(comp, c);
    }

}
