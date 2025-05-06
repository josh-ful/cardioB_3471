/**
 * this class stores user information as the user
 * uses the app
 * kiera shepperd
 * noah mathew
 * carter lewis
 */
package UserInformation;

import Controller.*;
import Exceptions.UserNotFoundException;
import FitnessCourse.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import static UserInformation.SecurityQuestions.securityQuestions;

//todo make sure all onboarding info is inputted at register

public class CurrentUser {
    private static Integer id;
    private static String name;
    private static String type;
    private static Integer age;
    private static String gender;
    private static String email;
    private static int securityQ;
    private static String securityAnswer;

    //TODO make controller global/static in main?
    public static Controller controller;

    // todo move statistics and exercise list to their own classes
    private static ArrayList<Exercise> exerciseList = new ArrayList<>();
    private static Integer weight;

    //needs to be called after successful login!
    public static void setId(int userId) {
        id = userId;
    }

    public static void setType(String type) {
        type = type;
    }

    public static void updateCurrentUser(){
        //query users table with name
        //set id? type? (in theory) these can't be changed
        //def set age, gender, email, sQ, sA from what is stored in database
    }

    //TODO make sure this works,
    public static void initialize(String username) throws UserNotFoundException{
        try (Connection conn = main.DBConnection.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM userInfo WHERE username = ?"
            );
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                name = username;
                id = resultSet.getInt("id");
                type = resultSet.getString("type");
                age = resultSet.getInt("age");
                gender = resultSet.getString("gender");
                email = resultSet.getString("email");
                securityQ = resultSet.getInt("securityQ");
                securityAnswer = resultSet.getString("securityA");
                createController();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Gets the name of the user.
     *
     * @return name of the user
     */
    public static String getName() {
        return name;
    }
    /**
     * sets the name of the user
     *
     * @param n string name
     */
    public static void setName(String n) {
        name = n;
    }

    /**
     * get the type of the user
     *
     * @return type of user
     */
    public static String getType() {
        return type;
    }
    public static int getTypeInt() {
        if(type.equals("Admin")){
            return 2;
        } else if (type .equals("Trainer")) {
            return 1;
        } else {
            return 0;
        }
    }

    public static Integer getId() {
        return id;
    }
    public static Integer getAge() {
        return age;
    }
    public static String getGender() {
        return gender;
    }
    public static String getEmail() {
        return email;
    }
    public static String getSecurityQ() {
        return securityQuestions[securityQ];
    }
    public static String getSecurityAnswer() {
        return securityAnswer;
    }

    private static void createController() {
        switch (type){
            case ("admin"):
                controller = new AdminController();
                break;
            case ("trainer"):
                controller = new TrainerController();
                break;
            case ("general"):
                controller = new UserController();
        }
    }

    /**
     * checks if the user has been instantiated
     *
     * @return boolean if there is info stored in the user
     */
    public static boolean infoInputted(){
        return name != null && id != null;
    }
    /**
     * gets the name and password of the user
     *
     * @return username and description
     */
    public static String userInfo() {
        return name + " " + type;
    }
    /**
     * adds an exercise to the user's exercise set
     *
     * @param e exercise new exercise
     */
    public static void addExercise(Exercise e) {
        exerciseList.add(e);
    }
    /**
     * gets the user's exercise set
     *
     * @return exercise set of the user
     */
    public static ArrayList<Exercise> getExercises() {
        return exerciseList;
    }
    /**
     * removes all exercises from the user's exercise set
     *
     *
     */
    public static void clearExercises() {
        exerciseList.clear();
    }
    /**
     * set user's weight
     *
     * @param w int weight
     */
    public static void setWeight(int w) {
        weight = w;
    }
    /**
     * get the user's weight
     *
     * @return weight of the user
     */
    public static Integer getWeight() {
        return weight;
    }

//    public static void importExercises(ArrayList<Exercise> set) {
//        clearExercises();
//        exerciseList.addAll(set);
//    }


    public static void destroy(){
        id = null;
        name = null;
        type = null;
        age = null;
        gender = null;
        email = null;

        exerciseList.clear();
        weight = null;
        controller = null;
    }
}
