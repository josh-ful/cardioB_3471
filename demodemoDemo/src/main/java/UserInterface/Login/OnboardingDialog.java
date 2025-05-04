package UserInterface.Login;

import Controller.Controller;

import javax.swing.*;

public class OnboardingDialog extends JDialog {
    static JSpinner txtAge;
    static JComboBox txtGender;
    static JTextField txtEmail;
    static JComboBox txtSecurityQuestion;
    static JTextField txtSecurityAnswer;

    public final static String[] securityQuestions = {"What is the name of your first pet?",
            "What is your mother's maiden name?",
            "What was the name of your elementary school?",
            "What was the model of your first car?",
            "What was the name of the street you grew up on?"};

    public OnboardingDialog() {
        setTitle("Onboarding");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        txtAge = new JSpinner();
        txtAge.setModel(new SpinnerNumberModel(15, 15, 120, 1));
        txtGender = new JComboBox(new String[]{"male", "female"});
        txtEmail = new JTextField(45);
        txtSecurityQuestion = new JComboBox(securityQuestions);
        txtSecurityAnswer = new JTextField(30);

        panel.add(new JLabel("Age:"));
        panel.add(txtAge);
        panel.add(new JLabel("Gender:"));
        panel.add(txtGender);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Security Question:"));
        panel.add(txtSecurityQuestion);
        panel.add(new JLabel("Security Answer:"));
        panel.add(txtSecurityAnswer);
        panel.add(this.getSubmitButton());

        add(panel);
        setVisible(true);
        pack();
    }

    private JButton getSubmitButton() {
        JButton btnSubmit = new JButton("Submit");

        btnSubmit.addActionListener(e -> {
            Controller.insertOnboardingInfo((int)txtAge.getValue(), txtGender.getSelectedItem().toString(),
                    txtEmail.getText(), txtSecurityQuestion.getSelectedIndex(), txtSecurityAnswer.getText());
            dispose();
        });

        return btnSubmit;
    }
}
