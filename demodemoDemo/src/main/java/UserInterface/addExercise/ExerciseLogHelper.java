package UserInterface.addExercise;

import Controller.UserController;
import FitnessCourse.Exercise;
import UserInformation.CurrentUser;



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
        for(Exercise e : CurrentUser.getExercises()){
            matrix[i][0] = e.getName();
            matrix[i][1] = e.getDescription();
            i++;
        }
        return matrix;
    }
}
