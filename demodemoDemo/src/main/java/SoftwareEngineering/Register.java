package SoftwareEngineering;

import static SoftwareEngineering.userStorage.*;

public class Register implements LoginHardCodes {
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
