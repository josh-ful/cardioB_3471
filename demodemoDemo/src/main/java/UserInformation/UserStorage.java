/**
 * this class stores user information as the user
 * uses the app
 * kiera shepperd
 * noah mathew
 * carter lewis
 */
package UserInformation;

import FitnessCourse.*;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {
    private static String name;
    private static String password;
    private static String type;

    private static Set<Exercise> exerciseSet = new HashSet<>();
    private static Integer weight;
    /**
     * Gets the name of the user.
     *
     * @return name of the user
     */
    public static String getName() {
        return name;
    }
    /**
     *
     *
     * @param
     */
    public static void setName(String n) {
        name = n;
    }
    /**
     *
     *
     * @return
     */
    public static String getPassword() {
        return password;
    }
    /**
     *
     *
     * @param
     */
    public static void setPassword(String p) {
        password = p;
    }
    /**
     *
     *
     * @return
     */
    public static String getType() {
        return type;
    }
    /**
     *
     *
     * @param
     */
    public static void setType(Boolean utStatus) {
        if (utStatus) {
            type = "Trainer";
        } else {
            type = "User";
        }
    }
    /**
     *
     *
     * @return
     */
    public static boolean infoInputted(){
        return name != null && password != null;
    }
    /**
     *
     *
     * @return
     */
    public static String userInfo() {
        return name + " " + password;
    }
    /**
     *
     *
     * @param
     */
    public static void addExercise(Exercise e) {
        exerciseSet.add(e);
    }
    /**
     *
     *
     * @return
     */
    public static Set<Exercise> getExercises() {
        return exerciseSet;
    }
    /**
     *
     *
     *
     */
    public static void clearExercises() {
        exerciseSet.clear();
    }

    public static void addWeight(int w) {
        weight = w;
    }
    public static Integer getWeight() {
        return weight;
    }
}
