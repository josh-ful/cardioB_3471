package Exceptions;
/*
 * this class represents AlreadyRegisteredException type Exception object
 */
public class AlreadyRegisteredException extends RuntimeException{
    /**
     * constructs an AlreadyRegisteredException type Exception object
     *
     * @param message String error message
     */
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
