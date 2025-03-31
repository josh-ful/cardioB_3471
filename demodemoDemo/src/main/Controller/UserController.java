package main.Controller;

import main.FitnessCourse.*;
import main.UserInformation.UserStorage;

public class UserController implements Controller{
    public static void enterExercise(String name, String description) {
        Exercise e = new Exercise(name);
        e.setDescription(description);
        UserStorage.addExercise(e);
        System.out.println(name + " : " + description);
    }
}
