// Author: Trello Fellows
// Assignment Title: CardioB
// Java File Description: ProfileInfoScene.java is an extension
// of Scenes.java and creates a scene with...idk what this actually does

package main.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileInfoScene extends Scenes{
    JPanel panel = new JPanel();

    public ProfileInfoScene(JFrame frame){
        createPI_SCENE(frame);
    }

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


    public void createPI_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        frame.add(panel);
    }
}
