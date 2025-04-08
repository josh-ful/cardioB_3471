package UserInterface.Login;

import UserInterface.Scenes;

import javax.swing.*;

public class LR_Dialog extends Scenes{
    protected JDialog dialog;
    /**
     * Constructs a RegisterScene object
     *
     * @param success boolean if dialog was successful
     */
    public LR_Dialog(boolean success) {
        dialog = makeDialog();

        if (success) {
            dialog.setTitle("Success!");
        } else {
            dialog.setTitle("Failure!");
        }
    }
    /**
     * creates dialog page
     *
     */
    private JDialog makeDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(200, 200);

        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);

        return dialog;
    }
}
