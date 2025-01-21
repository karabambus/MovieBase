package Exceptions;

/**
 * Custom exception to handle login-related errors.
 */
public class LoginException extends Exception {
    private final String errorCode; // to categorize error types
    private final String username; // to track the affected username

    public LoginException(String message) {
        super(message);
        this.errorCode = null;
        this.username = null;
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.username = null;
    }


    public LoginException(String message, String errorCode, String username) {
        super(message);
        this.errorCode = errorCode;
        this.username = username;
    }

    public LoginException(String message, Throwable cause, String errorCode, String username) {
        super(message, cause);
        this.errorCode = errorCode;
        this.username = username;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "LoginException{" +
                "message='" + getMessage() + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", username='" + username + '\'' +
                ", cause=" + getCause() +
                '}';
    }
}
