/**
 * this class creates a scene that verifies that the
 * login info is valid
 */
package UserInformation;

import main.DBConnection;
import main.DatabaseInfo;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

import static UserInformation.UserStorage.setName;
import static UserInformation.UserStorage.setPassword;

public class Login implements LoginHardCodes {
    /**
     *
     *
     * @param user string username
     * @param pass string password
     * @return boolean of login success
     */
    public static boolean loginLogic(String user, String pass) throws SQLException {
        boolean success = false;
        boolean usingSQL = DatabaseInfo.states.get("SQL");

        if(usingSQL){
            String query = "SELECT password FROM users WHERE username = ?";
            Connection conn = DBConnection.getConnection();

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");

                    if (BCrypt.checkpw(pass, hashedPassword)) {//compares hashed and plaintext password
                        success = true;
                    } else {
                        // TODO throw an exception for this and catch with dialog in LoginScene??
                        System.out.println("Password Incorrect");
                        throw new SQLException("Password incorrect");
                    }
                } else {
                    // TODO throw an exception for this and catch with dialog in LoginScene??
                    System.out.println("User not found");
                    throw new SQLException("User not found");
                }
            }
        }

        else{
            success = localLoginLogic(user, pass, success);
        }

        if(success){
            setName(user);
            setPassword(pass);
        }

        return success;
    }
    /**
     *
     * @param
     * @return
     */
    private static boolean localLoginLogic(String user, String pass, boolean success) {
        if(logins.containsKey(user) && pass.equals(logins.get(user))){
            success = true;
        }
        return success;
    }

}
