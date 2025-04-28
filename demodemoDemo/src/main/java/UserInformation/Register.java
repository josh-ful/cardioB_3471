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

public class Register implements LoginHardCodes {

    private static final Logger logger = Logger.getLogger(LoginHardCodes.class.getName());
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

        if (usingSQL) {
            Connection conn = DBConnection.getConnection();
            String insertSql = "INSERT INTO users (username, password, type) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                addUser(ps, user, pass);
                ps.setString(3, type);
                success = ps.executeUpdate() > 0;

            } catch (SQLIntegrityConstraintViolationException e) {
                logger.log(Level.INFO, "Sorry, that username is already in use.!");
                throw new AlreadyRegisteredException("User already exists!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            success = localRegisterLogic(user, pass, type, success);
        }

        return success;
    }
    /**
     * confirms registration against hardcoded logins
     *
     * @param user username of user
     * @param pass password of user
     * @param type
     * @return boolean if registration was a success
     */
    private static boolean localRegisterLogic(String user, String pass, String type, boolean success) {
        if (!logins.containsKey(user)) {
            success = true;
            logins.put(user, pass);

            setName(user);
            setPassword(pass);
            setType(type);
        }

        return success;
    }
}
