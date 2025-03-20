package SoftwareEngineering;

public class userStorage {
    private static String name;
    private static String password;

    public userStorage() {}

    public String getName() {
        return name;
    }

    public static void setName(String n) {
        name = n;
    }

    public String getPassword() {
        return password;
    }

    public static void setPassword(String p) {
        password = p;
    }

    public boolean infoInputted(){
        return name != null && password != null;
    }
}
