package Controller;

import FitnessCourse.*;
import UserInformation.UserStorage;
import UserInterface.addExercise.LogCSVReaderWriter;

import main.DatabaseInfo;
/*
 * this class serves as the general user type controller
 */
public class UserController implements Controller{
    public static void enterWeight(int weight){
        UserStorage.setWeight(weight);
    }

    /**
     * enters exercise information
     *
     * @param name of exercise
     * @param description of exercise
     */
    public static void enterExercise(String name, String description) {
        Exercise e = new Exercise(name);
        e.setDescription(description);
        UserStorage.addExercise(e);
        System.out.println("Name:" + name);
        System.out.println("Description: " + description);
        LogCSVReaderWriter.writeCSV();

        if(DatabaseInfo.states.get("SQL")){
            //SQL Implementation

        }
    }
    /**
     * adds exercise
     *
     * @param name String name
     * @param description String description
     */
    public static void newExercise(String name, String description) {
        LogCSVReaderWriter.addExercise(name, description);
    }
    /**
     *
     *
     */
    public static void clearExercises() {
        UserStorage.clearExercises();
    }
}
