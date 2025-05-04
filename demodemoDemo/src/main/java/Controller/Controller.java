package Controller;

import UserInformation.CurrentUser;

import javax.swing.*;

/*
 * this interface serves as the controller
 */
public interface Controller {

    public void createDashboard(JFrame frame);

    public static String getUsername(){
        return CurrentUser.getName();
    }

    public static void insertOnboardingInfo(int age, String gender, String email,
                                            int securityQ, String securityA){
        //TODO input into database (change database)
    }

    public static void insertRegisterInfo(String username, String password){
        //TODO input into database
    }

    public static void destroyCurrentUser(){
        CurrentUser.destroy();
    }
}
