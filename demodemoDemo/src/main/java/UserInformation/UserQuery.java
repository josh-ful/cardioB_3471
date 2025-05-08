package UserInformation;

import Exceptions.UserNotFoundException;
import main.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/*
 * this class represents a UserQuery object
 * containing information about UserQuery's
 */
public class UserQuery {
    /**
     * determines if a user exists
     *
     * @param user String user name
     */
    public static void userExists(String user) {
        try(Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM userInfo WHERE username = ?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new UserNotFoundException("User not found");
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found");
        }
    }
    /**
     * executes security question from specific user
     *
     * @param username String username of user
     */
    public static int securityQ(String username) {
        try(Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT securityQ FROM userInfo WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("securityQ");
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found");
        }

        return -1;
    }
    /**
     * gets security answer from user
     *
     * @param username String username of user
     * @return String response
     */
    public static String securityA(String username) {
        try(Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT securityA FROM userInfo WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getString("securityA");
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found");
        }

        return null;
    }
    /**
     * changes password to parameter
     *
     * @param username String username of user
     * @param newPassword String new password
     */
    public static int changePassword(String username, String newPassword) {
        int updated = 0;

        try(Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE userInfo SET password = ? WHERE username = ?");

            String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            ps.setString(1, hashed);
            ps.setString(2, username);

            updated = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Password change failed");
        }

        return updated;
    }

}

