/**
 * this class takes the information inputted while user is
 * registering and stores it in the logins
 */
package UserInformation;

import main.DatabaseInfo;

import java.sql.*;

import static UserInformation.UserStorage.*;
import static main.DBConnection.addUser;

import main.DBConnection;

public class Register implements LoginHardCodes {
    /**
     * confirms that a registration scenario was a success
     * @param user username of user
     * @param pass password of user
     * @param utStatus
     */
    public static boolean registerLogic(String user, String pass, Boolean utStatus)
            throws SQLException {
        boolean success = false;
        boolean usingSQL = DatabaseInfo.states.get("SQL");

        if (usingSQL) {
            Connection conn = DBConnection.getConnection();
            String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                addUser(ps, user, pass);
                success = ps.executeUpdate() > 0;

            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Sorry, that username is already in use.!");
                throw new SQLException("User already exists!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            success = localRegisterLogic(user, pass, utStatus, success);
        }

        return success;
    }
    /**
     * confirms registration against hardcoded logins
     *
     * @param user username of user
     * @param pass password of user
     * @param utStatus
     * @return boolean if registration was a success
     */
    private static boolean localRegisterLogic(String user, String pass, Boolean utStatus, boolean success) {
        if (!logins.containsKey(user)) {
            success = true;
            logins.put(user, pass);

            setName(user);
            setPassword(pass);
            setType(utStatus);
        }

        return success;
    }
}
