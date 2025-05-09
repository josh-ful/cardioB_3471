package Exceptions;
/*
 * this class represents IncorrectSecurityAnswer type Exception object
 */
public class IncorrectSecurityAnswer extends RuntimeException {
    /**
     * constructs an AlreadyRegisteredException type Exception object
     *
     * @param e String error message
     */
    public IncorrectSecurityAnswer(String e) {
        super(e);
    }
}

