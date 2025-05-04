package UserInterface;

import Exceptions.IncorrectSecurityAnswer;
import UserInformation.CurrentUser;

import javax.swing.*;
import java.awt.*;

public class UserResetPasswordDialog extends JDialog {
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
        setLayout(new GridLayout(3, 2, 10, 10));

        JTextField securityAnswer = new JTextField();
        newPasswordField = new JPasswordField();
        newPasswordField2 = new JPasswordField();

        add(new JLabel("Security Question:" + CurrentUser.getSecurityQ()));
        add(new JLabel("Security Answer:"));
        add(securityAnswer);
        add(new JLabel("New Password:"));
        add(newPasswordField);
        add(new JLabel("Confirm New Password:"));
        add(newPasswordField2);

        add(getConfirmButton());
        add(getCancelButton());
    }

    private JButton getConfirmButton() {
        JButton confirmBtn = new JButton("Confirm");

        confirmBtn.addActionListener(e -> {
            try {
                if (!(securityAnswer.getText().equals(CurrentUser.getSecurityAnswer()))) {
                    throw new IncorrectSecurityAnswer("Answer to the security question is incorrect");
                } else if (!(newPasswordField.toString().equals(newPasswordField2.toString()))) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                }
                else {
                    //TODO is good: change password, close dialog
                }
            }catch(IncorrectSecurityAnswer ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
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
