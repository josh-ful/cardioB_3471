package Exceptions;
/*
 * this class represents IncorrectSecurityQuestion type Exception object
 */
public class IncorrectSecurityQuestion extends RuntimeException {
    /**
     * constructs an AlreadyRegisteredException type Exception object
     *
     * @param e String error message
     */
    public IncorrectSecurityQuestion(String e) {
        super(e);
    }
}

