package seedu.address.model.group.tasks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import seedu.address.model.group.exceptions.DuplicateTaskException;
import seedu.address.model.group.exceptions.TaskException;

public class TaskLoader {

    private static final String FILE_PATH = "data/tasks.json";

    public static UniqueTaskList loadTasks() {
        ObjectMapper objectMapper = new ObjectMapper();
        UniqueTaskList uniqueTaskList = new UniqueTaskList();

        try {
            File file = new File(FILE_PATH);
            List<TaskJson> tasks = objectMapper.readValue(file, new TypeReference<List<TaskJson>>() {});

            for (TaskJson taskJson : tasks) {
                Task task;
                if ("deadline".equals(taskJson.getType())) {
                    task = new Deadline(taskJson.getDescription(), false, taskJson.getBy());
                } else {
                    task = new Todo(taskJson.getDescription(), false);
                }
                uniqueTaskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
        } catch (TaskException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return uniqueTaskList;
    }

    private static class TaskJson {
        private String type;
        private String description;
        private String by;

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        public String getBy() {
            return by;
        }
    }
}
