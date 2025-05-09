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
import UserInformation.DailyMetrics.DailyMetricDAO;

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

    //current metrics
    private static Double currentWeight;
    private static Double avgSleep;
    private static Double avgCalories;
    private static Double avgWorkout;


    //goal metrics
    private static Double weightGoal;
    private static Double avgSleepGoal;
    private static Double avgCaloriesGoal;
    private static Double avgWorkoutGoal;


    //TODO make controller global/static in main?
    public static Controller controller;

    // todo move statistics and exercise list to their own classes
    private static ArrayList<Exercise> exerciseList = new ArrayList<>();
    private static Integer weight;
    /**
     * sets current user's id
     *
     * @param userId int id of current user
     */
    //needs to be called after successful login!
    public static void setId(int userId) {
        id = userId;
    }
    /**
     * sets current user's type
     *
     * @param type String type of current user
     */
    public static void setType(String type) {
        type = type;
    }
    /**
     * initializes a new user in the database
     *
     * @param username String username of current user
     */
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
    /**
     * gets current user's id
     *
     * @return Integer userId id of current user
     */
    public static Integer getId() {
        return id;
    }
    /**
     * gets current user's age
     *
     * @return Integer age of current user
     */
    public static Integer getAge() {
        return age;
    }
    /**
     * gets current user's gender
     *
     * @return String gender of current user
     */
    public static String getGender() {
        return gender;
    }
    /**
     * gets current user's email
     *
     * @return String email of current user
     */
    public static String getEmail() {
        return email;
    }
    /**
     * creates controller of type of current user
     *
     */
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

    /**
     * clears information of current user
     *
     */
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
    /**
     * sets user's average sleep to parameter
     *
     * @param avgSleep Double average sleep time of current user
     */
    public static void setAvgSleep(Double avgSleep) {
        CurrentUser.avgSleep = avgSleep;
    }
    /**
     * sets user's current weight to parameter
     *
     * @param currentWeight Double current weight of current user
     */
    public static void setCurrentWeight(Double currentWeight) {//TODO hook up with database
        CurrentUser.currentWeight = currentWeight;
    }
    /**
     * sets user's average calories to parameter
     *
     * @param avgCalories Double average calories consumed of current user
     */
    public static void setAvgCalories(Double avgCalories) {
        CurrentUser.avgCalories = avgCalories;
    }
    /**
     * sets user's average workout duration to parameter
     *
     * @param avgWorkout Double average workout duration of current user
     */
    public static void setAvgWorkout(Double avgWorkout) {
        CurrentUser.avgWorkout = avgWorkout;
    }
    /**
     * gets user's weight goal
     *
     * @return Double weight goal
     */
    public static Double getWeightGoal() {
        if (CurrentUser.weightGoal == null){
            return UserController.getUserGoal("weight");
        }
        return weightGoal;
    }
    /**
     * gets user's average sleep goal
     *
     * @return Double average sleep goal
     */
    public static Double getAvgSleepGoal() {
        if (CurrentUser.avgSleepGoal == null){
            return UserController.getUserGoal("sleep");
        }
        return avgSleepGoal;
    }
    /**
     * gets user's average calories goal
     *
     * @return Double average calories goal
     */
    public static Double getAvgCaloriesGoal() {
        if (CurrentUser.avgCaloriesGoal == null){
            return UserController.getUserGoal("calories");
        }
        return avgCaloriesGoal;
    }
    /**
     * gets user's average workout duration goal
     *
     * @return Double average workout duration goal
     */
    public static Double getAvgWorkoutGoal() {
        if (CurrentUser.avgWorkoutGoal == null){
            return UserController.getUserGoal("workout");
        }
        return avgWorkoutGoal;
    }
    /**
     * gets user's average sleep time goal
     *
     * @return Double average sleep time goal
     */
    public static Double getAvgSleep() throws SQLException {
        if(avgSleep == null){
            DailyMetricDAO.getAvgSleep();
        }
        return avgSleep;
    }
    /**
     * gets user's current weight
     *
     * @return Double current weight
     */
    public static Double getCurrentWeight() throws SQLException {
        if(currentWeight == null){
            DailyMetricDAO.getCurrentWeight();
        }
        System.out.println(currentWeight);
        return currentWeight;
    }
    /**
     * gets user's average calories consumed
     *
     * @return Double average calories consumed
     */
    public static Double getAvgCalories() throws SQLException {
        if(avgCalories == null){
            DailyMetricDAO.getAvgCalories();
        }
        return avgCalories;
    }
    /**
     * gets user's average workout duration
     *
     * @return Double average workout duration
     */
    public static Double getAvgWorkout() throws SQLException {
        if(avgWorkout == null){
            DailyMetricDAO.getAvgWorkoutDur();
        }
        return avgWorkout;
    }
}
