package UserInterface;

import Exceptions.IncorrectPasswordException;
import Exceptions.IncorrectSecurityAnswer;
import Exceptions.IncorrectSecurityQuestion;
import Exceptions.UserNotFoundException;
import UserInformation.UserQuery;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static UserInformation.SecurityQuestions.securityQuestions;
/*
 * this class represents a UserResetPasswordDialog object
 * containing information about UserResetPasswordDialogs
 */
public class UserResetPasswordDialog extends JDialog {
    GridBagConstraints c;
    JTextField username;
    JComboBox securityQuestion;
    JTextField securityAnswer;
    JPasswordField newPasswordField;
    JPasswordField newPasswordField2;
    public static final Dimension FRAME_DIM = new Dimension(450, 800);
    /**
     * constructs a UserResetPasswordDialog object
     *
     */
    public UserResetPasswordDialog() {
        super((Frame)null, "Reset Your Password", true);
        createAndShowGui(null);
        this.setSize(FRAME_DIM);
        this.setResizable(false);
    }
    /**
     * constructs a UserResetPasswordDialog object
     *
     * @param user String name
     */
    public UserResetPasswordDialog(String user) {
        super((Frame)null, "Reset Your Password", true);
        createAndShowGui(user);
        this.setSize(FRAME_DIM);
        this.setResizable(false);
    }
    /**
     * creates and displays gui
     *
     * @param user String name
     */
    private void createAndShowGui(String user) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        username = new JTextField(user, 15);
        securityQuestion = new JComboBox(securityQuestions);
        securityAnswer = new JTextField(15);
        newPasswordField = new JPasswordField(15);
        newPasswordField2 = new JPasswordField(15);

        int row = 0;

        addRow(panel, new JLabel("Username:"), row++);
        addRow(panel, username, row++);
        addRow(panel, new JLabel("Security Question:"), row++);
        addRow(panel, securityQuestion, row++);
        addRow(panel, new JLabel("Security Answer:"), row++);
        addRow(panel, securityAnswer, row++);
        addRow(panel, new JLabel("New Password:"), row++);
        addRow(panel, newPasswordField, row++);
        addRow(panel, new JLabel("Confirm New Password:"), row++);
        addRow(panel, newPasswordField2, row++);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(getConfirmButton());
        buttonPanel.add(getCancelButton());

        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        panel.add(buttonPanel, c);

        setContentPane(panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
    /**
     * creates button to confirm resetting a password and updates
     *
     * @return JButton
     */
    private JButton getConfirmButton(){
        JButton confirmBtn = new JButton("Confirm");

        confirmBtn.addActionListener(e -> {
            try {
                UserQuery.userExists(username.getText());
                if (securityQuestion.getSelectedIndex() != UserQuery.securityQ(username.getText())) {
                    throw new IncorrectSecurityQuestion("Security question is not associated with this user");
                } else if (!(securityAnswer.getText().equals(UserQuery.securityA(username.getText())))) {
                    throw new IncorrectSecurityAnswer("Answer to the security question is incorrect");
                } else if (newPasswordField2.getPassword().length == 0) {
                    throw new IncorrectPasswordException("New password cannot be empty");
                } else if (!(Arrays.equals(newPasswordField.getPassword(), newPasswordField2.getPassword()))) {
                    throw new IncorrectPasswordException("Passwords do not match");
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to reset your password?");
                if (confirm == JOptionPane.YES_OPTION) {
                    UserQuery.changePassword(username.getText(), Arrays.toString(newPasswordField.getPassword()));
                    JOptionPane.showMessageDialog(UserResetPasswordDialog.this, "Password changed successfully");
                }
                
                dispose();
            }catch(UserNotFoundException | IncorrectSecurityAnswer |
                   IncorrectSecurityQuestion | IncorrectPasswordException ex){
                JOptionPane.showMessageDialog(UserResetPasswordDialog.this, ex.getMessage() + "\n Please try again",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 11;
        return confirmBtn;
    }
    /**
     * creates button to cancel resetting a password
     *
     * @return JButton
     */
    private JButton getCancelButton() {
        JButton cancelBtn = new JButton("Cancel");

        cancelBtn.addActionListener(e -> {
            dispose();
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 12;
        return cancelBtn;
    }
    /**
     * adds a row to the display
     *
     * @param panel displayed on
     * @param comp component added
     * @param row added
     */
    public void addRow(JPanel panel, Component comp, int row) {
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        panel.add(comp, c);
    }
}
