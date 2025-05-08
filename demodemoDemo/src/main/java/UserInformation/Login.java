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
     * @param user string username
     * @param pass string password
     * @return boolean of login success
     */
    public static boolean loginLogic(String user, String pass) throws RuntimeException, SQLException {
        String query = "SELECT * FROM userInfo WHERE username = ?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user);
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
            CurrentUser.setId(rs.getInt("id"));
            CurrentUser.setName(rs.getString("username"));
            CurrentUser.setType(rs.getString("type"));

        } catch (UserNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
            throw e;
        } catch (IncorrectPasswordException e) {
            logger.log(Level.INFO, e.getMessage());
            throw e;
        }

        CurrentUser.initialize(user);

        return true;
    }
}
