package Controller;

import FitnessCourse.*;
import UserInformation.UserStorage;
import UserInterface.ExerciseLogScene;
import UserInterface.addExercise.ExerciseLogHelperCSV;
import UserInterface.addExercise.ExerciseLogHelperSQL;
import UserInterface.addExercise.ExerciseLogHelper;

import main.DatabaseInfo;

import java.util.ArrayList;
import java.util.Set;

/*
 * this class serves as the general user type controller
 */
public class UserController implements Controller{

    public UserController() {
        System.out.println("UserController");
        if (DatabaseInfo.states.get("SQL"))
        {
            // Establish SQL connection to ExerciseLogHelper
        }
        else {
            new ExerciseLogHelperCSV("src/resources/testCreateExercise.csv");
            UserStorage.importExercises(ExerciseLogHelperCSV.readCSV());
        }
    }


    public static void enterWeight(int weight){
        UserStorage.setWeight(weight);
    }

    /**
     * enters exercise information
     *
     * @param name of exercise
     * @param description of exercise
     */
    public static void addExercise(String name, String description) {
        Exercise e = new Exercise(name);
        e.setDescription(description);
        System.out.println("Name:" + e.getName());
        System.out.println("Description: " + e.getDescription());
        UserStorage.addExercise(e);

        if(DatabaseInfo.states.get("SQL")){
            // TODO SQL Implementation
            ExerciseLogHelperSQL.addExercise(name, description);
        }
        else {
            ExerciseLogHelperCSV.update();
        }
    }
    /**
     * adds exercise
     *
     * @param name String name
     * @param description String description
     */
    public static void newExercise(String name, String description) {
        ExerciseLogHelper.addExercise(name, description);
    }
    /**
     *
     *
     */
    public static void clearExercises() {
        UserStorage.clearExercises();
    }

    public static ArrayList<Exercise> getExercises() {
        return UserStorage.getExercises();
    }

    public static String[][] getTableMatrix() {
        return ExerciseLogHelper.getTableMatrix();
    }
}
