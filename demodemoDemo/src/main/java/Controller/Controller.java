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

    public void createDashboard() throws SQLException;

    public static String getUsername(){
        return CurrentUser.getName();
    }

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

    public static void destroyCurrentUser(){
        CurrentUser.destroy();
    }


}
