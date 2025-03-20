package SoftwareEngineering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginRegister {
    
    public void createAndShowGUI(JFrame frame) {
        Map<String,String> logins  = new LinkedHashMap<>();
        logins.put("Noah", "Mathew");
        logins.put("Josh", "Fulton");
        logins.put("Kiera", "Shepperd");
        logins.put("Emily", "Wokoek");
        logins.put("Carter", "Lewis");
        logins.put("Lawson", "Hale");
        
        //JFrame frame = frames;
        //frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Trello Fellows®");

        frame.setSize(450, 800);

        JLabel companyName = new JLabel("CardioB");
        companyName.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
        companyName.setForeground(Color.BLACK);
        companyName.setAlignmentY(Component.CENTER_ALIGNMENT);
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        companyName.setVerticalAlignment(SwingConstants.TOP);

        JButton loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(100, 50));
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        loginScene(frame, loginButton, logins);

        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(100, 50));
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        registerScene(registerButton, logins);
        
        
        ImageIcon icon = new ImageIcon("src/main/resources/cardioB_logo.png");
        Image image = icon.getImage();
        image = image.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel iconLabel = new JLabel(icon);


        c.anchor = GridBagConstraints.PAGE_END;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 100;
        frame.add(iconLabel, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 10;
        frame.add(companyName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        frame.add(loginButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 15;
        frame.add(registerButton, c);


        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


    }

    private static void loginScene(JFrame frame, JButton loginButton, Map<String, String> logins) {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                frame.removeAll();
                frame.revalidate();
                frame.repaint();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Login ™");
                JPanel panel = new JPanel();
                panel.setBackground(Color.RED); // Set background color

                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                JTextField username = new JTextField(20);
                JPasswordField password = new JPasswordField(20);

                JButton loginButton = new JButton("Login");
                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String user = username.getText();
                        String pass = new String(password.getPassword());
                        if(logins.containsKey(user) && pass.equals(logins.get(user))){
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Login Successful!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        }
                        else{
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Login Failed!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        }
                    }
                });
                panel.add(username);
                panel.add(password);
                panel.add(loginButton);
                frame.add(panel);
                frame.setVisible(true);
            }
        });
    }

    private static void registerScene(JButton registerButton, Map<String, String> logins) {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Register");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Register ™");
                JPanel panel = new JPanel();
                panel.setBackground(Color.RED); // Set background color

                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                JTextField username = new JTextField(20);
                JPasswordField password = new JPasswordField(20);

                JButton registerButton = new JButton("Register");
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String user = username.getText();
                        String pass = new String(password.getPassword());
                        if (!logins.containsKey(user)) {
                            logins.put(user, pass);
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Registration Successful!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        } else {
                            JDialog dialog = new JDialog();
                            dialog.setTitle("Registration Failed!");
                            dialog.setSize(200, 200);
                            dialog.setLocationRelativeTo(null);
                            dialog.setResizable(false);
                            dialog.setVisible(true);
                        }
                    }
                });
                panel.add(username);
                panel.add(password);
                panel.add(registerButton);
                frame.add(panel);
                frame.setVisible(true);
            }
        });
    }

}
