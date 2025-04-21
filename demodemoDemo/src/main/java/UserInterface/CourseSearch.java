package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CourseSearch extends JFrame {
    private JComboBox<String> courseTypeCombo;
    private JTextField searchField;
    private JTextArea resultsArea;

    public CourseSearch() {
        setTitle("Course Search");
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Top bar
        JPanel topPanel = new JPanel(new FlowLayout());
        courseTypeCombo = new JComboBox<>(new String[]{"self", "group"});
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        topPanel.add(courseTypeCombo);
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        // Results area
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        searchButton.addActionListener(e -> performSearch());

        setVisible(true);
    }

    private void performSearch() {
        String type = (String) courseTypeCombo.getSelectedItem();
        String query = searchField.getText();

        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement stmt;
            if ("self".equals(type)) {
                stmt = conn.prepareStatement("SELECT id, name, description FROM self_paced_courses WHERE name LIKE ?");
            } else {
                stmt = conn.prepareStatement("SELECT id, name, description FROM group_courses WHERE name LIKE ?");
            }

            stmt.setString(1, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Name: ").append(rs.getString("name"))
                        .append(", Description: ").append(rs.getString("description")).append("\n");
            }

            resultsArea.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            resultsArea.setText("Database error occurred.");
        }
    }
}
