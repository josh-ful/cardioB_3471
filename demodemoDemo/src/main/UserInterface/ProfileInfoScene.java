package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileInfoScene extends Scenes{
    JPanel panel = new JPanel();

    public ProfileInfoScene(JFrame frame){
        createEL_SCENE(frame);
    }

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


    public void createEL_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        frame.add(panel);
    }
}
