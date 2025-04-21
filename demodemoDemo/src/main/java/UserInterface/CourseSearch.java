package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CourseSearch extends Scenes {
    private JTextArea resultsArea;
    private JComboBox<String> courseTypeCombo;
    private JTextField searchField;

    public CourseSearch(JFrame frame) {
        createAndShowGUI(frame);
    }

    @Override
    protected void createAndShowGUI(JFrame frame) {
        super.createAndShowGUI(frame);

        JLabel title = new JLabel("Find a Class");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        JPanel filterPanel = new JPanel(new FlowLayout());
        courseTypeCombo = new JComboBox<>(new String[]{"self", "group"});
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> performSearch());

        filterPanel.add(courseTypeCombo);
        filterPanel.add(searchField);
        filterPanel.add(searchBtn);

        panel.add(filterPanel);

        resultsArea = new JTextArea(15, 40);
        resultsArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultsArea);
        panel.add(scroll);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> new ClassListScene(frame));
        panel.add(backButton);

        frame.setContentPane(panel);
        frame.revalidate();
    }

    private void performSearch() {
        String type = (String) courseTypeCombo.getSelectedItem();
        String search = searchField.getText();

        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement stmt;
            if ("self".equals(type)) {
                stmt = conn.prepareStatement("SELECT id, name, description FROM self_paced_courses WHERE name LIKE ?");
            } else {
                stmt = conn.prepareStatement("SELECT id, name, description FROM group_courses WHERE name LIKE ?");
            }

            stmt.setString(1, "%" + search + "%");
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(" | Name: ").append(rs.getString("name"))
                        .append(" | Desc: ").append(rs.getString("description"))
                        .append("\n");
            }

            resultsArea.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            resultsArea.setText("Error fetching results.");
        }
    }
}
