package seedu.address.model.group.exceptions;

/**
 * Signals that the operation will result in duplicate Tasks (Tasks are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTaskException extends TaskException {

    /**
     * Initializes a new instance of `DuplicateTaskException` with a default error message.
     */
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
