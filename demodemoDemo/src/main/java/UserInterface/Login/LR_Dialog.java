package UserInterface.Login;

import UserInterface.Scenes;

import javax.swing.*;

public class LR_Dialog extends Scenes{
    protected JDialog dialog;

    public LR_Dialog(boolean success) {
        dialog = makeDialog();

        if (success) {
            dialog.setTitle("Success!");
        } else {
            dialog.setTitle("Failure!");
        }
    }

    private JDialog makeDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(200, 200);

        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);

        return dialog;
    }
}
