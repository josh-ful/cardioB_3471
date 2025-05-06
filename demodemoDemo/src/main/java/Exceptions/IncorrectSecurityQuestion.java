package Exceptions;

public class IncorrectSecurityQuestion extends RuntimeException {
    public IncorrectSecurityQuestion(String e) {
        super(e);
    }
}

