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
        addRow(panel, new JLabel("Username:"), 0, 1);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        addRow(panel, new JLabel("Username:"), 1, 1);

        panel.add(new JLabel(CurrentUser.getName()),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("User Type:"),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(new JLabel(CurrentUser.getType()),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Age:"),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 3;
        panel.add(new JLabel(CurrentUser.getAge().toString()),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(new JLabel("Gender:"),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 4;
        panel.add(new JLabel(CurrentUser.getGender()),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(new JLabel("Email:"),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 5;
        panel.add(new JLabel(CurrentUser.getEmail()),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 6;
        panel.add(getEditOnboardingBtn(),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 7;
        panel.add(getResetPasswordBtn(),c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 8;
        panel.add(getLogoutBtn(frame),c);
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
