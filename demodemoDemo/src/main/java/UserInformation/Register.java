/**
 * this class takes the information inputted while user is
 * registering and stores it in the logins
 */
package UserInformation;

import static UserInformation.UserStorage.*;

public class Register implements LoginHardCodes {
    public static boolean registerLogic(String user, String pass, Boolean utStatus){
        boolean success = false;

        if(!logins.containsKey(user)){
            success = true;
            logins.put(user, pass);

            setName(user);
            setPassword(pass);
            setType(utStatus);
        }
        return success;
    }
}
