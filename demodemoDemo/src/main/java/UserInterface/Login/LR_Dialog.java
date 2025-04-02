package UserInterface.Login;

import UserInterface.Scenes;

import javax.swing.*;

public class LR_Dialog extends Scenes {
    public LR_Dialog(boolean success){
        JDialog dialog = new JDialog();
        dialog.setSize(200, 200);

        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);

        if (success){
           dialog.setTitle("Success");
        }
        else {
            dialog.setTitle("Error");
        }
    }



}
