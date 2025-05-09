package Controller;

import UserInterface.Admin.AdminDashboardScene;

import javax.swing.*;

/*
 * this class serves as the admin user type controller
 */
public class AdminController implements Controller{

    public void createDashboard(JFrame frame) {
        new AdminDashboardScene(frame);
    }

//    public ArrayList<Map<String, String>> getAllUsers() {
//        return new ArrayList<HashMap<String, String>>();
//    }
}
