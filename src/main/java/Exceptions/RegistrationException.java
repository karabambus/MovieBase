package Exceptions;

/**
 * Custom exception to handle registration-related errors.
 */
public class RegistrationException extends Exception {
    private final String errorCode; // Error code for categorizing the error
    private final String username; // Username involved in the error

    // Constructor with message only
    public RegistrationException(String message) {
        super(message);
        this.errorCode = null;
        this.username = null;
    }

    // Constructor with message and cause
    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.username = null;
    }

    // Constructor with message, error code, and username
    public RegistrationException(String message, String errorCode, String username) {
        super(message);
        this.errorCode = errorCode;
        this.username = username;
    }

    // Constructor with message, cause, error code, and username
    public RegistrationException(String message, Throwable cause, String errorCode, String username) {
        super(message, cause);
        this.errorCode = errorCode;
        this.username = username;
    }

    // Get the error code
    public String getErrorCode() {
        return errorCode;
    }

    // Get the username
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "RegistrationException{" +
                "message='" + getMessage() + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", username='" + username + '\'' +
                ", cause=" + getCause() +
                '}';
    }
}
