package SoftwareEngineering;

public class Register extends LoginHardCodes {
    public boolean registerLogic(String user, String pass){
        boolean success = false;

        if(!logins.containsKey(user)){
            success = true;
        }

        return success;
    }
}
