package UserInterface.addExercise;

import Controller.UserController;
import FitnessCourse.Exercise;
import UserInformation.UserStorage;

import java.io.*;
import java.util.Iterator;

//TODO these shouldn't be static??
/**
 * this class contains functionality to read and to write from a
 * CSV file and use the information to include in our classes
 */
// RESOLVED no they should because otherwise they wouldn't be accessible
// it breaks if it's not static

public abstract class ExerciseLogHelper {

    public static void addExercise(String name, String description) {
    }
    /**
     * converts set of exercises to 2D matrix for conversion to table
     *
     * @return matrix of exercises by name and description
     */
    public static String[][] getTableMatrix(){
        int i= 0;
        String [][] matrix = new String[UserController.getExercises().size()][2];
        for(Exercise e : UserStorage.getExercises()){
            matrix[i][0] = e.getName();
            //System.out.println(matrix[i][0]);
            matrix[i][1] = e.getDescription();
            // System.out.println(matrix[i][1]);
            i++;
        }
        return matrix;
    }
}
