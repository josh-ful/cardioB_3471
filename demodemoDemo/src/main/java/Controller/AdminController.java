package Controller;

import UserInterface.AdminDashboardScene;

import javax.swing.*;

/*
 * this class serves as the admin user type controller
 */
public class AdminController implements Controller{

    public void createDashboard(JFrame frame) {
        new AdminDashboardScene(frame);
    }
}
