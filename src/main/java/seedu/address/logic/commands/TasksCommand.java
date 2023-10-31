package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.tasks.Task;

/**
 * Lists all tasks for a specific group.
 */
public class TasksCommand extends Command {

    public static final String COMMAND_WORD = "tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks for a specific group. "
        + "Parameters: GROUP_NUMBER (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing all tasks for group %1$d.";
    public static final String MESSAGE_GROUP_NOT_FOUND = "The group with number %1$d could not be found.";

    private final int groupNumber;

    public TasksCommand(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Group> optionalGroup = model.getGroupWithNumber(groupNumber);

        if (optionalGroup.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_GROUP_NOT_FOUND, groupNumber));
        }

        Group group = optionalGroup.get();
        Set<Task> tasks = group.getTasks();

        if (tasks.isEmpty()) {
            return new CommandResult(String.format("There are no tasks for group %d.", groupNumber));
        }

        StringBuilder taskList = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            taskList.append(i).append(". ").append(task).append("\n");
            i++;
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, groupNumber) + "\n" + taskList.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TasksCommand // instanceof handles nulls
            && groupNumber == ((TasksCommand) other).groupNumber); // state check
    }
}
