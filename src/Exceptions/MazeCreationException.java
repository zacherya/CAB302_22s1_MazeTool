package Exceptions;

public class MazeCreationException extends Exception {
    /**
     * A custom method for defining issues that occurred while creating a particular
     * maze image. This can occur while regenerating.
     * @param message The message to display for error context
     * @author Zac Adams
     */
    public MazeCreationException(String message) {
        super(message);
    }
}