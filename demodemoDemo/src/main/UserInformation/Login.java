package main.UserInformation;

import static main.UserInformation.UserStorage.setName;
import static main.UserInformation.UserStorage.setPassword;

public class Login implements LoginHardCodes {
    public static boolean loginLogic(String user, String pass){
        boolean success = false;

        if(logins.containsKey(user) && pass.equals(logins.get(user))){
            success = true;
            setName(user);
            setPassword(pass);
        }

        return success;
    }
}
