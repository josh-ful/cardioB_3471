/**
 * this class creates a scene that verifies that the
 * login info is valid
 */
package UserInformation;

import Controller.UserController;
import Exceptions.IncorrectPasswordException;
import Exceptions.UserNotFoundException;
import main.DBConnection;
import main.DatabaseInfo;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static UserInformation.CurrentUser.*;

public class Login {
    private static final Logger logger = Logger.getLogger(Login.class.getName());
    /**
     * validates login inputs with logins stored in database
     *
     * @param username string username
     * @param pass string password
     * @return boolean of login success
     */

    public static boolean loginLogic(String username, String pass) throws UserNotFoundException, IncorrectPasswordException, SQLException {
        String query = "SELECT password FROM userInfo WHERE username = ?";

        /*
            Carter changed the conditional blocks with this -
            if there's a problem we can change it back
             */
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");

                if (!BCrypt.checkpw(pass, hashedPassword)) {
                    throw new IncorrectPasswordException("Incorrect password\n");
                }
            }
            else{
                throw new UserNotFoundException("User not found\n");
            }

        } catch (UserNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
            throw e;
        } catch (IncorrectPasswordException e) {
            logger.log(Level.INFO, e.getMessage());
            throw e;
        }

        CurrentUser.initialize(username);
        return true;
    }
}
