package Exceptions;

public class DatabaseException extends Exception {
    private final String errorCode; // Error code for categorizing the error
    private final String query;    // Query causing the error

    // Constructor with message only
    public DatabaseException(String message) {
        super(message);
        this.errorCode = null;
        this.query = null;
    }

    // Constructor with message and cause
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.query = null;
    }

    // Constructor with message, error code, and query
    public DatabaseException(String message, String errorCode, String query) {
        super(message);
        this.errorCode = errorCode;
        this.query = query;
    }

    // Constructor with message, cause, error code, and query
    public DatabaseException(String message, Throwable cause, String errorCode, String query) {
        super(message, cause);
        this.errorCode = errorCode;
        this.query = query;
    }

    // Get the error code
    public String getErrorCode() {
        return errorCode;
    }

    // Get the query
    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return "DatabaseException{" +
                "message='" + getMessage() + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", query='" + query + '\'' +
                ", cause=" + getCause() +
                '}';
    }
}
