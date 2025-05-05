package Controller;

import UserInformation.UserQuery;
import UserInterface.AdminDashboardScene;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/*
 * this class serves as the admin user type controller
 */
public class AdminController implements Controller{

    public void createDashboard(JFrame frame) {
        new AdminDashboardScene(frame);
    }

    public List<Map<String, String>> getAllUsers(){


    }
}
