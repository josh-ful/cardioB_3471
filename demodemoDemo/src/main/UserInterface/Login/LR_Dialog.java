package main.UserInterface.Login;

import main.UserInterface.Scenes;

import javax.swing.*;
/**
 * this class is an extension of Scenes that creates a
 * dialog with titles depending on situation
 */
public class LR_Dialog extends Scenes {
    /**
     * Constructs a new 'LR_Dialog' with the specified boolean
     *
     * @param success boolean
     */
    public LR_Dialog(boolean success){
        JDialog dialog = new JDialog();
        dialog.setSize(200, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);

        if (success){
            successfulDialog(dialog);
        }
        else{
            unsuccessfulDialog(dialog);
        }
    }
    /**
     * sets title of dialog to success
     *
     * @param dialog
     */
    public static void successfulDialog(JDialog dialog){
        dialog.setTitle("Success!");
    }
    /**
     * sets title of dialog to failure
     *
     * @param dialog
     */
    public static void unsuccessfulDialog(JDialog dialog){
        dialog.setTitle("Failure!");
    }
}
