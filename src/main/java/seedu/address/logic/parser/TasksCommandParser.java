package seedu.address.logic.parser;

import seedu.address.logic.commands.TasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


/**
 * Parses input arguments and creates a new TasksCommand object.
 */
public class TasksCommandParser implements Parser<TasksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TasksCommand
     * and returns a TasksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public TasksCommand parse(String args) throws ParseException {
        try {
            int groupNumber = ParserUtil.parseGroupNumber(args);
            return new TasksCommand(groupNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TasksCommand.MESSAGE_USAGE), pe);
        }
    }
}
