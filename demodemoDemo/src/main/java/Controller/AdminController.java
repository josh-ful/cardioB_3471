package Controller;

import UserInterface.Admin.AdminDashboardScene;

import javax.swing.*;

/*
 * this class serves as the admin user type controller
 */
public class AdminController implements Controller{
    /**
     * creates AdminDashboardScene on frame
     *
     * @param frame JFrame which dashboard is displayed on
     */
    public void createDashboard(JFrame frame) {
        new AdminDashboardScene(frame);
    }
}
