package UserInformation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//TODO this entire file can go into register.java because it does not use login
/*
 * this class represents a ValidateLRInputs object which
 * confirms that the information inputted into each dialog
 * scene is valid
 */
public class ValidateLRInputs {

    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9_.-]{5,}$";
    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);
    private static final String PASSWORD_FORMAT =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_FORMAT);
    /**
     * confirms that a username is valid
     *
     * @param username of user
     * @param password of user
     */
    public static void validateRInputs(String username, String password) throws IllegalArgumentException {
        Matcher uMatcher = USERNAME_PATTERN.matcher(username);
        if (!uMatcher.matches()) {
            throw new IllegalArgumentException("Sorry, \"" + username + "\" does not meet criteria\n");
        }

        Matcher pMatcher = PASSWORD_PATTERN.matcher(password);
        if (!pMatcher.matches()) {
            throw new IllegalArgumentException("Sorry, \"" + password + "\" is not a valid password.\n");
        }
    }
}
