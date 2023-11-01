package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.tasks.Task;
import seedu.address.model.group.tasks.TaskList;

/**
 * Marks a task as done within a specific group.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark task specified as done.\n"
            + "Parameters: " + PREFIX_GROUP + "[GROUP NUMBER] " + PREFIX_TASK_INDEX + "[TASK INDEX]\n"
            + "Example: " + COMMAND_WORD + " gr/2 ti/3";

    public static final String MESSAGE_SUCCESS = "Marked task number %2$s for group %1$s";
    public static final String MESSAGE_TASK_GROUP_NOT_FOUND = "Group with the provided group number not found.";
    public static final String MESSAGE_INVALID_TASK_INDEX = "Invalid task index. Task not found.";
    private final int groupId;
    private final int taskIndex;

    /**
     * Creates a MarkCommand to mark a task as done within a specific group.
     *
     * @param groupId The group ID in which the task exists.
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int groupId, int taskIndex) {
        this.groupId = groupId;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Retrieve the group using the groupId
        Optional<Group> optionalGroup = model.getGroupWithNumber(groupId);
        if (optionalGroup.isEmpty()) {
            throw new CommandException(MESSAGE_TASK_GROUP_NOT_FOUND);
        }
        Group group = optionalGroup.get();
        TaskList taskList = group.getTasks();
        if (isValidTaskIndex(taskIndex, taskList)) {
            Task taskToMark = taskList.getTasks().get(taskIndex);
            taskToMark.mark();
            String displayedTasks = taskList.toString();
            return new CommandResult(String.format(MESSAGE_SUCCESS, groupId, taskIndex + 1) + "\n" + displayedTasks);
        } else {
            throw new CommandException(MESSAGE_INVALID_TASK_INDEX);
        }
    }

    private boolean isValidTaskIndex(int taskIndex, TaskList taskList) {
        return taskIndex >= 0 && taskIndex < taskList.getTasks().size();
    }

    @Override
    public boolean equals(Object other) {
        // Basic checks
        if (other == this) {
            return true;
        }
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // Property checks
        MarkCommand otherMarkCommand = (MarkCommand) other;
        return groupId == otherMarkCommand.groupId && taskIndex == otherMarkCommand.taskIndex;
    }
}
