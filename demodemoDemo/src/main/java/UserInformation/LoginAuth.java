package UserInformation;

import main.DBConnection;
/*
 * this class represents a LoginAuth object which
 * extends the DBConnection class
 */
public class LoginAuth extends DBConnection {
    /**
     *
     *
     * @param ip string ip
     */
    public LoginAuth(String ip) {
        super(ip);
    }
}
