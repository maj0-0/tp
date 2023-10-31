package seedu.address.model.group.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TaskLoaderTest {

    @Test
    public void testLoadTasks() {
        UniqueTaskList tasksList = TaskLoader.loadTasks();
        assertNotNull(tasksList, "Task list should not be null");
        assertEquals(2, tasksList.getTaskList().size(), "Number of tasks does not match");
        // other assertions based on your requirements
    }
}
