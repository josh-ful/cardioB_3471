/**
 * this class stores user information as the user
 * uses the app
 */
package main.UserInformation;

import main.FitnessCourse.*;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {
    private static String name;
    private static String password;
    private static Set<Exercise> exerciseSet = new HashSet<>();

    public static String getName() {
        return name;
    }

    public static void setName(String n) {
        name = n;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String p) {
        password = p;
    }

    public static boolean infoInputted(){
        return name != null && password != null;
    }

    public static String userInfo() {
        return name + " " + password;
    }
    public static void addExercise(Exercise e) {
        exerciseSet.add(e);
    }
}
