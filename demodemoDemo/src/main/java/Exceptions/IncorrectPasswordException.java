package Exceptions;
/*
 * this class represents IncorrectPasswordException type Exception object
 */
public class IncorrectPasswordException extends RuntimeException {
    /**
     * constructs an AlreadyRegisteredException type Exception object
     *
     * @param e String error message
     */
    public IncorrectPasswordException(String e) {
        super(e);
    }
}
