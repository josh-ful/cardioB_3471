package UserInterface;

import Exceptions.IncorrectPasswordException;
import Exceptions.IncorrectSecurityAnswer;
import Exceptions.IncorrectSecurityQuestion;
import Exceptions.UserNotFoundException;
import UserInformation.UserQuery;

import javax.swing.*;
import java.awt.*;

import static UserInformation.SecurityQuestions.securityQuestions;

public class UserResetPasswordDialog extends JDialog {
    JTextField username;
    JComboBox securityQuestion;
    JTextField securityAnswer;
    JPasswordField newPasswordField;
    JPasswordField newPasswordField2;

    public UserResetPasswordDialog() {
        super((Frame)null, "Reset Your Password", true);
        createAndShowGui(null);
    }

    public UserResetPasswordDialog(String user) {
        super((Frame)null, "Reset Your Password", true);
        createAndShowGui(user);
    }

    private void createAndShowGui(String user) {
        JPanel panel = new JPanel();

        username = new JTextField(user);
        securityQuestion = new JComboBox(securityQuestions);
        securityAnswer = new JTextField();
        newPasswordField = new JPasswordField();
        newPasswordField2 = new JPasswordField();

        panel.add(new JLabel("Username:"));
        panel.add(username);
        panel.add(new JLabel("Security Question:"));
        panel.add(securityQuestion);
        panel.add(new JLabel("Security Answer:"));
        panel.add(securityAnswer);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);
        panel.add(new JLabel("Confirm New Password:"));
        panel.add(newPasswordField2);

        panel.add(getConfirmButton());
        panel.add(getCancelButton());

        setContentPane(panel);
        setVisible(true);
    }

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
                } else if (!(newPasswordField.toString().equals(newPasswordField2.toString()))) {
                    throw new IncorrectPasswordException("Passwords do not match");
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to reset your password?");
                if (confirm == JOptionPane.YES_OPTION) {
                    UserQuery.changePassword(username.getText(), newPasswordField.toString());
                    JOptionPane.showMessageDialog(UserResetPasswordDialog.this, "Password changed successfully");
                }

                dispose();
            }catch(UserNotFoundException | IncorrectSecurityAnswer |
                   IncorrectSecurityQuestion | IncorrectPasswordException ex){
                JOptionPane.showMessageDialog(UserResetPasswordDialog.this, ex.getMessage() + "\n Please try again",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return confirmBtn;
    }

    private JButton getCancelButton() {
        JButton cancelBtn = new JButton("Cancel");

        cancelBtn.addActionListener(e -> {
            dispose();
        });

        return cancelBtn;
    }
}
