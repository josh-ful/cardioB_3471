package Exceptions;

public class AlreadyRegisteredException extends RuntimeException{

    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
