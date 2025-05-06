/**
 * this class stores user information as the user
 * uses the app
 * kiera shepperd
 * noah mathew
 * carter lewis
 */
package UserInformation;

import Controller.*;
import FitnessCourse.*;

import java.util.ArrayList;
import java.util.Locale;

public class CurrentUser {
    private static String name;
    //TODO delete password from here?
    // I don't know why its here but password should
    // definitely not be stored locally by the program?
    private static String password;
    private static String type;
    public static Controller controller;

    private static ArrayList<Exercise> exerciseList = new ArrayList<>();
    private static Integer weight;
    private static Integer id;

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        CurrentUser.id = id;
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
     * gets the password of the user
     *
     * @return password of the user
     */
    public static String getPassword() {
        return password;
    }
    /**
     * set the password of the user
     *
     * @param p string password
     */
    public static void setPassword(String p) {
        password = p;
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
     * set the type of the user
     *
     * @param userType String
     */
    public static void setType(String userType) {
        type = userType.toLowerCase();
//        System.out.println("setting type to " + type);

        createController();
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
        return name != null && password != null;
    }
    /**
     * gets the name and password of the user
     *
     * @return username and description
     */
    public static String userInfo() {
        return name + " " + password;
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

    public static void importExercises(ArrayList<Exercise> set) {
        clearExercises();
        exerciseList.addAll(set);
    }

    /*public static String[][] getTableMatrix(){
        int i= 0;
        String [][] matrix = new String[UserController.getWeightEntries(id).size()][2];
        for(Exercise e : CurrentUser.getWeightEntries()){
            matrix[i][0] = e.
            matrix[i][1] = e.getDescription();
            i++;
        }
        return matrix;
    }*/
}
