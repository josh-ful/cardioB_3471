package Controller;

import Exceptions.UserNotFoundException;
import UserInformation.CurrentUser;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * this interface serves as the controller
 */
public interface Controller {
    /**
     * creates dashboard on frame
     *
     * @param frame JFrame which dashboard is displayed on
     */
    public void createDashboard(JFrame frame) throws SQLException;
    /**
     * gets username of current user
     *
     * @return String Current User's name
     */
    public static String getUsername(){
        return CurrentUser.getName();
    }
    /**
     * receives onboarding info and inserts info
     *
     * @param age int onboarding insert
     * @param gender String onboarding insert gender
     * @param email String onboarding insert email
     * @param securityQ int onboarding insert securityQ
     * @param securityA String onboarding insert securityA
     */
    public static boolean insertOnboardingInfo(int age, String gender,
                                            String email, int securityQ,
                                            String securityA) throws SQLException {
        String query = "UPDATE userInfo "
                + "SET AGE = " + age + ", "
                + "GENDER = '" + gender + "', "
                + "EMAIL = '" + email + "', "
                + "SECURITYQ = " + securityQ + ", "
                + "SECURITYA = '" + securityA + "' "
                + "WHERE USERNAME = '" + CurrentUser.getName() + "'";
        boolean success = false;

        try (Connection conn = main.DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserNotFoundException("User not found");
        }

        return success;
    }
    /**
     * destroys current user
     *
     */
    public static void destroyCurrentUser(){
        CurrentUser.destroy();
    }


}
