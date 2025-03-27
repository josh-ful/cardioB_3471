/**
 * this class creates a scene that displays
 * the users profile info
 */

package main.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileInfoScene extends Scenes{

    public ProfileInfoScene(JFrame frame){
        createPI_SCENE(frame);
    }

    public void createPI_SCENE(JFrame frame) {
        super.createAndShowGUI(frame);

        frame.add(panel);
    }
}
