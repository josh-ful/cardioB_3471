package main.Controller;

import main.FitnessCourse.*;
import main.UserInformation.UserStorage;
import main.UserInterface.addExercise.LogCSVReaderWriter;

public class UserController implements Controller{
    public static void enterExercise(String name, String description) {
        Exercise e = new Exercise(name);
        e.setDescription(description);
        UserStorage.addExercise(e);
        System.out.println("Name:" + name);
        System.out.println("Description: " + description);

        LogCSVReaderWriter.addExercise(name, description);
    }
}
