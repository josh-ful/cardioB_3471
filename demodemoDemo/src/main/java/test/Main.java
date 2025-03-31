package test;

/*
Author: Trello Fellows
Date Created: 3/18/2025
File Name: Main.java
Description: Holds the main file and holds the display of the menu (for now)

Date Last Modified: 3/18/2025


PUT YOUR NAME HERE:
Josh Fulton
Noah Mathew
Carter Lewis
Emily Wokoek
Kiera Shepperd
Lawson Hale
 */

import UserInformation.*;
import UserInterface.*;

import javax.swing.*;
import java.awt.*;

//From main we are starting program so we need to bring the UI to scene 1 (login),
// and then from there try to give control to the individual controllers based on userType

//Data Abstraction (Locally based implementation--remove when implementing database)
//Map of valid usernames and passwords [DONE]
//Login scene: [DONE]
//Right now in the main file
//Could be extracted into its own class later

//within the scene the interactions should confirm validity of login [DONE]
//then assign the user with a session
//and in creating a session assign the correct type of controller
//in order to bring it to the next screen
//imo what makes sense is have a session obj that we have 3 different constructors for
//*KIERA: can create a session interface, implimented by 3 diff classes for the diff users
//if we have a session constructed with a admin type, in its creation its should bring
//the user to the next scene? in its construction create a new gui? that overrides the login gui'

//TO DO:
//Create Login GUI, that leads to two different screens login & register [DONE]
//Create fields in the login scene that check with the local map to see if the login info is valid [DONE]
//Create fields in the register scene, that accept valid formatted info and add that data to the map [DONE]
//Extract login and register button scenes to separate classes & link to one gui

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class Main {

    public class companyDetails extends JPanel {
        public companyDetails() {
            JLabel companyName = new JLabel("CardioB™");
            companyName.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
            companyName.setForeground(Color.BLACK);
            //companyName.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(companyName);
        }
    }

    private static void addUser(PreparedStatement ps, String username, String plainPassword) throws SQLException {
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        ps.setString(1, username);
        ps.setString(2, hashed);
        ps.executeUpdate();
    }

    public static void main(String[] args) {

        String url = "jdbc:mysql://192.168.137.234:3306/fitnessdb";
        String user = "fitnessuser";
        String password = "strongpassword123";

        try {
            // Explicitly load MySQL JDBC driver clearly
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Now, connect clearly
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL successfully!");

            String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertSql);

            // clearly define initial users here:
            //addUser(ps, "alice", "password1");
            //addUser(ps, "bob", "password2");
            //addUser(ps, "charlie", "password3");

            String sql = "SELECT username FROM users";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                //System.out.println("Password: " + rs.getString("password"));
            }

            String sql2 = "SELECT password FROM users";
            PreparedStatement ps3 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps3.executeQuery();
            while (rs2.next()) {
                System.out.println("Password: " + rs2.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        HomeScreen hs = new HomeScreen(frame);
        //when me has information stored in it
        System.out.println("next command ran anyways");

        if (UserStorage.infoInputted()) {
            System.out.println("Login Successful");
            System.out.println(UserStorage.userInfo());
            //userMenuScene umS = new userMenuScene(frame);
        }
        //userMenuScene umS = new userMenuScene(frame);

        //System.out.println("mommy nodes"); >:( no
    }
}