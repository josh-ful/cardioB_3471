package Controller;

import FitnessCourse.*;
import UserInformation.UserStorage;
import UserInterface.addExercise.LogCSVReaderWriter;

public class UserController implements Controller{
    public static void enterExercise(String name, String description) {
        Exercise e = new Exercise(name);
        e.setDescription(description);
        UserStorage.addExercise(e);
        System.out.println("Name:" + name);
        System.out.println("Description: " + description);
        LogCSVReaderWriter.writeCSV();
    }

    public static void newExercise(String name, String description) {
        LogCSVReaderWriter.addExercise(name, description);
    }

    public static void clearExercises() {
        UserStorage.clearExercises();
    }
}
