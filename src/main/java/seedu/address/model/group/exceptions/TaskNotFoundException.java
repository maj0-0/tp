package seedu.address.model.group.exceptions;

public class TaskNotFoundException extends TaskException {

    /**
     * Initializes a new instance of `TaskNotFoundException` with a default error message.
     */
    public TaskNotFoundException() {
        super("Task is not found in the list");
    }
}
