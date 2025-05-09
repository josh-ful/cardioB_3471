package UserInterface.Login;

import Controller.Controller;
import Exceptions.UserNotFoundException;
import UserInformation.CurrentUser;
import UserInterface.Profile;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static UserInformation.SecurityQuestions.securityQuestions;

public class OnboardingDialog extends JDialog {
    GridBagConstraints c;
    static JSpinner txtAge;
    static JComboBox txtGender;
    static JTextField txtEmail;
    static JComboBox txtSecurityQuestion;
    static JTextField txtSecurityAnswer;

    /**
     * constructs an OnboardingDialog object
     *
     * @param editOnboarding boolean
     */
    public OnboardingDialog(boolean editOnboarding) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setTitle("Onboarding");
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        txtAge = new JSpinner();
        txtAge.setModel(new SpinnerNumberModel(15, 15, 120, 1));
        txtGender = new JComboBox(new String[]{"male", "female"});
        txtEmail = new JTextField(45);
        txtSecurityQuestion = new JComboBox(securityQuestions);
        txtSecurityAnswer = new JTextField(30);

        addRow(panel, new JLabel("Age:"), 0, 0);
        addRow(panel, txtAge, 0, 1);
        addRow(panel, new JLabel("Gender:"), 1, 0);
        addRow(panel, txtGender, 1, 1);
        addRow(panel, new JLabel("Email:"), 2, 0);
        addRow(panel, txtEmail, 2, 1);
        addRow(panel, new JLabel("Security Question:"), 3, 0);
        addRow(panel, txtSecurityQuestion, 3, 1);
        addRow(panel, new JLabel("Security Answer:"), 4, 0);
        addRow(panel, txtSecurityAnswer, 4, 1);
        addRow(panel, this.getSubmitButton(editOnboarding), 5, 0);

        add(panel);
        setVisible(true);
        pack();
    }

    /**
     * constructs a submit button
     *
     * @param editOnboarding boolean
     */
    private JButton getSubmitButton(boolean editOnboarding) {
        JButton btnSubmit = new JButton("Submit");

        btnSubmit.addActionListener(e -> {
            try{
                Controller.insertOnboardingInfo((int)txtAge.getValue(), txtGender.getSelectedItem().toString(),
                        txtEmail.getText(), txtSecurityQuestion.getSelectedIndex(), txtSecurityAnswer.getText());
                if (editOnboarding) {
                    CurrentUser.initialize(CurrentUser.getName());
                    Profile.refreshInfoPanel();
                    JOptionPane.showMessageDialog(this,
                            "Your information has been updated!");
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            "Congrats! You're onboarding information has been inputted. \r\n" +
                                    "Return to main menu and login!",
                            "Registration Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(SQLException ex){
                // TODO maybe this should throw a runtime exception cuz it should work
                JOptionPane.showMessageDialog(null, ex.getMessage() + ". Please try again",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }catch(UserNotFoundException ex){
                // TODO maybe this should throw a runtime exception cuz it should work
                JOptionPane.showMessageDialog(this, ex.getMessage() + ". Please try again",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        });

        return btnSubmit;
    }

    /**
     * adds a row to the grid bag constraints
     *
     * @param panel JPanel
     * @param comp Component
     * @param row int
     * @param col int
     */
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