package main;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import static main.DatabaseInfo.*;

public class DBConnection implements DatabaseInfo {
    private static  String URL;
    private static final String USER = "fitnessuser";
    private static final String PASSWORD = "strongpassword123";


    public DBConnection(String port) {
        states.put("SQL", true);
        URL = "jdbc:mysql://localhost:" + port + "/fitnessdb";
        System.out.println(URL);
        try {
            // Explicitly load MySQL JDBC driver clearly
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Now, connect clearly
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL successfully!");

            //TESTING ADDING USERS ✅
            //String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
            //PreparedStatement ps = conn.prepareStatement(insertSql);
            //addUser(ps, "alice", "password1");
            //addUser(ps, "bob", "password2");
            //addUser(ps, "charlie", "password3");


            //CHECK CONNECTION TO RIGHT DATABASE (LIST USERS/HASHED PASSWORDS)
            String sql = "SELECT username FROM users";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                //System.out.println("Password: " + rs.getString("password"));
            }
            String sql2 = "SELECT password FROM users";
            PreparedStatement ps3 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps3.executeQuery();
            while (rs2.next()) {
                System.out.println("Password: " + rs2.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBConnection() {
    }

    public static void addUser(PreparedStatement ps, String username, String plainPassword) throws SQLException {
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        ps.setString(1, username);
        ps.setString(2, hashed);
        //ps.executeUpdate();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
