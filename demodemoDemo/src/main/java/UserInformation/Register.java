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
    public static boolean registerLogic(String user, String pass, Boolean utStatus){
        boolean success = false;
        boolean usingSQL = DatabaseInfo.states.get("SQL");

        //TODO: validate input before passing to register logic

        if(usingSQL){

            try (Connection conn = DBConnection.getConnection()) {
                String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(insertSql);
                addUser(ps, user, pass);
                success = ps.executeUpdate() > 0;
            } catch(SQLIntegrityConstraintViolationException e){
                System.out.println("User already exists!");
                //TODO: Add dialog for this message
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        else{
            if(!logins.containsKey(user)){
                success = true;
                logins.put(user, pass);

                setName(user);
                setPassword(pass);
                setType(utStatus);
            }
        }

        return success;
    }
}
