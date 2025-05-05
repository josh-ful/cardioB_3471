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

public class UserQuery {
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

