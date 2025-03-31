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
    public static boolean loginLogic(String user, String pass){
        boolean success = false;
        boolean usingSQL = DatabaseInfo.states.get("SQL");
        if(usingSQL){
            String query = "SELECT password FROM users WHERE username = ?";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, user);
                ResultSet rs = stmt.executeQuery();

                if(rs.next()){
                    String hashedPassword = rs.getString("password");

                    if (BCrypt.checkpw(pass, hashedPassword)) {//compares hashed and plaintext password
                        System.out.println("Login successful");
                        success = true;
                    } else {
                        System.out.println("Invalid password");
                    }
                }
                else {
                    System.out.println("User not found");
                }

            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        else{
            if(logins.containsKey(user) && pass.equals(logins.get(user))){
                success = true;
            }
        }

        if(success){
            setName(user);
            setPassword(pass);
        }

        return success;
    }
}
