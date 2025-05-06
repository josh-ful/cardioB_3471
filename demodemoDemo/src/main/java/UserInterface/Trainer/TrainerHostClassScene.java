package UserInterface.Trainer;

import Controller.TrainerController;
import FitnessCourse.Course;
import UserInterface.Scenes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TrainerHostClassScene extends Scenes {

    private List<Course> allClasses;
    private JPanel listContainer;
    private JScrollPane scrollPane;

    public TrainerHostClassScene(JFrame frame) {
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 10));

        //search abr
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JTextField searchField = new JTextField(20);
        topBar.add(new JLabel("Search:"));
        topBar.add(searchField);
        panel.add(topBar, BorderLayout.NORTH);

        //scroll wheel
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(listContainer);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Back button to return to trainer menu
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> new TrainerMenuScene(frame));
        panel.add(backBtn, BorderLayout.SOUTH);

        //fetch all classes but show only group courses
        allClasses = TrainerController.getClassesForCurrentTrainer();

        //reload list on search changes
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { rebuildList(searchField.getText(), frame); }
            public void removeUpdate(DocumentEvent e) { rebuildList(searchField.getText(), frame); }
            public void changedUpdate(DocumentEvent e) { rebuildList(searchField.getText(), frame); }
        };
        searchField.getDocument().addDocumentListener(dl);

        //init query with empty search
        rebuildList("", frame);

        frame.setContentPane(panel);
        frame.revalidate();
    }


    private void rebuildList(String searchText, JFrame frame) {
        String search = searchText.trim().toLowerCase();

        List<Course> filtered = allClasses.stream()
                .filter(c -> c.getType().equalsIgnoreCase("group")
                        && c.getName().toLowerCase().contains(search))
                .collect(Collectors.toList());

        listContainer.removeAll();        //update
        for (Course cls : filtered) {
            listContainer.add(makeClassPanel(cls, frame));
        }
        listContainer.revalidate();
        listContainer.repaint();
    }

    private JPanel makeClassPanel(Course cls, JFrame frame) {
        JPanel row = new JPanel(new BorderLayout(10, 5));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        //class desc
        JLabel info = new JLabel(
                "<html><b>" + cls.getName() + "</b><br/>" + cls.getTime() + "</html>"
        );
        row.add(info, BorderLayout.CENTER);

        //host button
        JButton hostBtn = new JButton("Host");
        hostBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to start " + cls.getName() + "?",
                    "Start Class",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                new TrainerActiveClassScene(frame, cls);
                JOptionPane.showMessageDialog(frame, "Class started.");
            }
        });
        row.add(hostBtn, BorderLayout.EAST);

        return row;
    }
}