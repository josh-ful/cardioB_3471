/**
 * this class is the main
 */
package main;

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
//import org.w3c.dom.ls.LSInput;
//import UserInterface.addExercise.LogCSVReaderWriter;

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




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean sql = false;
        DBConnection db = new DBConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Use SQL server y/n: ");
        String answer = scanner.nextLine();
        if(answer.equals("y")) {
            sql = true;
            System.out.print("Enter your specified port (330X): ");
            String portNumber = scanner.nextLine();
            System.out.println("Connecting to " + portNumber + "...");
            scanner.close();

            db = new DBConnection(portNumber);
        }

        JFrame frame = new JFrame();
        HomeScreen hs = new HomeScreen(frame);
        //when me has information stored in it

        if (UserStorage.infoInputted()) {
            System.out.println("Login Successful");
            System.out.println(UserStorage.userInfo());
        }
    }
}