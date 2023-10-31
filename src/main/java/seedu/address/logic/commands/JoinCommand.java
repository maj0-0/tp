package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Adds a person specified by email to a group specified by group number.
 */
public class JoinCommand extends Command {

    public static final String COMMAND_WORD = "join";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the person specified by email to the group specified by group number.\n"
            + "Group number must be a positive integer. The maximum number of members a group can have is 5.\n"
            + "Parameters: "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GROUP + "GROUP NUMBER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "johnd@u.nus.edu "
            + PREFIX_GROUP + "1";

    public static final String MESSAGE_JOIN_SUCCESS = "Join successful! %1$s\nhas joined %2$s!";
    public static final String MESSAGE_JOIN_EMAIL_NOT_FOUND = "Person with the provided email not found.";
    public static final String MESSAGE_JOIN_GROUP_NOT_FOUND = "Group with the provided group number not found.";
    public static final String MESSAGE_PERSON_ALREADY_IN_GROUP = "The provided person is " +
            "already a member of the provided group.";
    public static final String MESSAGE_GROUP_FULL = "Join failed as the group already has 5 members.";
    public static final String MESSAGE_PERSON_IN_ANOTHER_GROUP = "The provided person is already in another group.";

    private final Email targetEmail;
    private final int targetGroupNumber;

    public JoinCommand(Email targetEmail, int targetGroupNumber) {
        this.targetEmail = targetEmail;
        this.targetGroupNumber = targetGroupNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Find the person with the provided email
        Optional<Person> personToJoin = model.getPersonWithEmail(targetEmail);

        if (personToJoin.isEmpty()) {
            throw new CommandException(MESSAGE_JOIN_EMAIL_NOT_FOUND);
        }

        // Find the group with the provided email
        Optional<Group> groupToJoin = model.getGroupWithNumber(targetGroupNumber);

        if (groupToJoin.isEmpty()) {
            throw new CommandException(MESSAGE_JOIN_GROUP_NOT_FOUND);
        }

        if (groupToJoin.get().hasMember(personToJoin.get())) {
            throw new CommandException(MESSAGE_PERSON_ALREADY_IN_GROUP);
        }

        if (model.personIsInAGroup(personToJoin.get())) {
            throw new CommandException(MESSAGE_PERSON_IN_ANOTHER_GROUP);
        }

        if (groupToJoin.get().isFull()) {
            throw new CommandException(MESSAGE_GROUP_FULL);
        }

        // Add the person to the group
        model.addPersonToGroup(personToJoin.get(), groupToJoin.get());

        return new CommandResult(String.format(MESSAGE_JOIN_SUCCESS,
                Messages.format(personToJoin.get()), Messages.format(groupToJoin.get())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof JoinCommand)) {
            return false;
        }

        JoinCommand otherJoinCommand = (JoinCommand) other;
        return targetEmail.equals(otherJoinCommand.targetEmail)
                && targetGroupNumber == otherJoinCommand.targetGroupNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetEmail", targetEmail)
                .add("targetGroupNumber", targetGroupNumber)
                .toString();
    }
}
