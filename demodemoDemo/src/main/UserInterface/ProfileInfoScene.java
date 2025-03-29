/**
 * this class creates a scene that displays
 * the users profile info
 */

package main.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * this class is an extension of Scenes.java that creates a
 * scene with a menu and a welcome method
 */
public class ProfileInfoScene extends Scenes{
<<<<<<< Updated upstream
    JPanel panel = new JPanel();

    public ProfileInfoScene(JFrame frame){
        createPI_SCENE(frame);
    }

    private void panelLayout() { panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); }


=======
    /**
     * Constructs a new 'ProfileInfoScene' with the specified frame
     *
     * @param frame
     */
    public ProfileInfoScene(JFrame frame){
        createPI_SCENE(frame);
    }
    /**
     *
     *
     * @param frame
     */
>>>>>>> Stashed changes
    public void createPI_SCENE(JFrame frame) {
        panelLayout();
        super.createAndShowGUI(frame);

        frame.add(panel);
    }
}
