package SoftwareEngineering;

public class Login extends LoginHardCodes {
    public boolean loginLogic(String user, String pass){
       boolean success = false;

        if(logins.containsKey(user) && pass.equals(logins.get(user))){
            success = true;
        }

        return success;
    }
}
