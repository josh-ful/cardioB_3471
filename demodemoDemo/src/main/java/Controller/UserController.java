package Controller;

import FitnessCourse.*;
import UserInformation.CurrentUser;
import UserInterface.UserMenuScene;
import UserInterface.addExercise.ExerciseLogHelperCSV;
import UserInterface.addExercise.ExerciseLogHelperSQL;
import UserInterface.addExercise.ExerciseLogHelper;

import main.DatabaseInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
            CurrentUser.importExercises(ExerciseLogHelperCSV.readCSV());
        }
    }


    public static void enterWeight(int weight){
        CurrentUser.setWeight(weight);
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
        CurrentUser.addExercise(e);

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
        CurrentUser.clearExercises();
    }

    public static ArrayList<Exercise> getExercises() {
        return CurrentUser.getExercises();
    }

    public static String[][] getTableMatrix() {
        return ExerciseLogHelper.getTableMatrix();
    }

    public void createDashboard(JFrame frame) {
        new UserMenuScene(frame);
    }
}
