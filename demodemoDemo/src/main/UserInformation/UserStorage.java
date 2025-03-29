/**
 * this class stores user information as the user
 * uses the app
 */
package main.UserInformation;

import main.FitnessCourse.*;

import java.util.HashSet;
import java.util.Set;
/**
 * this class stores user information
 */
public class UserStorage {
    private static String name;
    private static String password;
<<<<<<< Updated upstream
=======
    private static String type;
    /**
     * initializes exerciseSet
     *
     * @return set of exercises
     */
>>>>>>> Stashed changes
    private static Set<Exercise> exerciseSet = new HashSet<>();
    /**
     * returns name of user
     *
     * @return name
     */
    public static String getName() {
        return name;
    }
    /**
     * sets name of user
     *
     * @param n name
     */
    public static void setName(String n) {
        name = n;
    }
    /**
     * returns password of user
     *
     * @return password
     */
    public static String getPassword() {
        return password;
    }
    /**
     * sets password of user
     *
     * @param p password
     */
    public static void setPassword(String p) {
        password = p;
    }
<<<<<<< Updated upstream

=======
    /**
     * returns type of user
     *
     * @return type of user
     */
    public static String getType() {
        return type;
    }
    /**
     * set type of user
     *
     * @param utStatus
     */
    public static void setType(Boolean utStatus) {
        if (utStatus) {
            type = "Trainer";
        } else {
            type = "User";
        }
    }
    /**
     * returns if user info was inputted
     *
     * @return boolean
     */
>>>>>>> Stashed changes
    public static boolean infoInputted(){
        return name != null && password != null;
    }
    /**
     * returns name and password
     *
     * @return name and password
     */
    public static String userInfo() {
        return name + " " + password;
    }
    /**
     * add exercise to exercise set
     *
     * @param e exercise set
     */
    public static void addExercise(Exercise e) {
        exerciseSet.add(e);
    }
}
