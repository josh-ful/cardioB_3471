package UserInterface;

import Controller.TrainerController;
import FitnessCourse.Course;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class HostClassScene extends Scenes {

    private List<Course> allClasses;
    private JPanel listContainer;
    private JScrollPane scrollPane;

    public HostClassScene(JFrame frame) {
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
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

        // --- Center: scrollable list container ---
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(listContainer);
        panel.add(scrollPane, BorderLayout.CENTER);

        //create new class button -> dialog
        JButton newClassBtn = new JButton("Create New Class");
        newClassBtn.addActionListener(e -> new CreateClassDialog(frame));
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomBar.add(newClassBtn);
        panel.add(bottomBar, BorderLayout.SOUTH);

        //fetching all classes
        allClasses = TrainerController.getClassesForCurrentTrainer();

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

    private void rebuildList() {
        // Grab current filter & search
        JComboBox<?> filterBox = (JComboBox<?>)((JPanel)panel.getComponent(0)).getComponent(1);
        String selected = (String)filterBox.getSelectedItem();
        String search = ((JTextField)((JPanel)panel.getComponent(0)).getComponent(3))
                .getText().trim().toLowerCase();

        // Filter in-memory
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

    private JPanel makeClassPanel(Course cls) {
        JPanel row = new JPanel(new BorderLayout(10, 5));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        //class info
        JLabel info = new JLabel("<html><b>" + cls.getName() + "</b><br/>" + cls.getTime() + "</html>");
        row.add(info, BorderLayout.CENTER);

        //host button on the right
        JButton hostBtn = new JButton("Host");
        hostBtn.addActionListener(e -> {
            System.out.println("Clicking host button");
            //new HostClassDialog((JFrame)SwingUtilities.getWindowAncestor(row), cls);
        });
        row.add(hostBtn, BorderLayout.EAST);

        return row;
    }
}
