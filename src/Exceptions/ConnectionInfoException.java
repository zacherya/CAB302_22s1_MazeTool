package Exceptions;

/**
 * Connection info exception for raising database related issues, extends the Exception class.
 * @author Zac Adams
 */
public class ConnectionInfoException extends Exception {
    /**
     * A custom method for defining issues that occur while unwrapping or defining
     * database connection variables from a particular file or call.
     * @param message The message to display for error context
     * @author Zac Adams
     */
    public ConnectionInfoException(String message) {
        super(message);
    }
}
