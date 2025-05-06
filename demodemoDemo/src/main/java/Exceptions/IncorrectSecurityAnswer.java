package Exceptions;

public class IncorrectSecurityAnswer extends RuntimeException {
    public IncorrectSecurityAnswer(String e) {
        super(e);
    }
}

