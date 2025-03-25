package main.UserInformation;

public class UserStorage {
    private static String name;
    private static String password;

    public static String getName() {
        return name;
    }

    public static void setName(String n) {
        name = n;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String p) {
        password = p;
    }

    public static boolean infoInputted(){
        return name != null && password != null;
    }

    public static String userInfo() {
        return name + " " + password;
    }
}
