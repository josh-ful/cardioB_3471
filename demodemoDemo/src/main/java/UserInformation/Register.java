/**
 * this class takes the information inputted while user is
 * registering and stores it in the logins
 */
package UserInformation;

import Exceptions.AlreadyRegisteredException;
import main.DatabaseInfo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static UserInformation.CurrentUser.*;
import static main.DBConnection.addUser;

import main.DBConnection;

public class Register {

    private static final Logger logger = Logger.getLogger(Register.class.getName());
    /**
     * confirms that a registration scenario was a success
     * @param user username of user
     * @param pass password of user
     * @param type
     */
    public static boolean registerLogic(String user, String pass, String type)
            throws SQLException, AlreadyRegisteredException {
        boolean success = false;
        boolean usingSQL = DatabaseInfo.states.get("SQL");

        String insertSql = "INSERT INTO userInfo (username, password, type) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(insertSql);
            addUser(ps, user, pass, type);
            success = ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.log(Level.INFO, "Sorry, that username is already in use!");
            throw new AlreadyRegisteredException("User already exists!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
