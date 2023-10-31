package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a Group in StudentConnect.
 * Guarantees: details are present and not null, number is immutable.
 */
public class Group {

    private static final int MAXIMUM_SIZE = 5;

    private final int number;
    private Set<Person> members = new HashSet<>();

    /**
     * Constructs a {@code Group}.
     *
     * @param number A valid group number.
     */
    public Group(int number) {
        this.number = number;
    }

    /**
     * Constructs a {@code Group}.
     *
     * @param number A valid group number.
     * @param members The members of the group.
     */
    public Group(int number, Set<Person> members) {
        this.number = number;
        this.members = members;
    }

    /**
     * Adds a person to the group.
     *
     * @param person The person to be added as a member.
     */
    public void addMember(Person person) {
        this.members.add(person);
    }

    public int getNumber() {
        return this.number;
    }

    public Set<Person> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    /**
     * Returns true if the set contains an equivalent person as the given argument.
     */
    public boolean hasMember(Person person) {
        return this.members.stream().anyMatch(person::isSamePerson);
    }

    /**
     * Returns true if the group contains the maximum number of members.
     */
    public boolean isFull() {
        return this.members.size() == MAXIMUM_SIZE;
    }

    /**
     * Returns true if the group number is a positive integer.
     */
    public static boolean isValidGroupNumber(String groupNumber) {
        requireNonNull(groupNumber);

        try {
            int value = Integer.parseInt(groupNumber);
            return value > 0 && !groupNumber.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if both groups have the same number.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getNumber() == this.number;
    }

    /**
     * Returns true if both groups have the same number and members.
     * This defines a stronger notion of equality between two groups.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return number == otherGroup.getNumber()
                && members.equals(otherGroup.members);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("group number", number)
                .add("members", members)
                .toString();
    }
}
