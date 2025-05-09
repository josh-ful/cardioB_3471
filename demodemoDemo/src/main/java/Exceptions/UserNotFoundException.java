package Exceptions;
/*
 * this class represents UserNotFoundException type Exception object
 */
public class UserNotFoundException extends RuntimeException{
    /**
     * constructs an AlreadyRegisteredException type Exception object
     *
     * @param errorMessage String error message
     */
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
