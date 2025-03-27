package main.userInformation;

import static main.UserInformation.UserStorage.setName;
import static main.UserInformation.UserStorage.setPassword;

public class Register implements main.UserInformation.LoginHardCodes {
    public static boolean registerLogic(String user, String pass){
        boolean success = false;

        if(!logins.containsKey(user)){
            success = true;
            logins.put(user, pass);

            setName(user);
            setPassword(pass);
        }

        return success;
    }
}
