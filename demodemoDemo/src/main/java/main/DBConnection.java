package main;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import static main.DatabaseInfo.*;
/*
 * this class represents a DBConnection object
 * which implements the DatabaseInfo interface
 */
public class DBConnection implements DatabaseInfo {
    private static  String URL;
    private static final String USER = "fitnessuser";
    private static final String PASSWORD = "strongpassword123";
    private static Connection conn;

    /**
     * establishes connection to the port
     *
     * @param port String port id
     */
    public DBConnection(String port) {
        states.put("SQL", true);
        URL = "jdbc:mysql://localhost:" + port + "/fitnessdb";
        try {
            // Explicitly load MySQL JDBC driver clearly
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Now, connect clearly
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("✅ Connected to MySQL successfully!");
            }
            else {
                System.out.println("Connection failed!");
            }


            //CHECK CONNECTION TO RIGHT DATABASE (LIST USERS/HASHED PASSWORDS)
            String sql = "SELECT username FROM users";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ResultSet rs = ps2.executeQuery();
            String sql2 = "SELECT password FROM users";
            PreparedStatement ps3 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps3.executeQuery();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates DBConnection object
     *
     */
    public DBConnection() {
    }
    /**
     * adds user
     *
     * @param ps PreparedStatement ps
     * @param username String username
     * @param plainPassword String normal text password
     */
    public static void addUser(PreparedStatement ps, String username, String plainPassword, String type) throws SQLException {
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        ps.setString(1, username);
        ps.setString(2, hashed);
        ps.setString(3, type);

    }
    /**
     * gets connection
     *
     * @return Connection to database
     */
    public static Connection getConnection() {
        try {
            if (conn.isClosed()) {
                System.out.println("Connection closed!");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection doesn't exist!");
        }
        return null;
    }

}
