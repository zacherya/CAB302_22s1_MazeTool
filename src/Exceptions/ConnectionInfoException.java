package Exceptions;

public class ConnectionInfoException extends Exception {
    /**
     * A custom method for defining issues that occured while unwrapping or defining
     * database connection variables from a particular file or call.
     * @param message The message to display for error context
     * @authors Zac Adams
     */
    public ConnectionInfoException(String message) {
        super(message);
    }
}
