package UserInterface;

import javax.swing.*;

import Controller.Controller;
import UserInformation.CurrentUser;
import UserInterface.Login.OnboardingDialog;

import java.awt.*;

public class Profile extends Scenes{
    GridBagConstraints c;
    public Profile(JFrame frame) {
        createAndShowGUI(frame);
    }

    protected void panelLayout() {
        //panel.setSize(600, 600);
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
    }
    /**
     * creates gui of weightGraphScene
     *
     * @param frame JFrame which the gui will be created on
     */
    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        panelLayout();
        c = new GridBagConstraints();

        //TODO make this look so much nicer
        addRow(panel, new JLabel("Username:"), 1, 0);
        addRow(panel, new JLabel(CurrentUser.getName()), 1, 1);
        addRow(panel, new JLabel("User Type:"), 2, 0);
        addRow(panel, new JLabel(CurrentUser.getType()), 2, 1);
        addRow(panel, new JLabel("Age:"), 3, 0);
        addRow(panel, new JLabel(CurrentUser.getAge().toString()), 3, 1);
        addRow(panel, new JLabel("Gender:"), 4, 0);
        addRow(panel, new JLabel(CurrentUser.getGender()),4, 1);
        addRow(panel, new JLabel("Email:"),5, 0);
        addRow(panel, new JLabel(CurrentUser.getEmail()),5, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(getEditOnboardingBtn());
        buttonPanel.add(getResetPasswordBtn());
        c.gridx = 0;
        c.gridy = 6;
        panel.add(buttonPanel, c);
        addRow(panel, getLogoutBtn(frame), 7,0);
        frame.add(panel);
    }

    private static JButton getEditOnboardingBtn() {
        JButton btnEditOnboarding = new JButton("Edit Information");

        btnEditOnboarding.addActionListener(e->{
            new OnboardingDialog(true);
        });

        return btnEditOnboarding;
    }

    private static JButton getResetPasswordBtn() {
        JButton btnResetPassword = new JButton("Reset Password");

        btnResetPassword.addActionListener(e->{
            new UserResetPasswordDialog(Controller.getUsername());
        });


        return btnResetPassword;
    }

    private static JButton getLogoutBtn(JFrame frame) {
        JButton btnLogout = new JButton("Logout");

        btnLogout.addActionListener(e->{
            Controller.destroyCurrentUser();
            new HomeScreen(frame);
        });

        return btnLogout;
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
