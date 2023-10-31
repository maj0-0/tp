package seedu.address.model.group.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.address.model.group.exceptions.TaskException;


/**
 * The Task class represents a task in the StudentConnect application.
 */
public class Task {
    private static ArrayList<Task> arr = new ArrayList<>();
    private static int counter = 0;
    private String task;
    private TaskStatus status;

    private Boolean isNotSaved;


    /**
     * Constructs a Task object with a task description and save status.
     *
     * @param task       The description of the task.
     * @param isNotSaved A boolean indicating whether the task is saved.
     */
    public Task(String task, Boolean isNotSaved) {
        this.task = task;
        this.status = TaskStatus.NOT_DONE;
        this.isNotSaved = isNotSaved;

        if (!task.isEmpty()) {
            addTask(this.task);
        }
    }

    /**
     * Empty constructor for Task.
     */
    public Task() {

    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task's status and description.
     */
    @Override
    public String toString() {
        return status.toString() + " " + this.task;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task description to add.
     */
    public void addTask(String task) {
        if (!task.equals("")) {
            if (!task.isEmpty()) {
                //Duke.taskList.addTask(this); //need to change according to how we implement the main class
                counter++;
            }
        }
    }

    /**
     * Gets the count of tasks.
     *
     * @return The count of tasks.
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public TaskStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the task.
     *
     * @param taskStatus The status to set.
     */
    public void setStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Parses a date and time string to a LocalDateTime object.
     *
     * @param dateTimeString The date and time string in the format "dd/MM/yyyy HHmm".
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws TaskException If the date and time string has an invalid format.
     */
    public LocalDateTime parseDateTime(String dateTimeString) throws TaskException {
        // Split the input string into date and time parts
        String[] parts = dateTimeString.split(" ", 2);

        // Check if there are exactly two parts (date and time)
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid date/time format: "
                    + dateTimeString);
        }

        String datePart = parts[0];
        String timePart = parts[1];

        // Define a formatter for the date part, e.g., "dd/MM/yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Parse the date part into a LocalDate object
        LocalDate date = LocalDate.parse(datePart, dateFormatter);

        // Define a formatter for the time part, e.g., "HHmm"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        // Parse the time part into a LocalTime object
        LocalTime time = LocalTime.parse(timePart, timeFormatter);

        // Combine the date and time into a LocalDateTime object
        return LocalDateTime.of(date, time);
    }

    /**
     * Formats the list of tasks into a human-readable string.
     *
     * @return A formatted string containing the tasks in the list.
     */
    public String formatList() {
        StringBuilder formattedList = new StringBuilder();
        formattedList.append("Here are the tasks in your list:\n");

        /*for (int i = 0; i < Duke.taskList.size(); i++) {
            Task task = Duke.taskList.getTasks().get(i);
            formattedList.append((i + 1)).append(". ").append(task.toString()).append("\n");
        }*/

        //formattedList.append(Ui.horizontalLine);
        return formattedList.toString();
    }


    /**
     * Gets the type of the task.
     *
     * @return The string representing task type.
     */
    public String getTaskType() {
        // Your logic to determine the task type based on the instance's actual class
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else {
            return ""; // Handle unknown task types or add appropriate logic
        }
    }

    /*/**
     * Marks a task as done in the task list and updates
     * the storage file accordingly.
     *
     * @param i The index of the task to be marked as done.
     * @return A message confirming the task's status change.
     * @throws TaskException If the provided index is invalid.
     */
    /*public String mark(int i) throws TaskException {
        if (i > Duke.taskList.getTasks().size() || i <= 0) {
            throw new TaskException(
                    "OOPS!!! Invalid number :(\n"
                    );
        }
        Task markTask = Duke.taskList.getTasks().get(i - 1);
        markTask.status = TaskStatus.DONE;

        // Update the task description in the file
        Storage.updateLineInFile(i, markTask.generateStr());
        return ( "Nice! I've marked this task as done:\n"
                + markTask.toString() + "\n" );
    }*/

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Checks if the current task contains the specified keyword.
     *
     * @param keyword The keyword to search for within the task.
     * @return {@code true} if the task contains the keyword,
     *     {@code false} otherwise.
     */
    public boolean contains(String keyword) {
        return task.contains(keyword);
    }
    /*/**
     * Unmarks a task as done by changing its status to "not done."
     *
     * @param i The index of the task in the list to unmark.
     * @return A string confirming the task's status change.
     * @throws TaskException If the provided index is invalid.
     */
    /*public String unmark(int i) throws TaskException {
        if (i > Duke.taskList.getTasks().size() || i <= 0) {
            throw new TaskException( "OOPS!!! Invalid number :(\n" );
        }
        Task unmarkTask = Duke.taskList.getTasks().get(i - 1);
        unmarkTask.status = TaskStatus.NOT_DONE;
        Storage.updateLineInFile(i, unmarkTask.generateStr());
        return ("Ok, I've marked this task as not done yet:\n"
                + unmarkTask.toString() + "\n" );
    }*/

    /*/**
     * Deletes a task from the task list and updates the
     * storage file accordingly.
     *
     * @param i The index of the task to be deleted.
     * @return A message confirming the deletion of the task and
     * the current task count.
     * @throws TaskException If the provided index is invalid.
     */
    /*public String delete(int i) throws TaskException {
        if (i > Duke.taskList.size() || i <= 0) {
            throw new TaskException(
                    "OOPS!!! Invalid number :(\n");
        }
        Task deleteTask = Duke.taskList.getTasks().get(i - 1);
        counter = counter - 1;
        Duke.taskList.deleteTask(i - 1);
        Storage.deleteLineFromFile(i);
        return ("Noted. I've removed this task:\n" + deleteTask.toString()
                + "\n" + String.format("Now you have %d tasks in the list\n", counter)
                );
    }*/

    /**
     * Returns true if both tasks have the same name and description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
            && otherTask.task.equals(task)
            && otherTask.status.equals(status);
    }

    /**
     * Generates String representation to be saved in text file.
     */
    public String generateStr() {
        return task;
    }

    /**
     * Saves the task to a file.
     */
    public void saveToFile() {
        return;
    }
}
