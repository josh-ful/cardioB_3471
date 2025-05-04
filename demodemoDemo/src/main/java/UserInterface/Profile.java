package UserInterface;

import javax.swing.*;

import Controller.Controller;
import UserInformation.CurrentUser;
import UserInterface.Login.OnboardingDialog;

public class Profile extends Scenes{
    public Profile(JFrame frame) {
        createAndShowGUI(frame);
    }

    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        //TODO make this look so much nicer
        panel.add(new JLabel("Username:"));
        panel.add(new JLabel(CurrentUser.getName()));
        panel.add(new JLabel("User Type:"));
        panel.add(new JLabel(CurrentUser.getType()));

        panel.add(getResetPasswordBtn());

        panel.add(new JLabel("Age:"));
        panel.add(new JLabel(CurrentUser.getAge().toString()));
        panel.add(new JLabel("Gender:"));
        panel.add(new JLabel(CurrentUser.getGender()));
        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(CurrentUser.getEmail()));

        panel.add(getEditOnboardingBtn());
        panel.add(getLogoutBtn(frame));

        frame.add(panel);
    }

    private static JButton getEditOnboardingBtn() {
        JButton btnEditOnboarding = new JButton("Edit Information");

        btnEditOnboarding.addActionListener(e->{
            new OnboardingDialog();
        });

        return btnEditOnboarding;
    }

    private static JButton getResetPasswordBtn() {
        JButton btnResetPassword = new JButton("Reset Password");

        btnResetPassword.addActionListener(e->{
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to reset your password?");
            if (confirm == JOptionPane.YES_OPTION) {
                new UserResetPasswordDialog(Controller.getUsername());
            }
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
}
