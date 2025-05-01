/**
 * this class creates a scene that verifies that the
 * login info is valid
 */
package UserInformation;

import Exceptions.IncorrectPasswordException;
import Exceptions.UserNotFoundException;
import main.DBConnection;
import main.DatabaseInfo;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static UserInformation.CurrentUser.*;

public class Login implements LoginHardCodes {
    private static final Logger logger = Logger.getLogger(Login.class.getName());
    /**
     * validates login inputs with logins stored in database
     *
     * @param user string username
     * @param pass string password
     * @return boolean of login success
     */

    public static boolean loginLogic(String user, String pass) throws RuntimeException, SQLException {
        boolean success = false;
        boolean usingSQL = DatabaseInfo.states.get("SQL");

        if (usingSQL) {
            String query = "SELECT * FROM users WHERE username = ?";
            Connection conn = DBConnection.getConnection();

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");

                    if (!BCrypt.checkpw(pass, hashedPassword)) {
                        throw new IncorrectPasswordException("Incorrect password\n");
                    }

                    setType(rs.getString("type"));
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

            success = true;
        }
        else {
            success = localLoginLogic(user, pass, success);
        }

        //TODO make one function that does this in controller and put it up with setUserType
        if (success) {
            setName(user);
            setPassword(pass); // Idk anywhere that the program uses password anymore
        }

        return success;
    }

    /**
     * validates login inputs with locally stored login pairs
     * @param user string username
     * @param pass string password
     * @param success boolean successStatus
     * @return boolean of login based on locally stored login inputs
     */
    private static boolean localLoginLogic(String user, String pass, boolean success) {
        if(logins.containsKey(user) && pass.equals(logins.get(user))){
            success = true;
        }
        return success;
    }

}
