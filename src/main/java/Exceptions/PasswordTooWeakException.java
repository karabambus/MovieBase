package Exceptions;

public class PasswordTooWeakException extends RuntimeException {
    public PasswordTooWeakException(String message) {
        super(message);
    }
}