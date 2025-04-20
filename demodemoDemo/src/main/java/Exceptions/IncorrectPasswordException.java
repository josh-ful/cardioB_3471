package Exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String e) {
        super(e);
    }
}
