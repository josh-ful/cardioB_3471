package UserInterface.Trainer;

import Controller.TrainerController;
import FitnessCourse.Course;
import UserInterface.Scenes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainerViewClassesScene extends Scenes {

    private List<Course> allClasses;
    private JPanel listContainer;
    private JScrollPane scrollPane;

    /**
     * constructs a TrainerViewClassesScene
     *
     * @param frame JFrame
     */
    public TrainerViewClassesScene(JFrame frame) {
        createAndShowGUI(frame);
    }

    /**
     * creates and displays GUI for viewing classes for trainers
     *
     * @param frame JFrame
     */
    @Override
    protected void createAndShowGUI(JFrame frame)  {
        super.createAndShowGUI(frame);
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 10));

        //filter and search
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //typefilter
        String[] types = { "All", "self", "group" };
        JComboBox<String> filterBox = new JComboBox<>(types);
        topBar.add(new JLabel("Show:"));
        topBar.add(filterBox);

        //search text field
        JTextField searchField = new JTextField(20);
        topBar.add(new JLabel("Search:"));
        topBar.add(searchField);

        panel.add(topBar, BorderLayout.NORTH);

        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(listContainer);
        panel.add(scrollPane, BorderLayout.CENTER);

        //create new class button -> dialog
        JButton newClassBtn = new JButton("Create New Class");
        newClassBtn.addActionListener(e -> new TrainerCreateClassDialog(frame));
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomBar.add(newClassBtn);
        panel.add(bottomBar, BorderLayout.SOUTH);

        //fetching all classes
        allClasses = TrainerController.getClassesForCurrentTrainer();

        //reload when changing search
        Runnable refreshList = this::rebuildList;
        filterBox.addActionListener(e -> refreshList.run());
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)    { refreshList.run(); }
            public void removeUpdate(DocumentEvent e)    { refreshList.run(); }
            public void changedUpdate(DocumentEvent e)   { refreshList.run(); }
        });

        // Initial build
        rebuildList();

        frame.setContentPane(panel);
        frame.revalidate();
    }

    /**
     * rebuilds list of classes after an update to the system's state
     *
     */
    private void rebuildList() {
        //Grab current filter & search
        JComboBox<?> filterBox = (JComboBox<?>)((JPanel)panel.getComponent(0)).getComponent(1);
        String selected = (String)filterBox.getSelectedItem();
        String search = ((JTextField)((JPanel)panel.getComponent(0)).getComponent(3))
                .getText().trim().toLowerCase();

        List<Course> filtered = allClasses.stream()
                .filter(c -> {
                    boolean typeMatch = selected.equals("All")
                            || (selected.equals("self") && c.getType().equalsIgnoreCase("self"))
                            || (selected.equals("group")     && c.getType().equalsIgnoreCase("group"));
                    boolean textMatch = c.getName().toLowerCase().contains(search);
                    return typeMatch && textMatch;
                }).collect(Collectors.toList());

        listContainer.removeAll();
        for (Course cls : filtered) {
            listContainer.add(makeClassPanel(cls));
        }
        listContainer.revalidate();
        listContainer.repaint();
    }

    /**
     * constructs the panel to display a course
     *
     * @param cls Course
     */
    private JPanel makeClassPanel(Course cls) {
        JPanel row = new JPanel(new BorderLayout(10, 5));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        //class info
        JLabel info = new JLabel("<html><b>" + cls.getName() + "</b><br/>" + cls.getTime() + "</html>");
        row.add(info, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));


        //edit button on the right
        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(e -> {
            System.out.println("Clicking edit button");
            new TrainerEditClassDialog((JFrame)SwingUtilities.getWindowAncestor(row), cls);
        });
        buttonPanel.add(editBtn);


        // manage exercises button
        JButton exercisesBtn = new JButton("Exercises");
        exercisesBtn.addActionListener(e -> {
            // open a new scene in the same frame
            JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(row);
            System.out.println("Clicking exercises button");
            try {
                new TrainerManageCoursesExercisesScene(owner, cls);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(exercisesBtn);
        row.add(buttonPanel, BorderLayout.EAST);
        return row;
    }
}
