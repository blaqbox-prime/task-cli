package com.blaqbox.taskmanager;

import com.blaqbox.taskmanager.Entities.Task;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager taskManager;
    private String filePath = "testingTasks.json";

    @BeforeEach
    void setUp() {
        taskManager = TaskManager.getInstance();
    }

    @AfterEach
    void tearDown() {
        taskManager.getTasks().clear();
    }

    @Test
    void loadsJSONArraySuccessfully() {
        Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
        assertInstanceOf(ArrayList.class, TaskManager.loadFromJsonFile(filePath));
    }

    @Test
    void getTasksReturnsEmptyListForEmptyTaskManager() {
        assertTrue(taskManager.getTasks().isEmpty());
    }

    @Test
    void addTaskWithValidDetails() {
        int initialCount = taskManager.getTasks().size();
        boolean result = taskManager.addTask("Testing new Task");
        assertEquals(initialCount + 1, taskManager.getTasks().size());
        assertTrue(result);
    }

    @Test
    void addTaskWithNoDescriptionReturnsFalseAndDoesNotAddToList() {
        int initialCount = taskManager.getTasks().size();
        boolean result = taskManager.addTask("");
        assertEquals(initialCount, taskManager.getTasks().size());
        assertFalse(result);
    }

    @Test
    void creatingTaskWithValidDescriptionDefaultsStatusToTODO() {
        Task task = new Task("This is a new task");
        assertEquals(Task.Status.TODO, task.getStatus());
    }


    @Test
    void updateTaskDescriptionWithValidNewDescriptionReturnsTrue() {
        Task existing = new Task("Existing thing to do");
        taskManager.getTasks().add(existing);
        String newDescription = "Newly Updated Task";
        boolean result = taskManager.update(existing.getId(), newDescription);
        assertTrue(result);
        assertEquals(newDescription,taskManager.getTaskById(existing.getId()).getDescription());
    }

    @Test
    void updateTaskDescriptionWithEmptyNewDescriptionReturnsFalse() {
        Task existing = new Task("Existing thing to do");
        taskManager.getTasks().add(existing);
        int initialTaskCount = taskManager.getTasks().size();
        String newDescription = "";
        boolean result = taskManager.update(existing.getId(), newDescription);
        assertFalse(result);
        assertEquals(existing.getDescription(), taskManager.getTaskById(existing.getId()).getDescription());
        assertEquals(initialTaskCount, taskManager.getTasks().size());
    }

    @Test
    void updateTaskDescriptionWithInvalidIDReturnsFalse() {
        Task existing = new Task("Existing thing to do");
        taskManager.getTasks().add(existing);
        int initialTaskCount = taskManager.getTasks().size();
        String newDescription = "This is a valid updated description";
        boolean result = taskManager.update(999, newDescription); //non-existent ID
        assertFalse(result);
        assertEquals(existing.getDescription(), taskManager.getTaskById(existing.getId()).getDescription());
        assertEquals(initialTaskCount, taskManager.getTasks().size());
    }



    @Test
    void deleteTaskWithValidIDReturnsTrue() {
        Task task1 = new Task("Task 1");
        taskManager.getTasks().add(task1);
        int initialTaskCount = taskManager.getTasks().size();

        boolean result = taskManager.delete(task1.getId());
        assertTrue(result);
        assertEquals(initialTaskCount - 1, taskManager.getTasks().size());

    }

    @Test
    void deleteTaskWithIDNotInTheTaskListReturnsFalse() {
        Task task1 = new Task("Task 1");
        taskManager.getTasks().add(task1);
        int initialTaskCount = taskManager.getTasks().size();

        boolean result = taskManager.delete(999);
        assertFalse(result);
        assertEquals(initialTaskCount, taskManager.getTasks().size());
    }

    @Test
    void deleteTaskWithANegativeIDReturnsFalse() {
        Task task1 = new Task("Task 1");
        taskManager.getTasks().add(task1);
        int initialTaskCount = taskManager.getTasks().size();

        boolean result = taskManager.delete(-1);
        assertFalse(result);
        assertEquals(initialTaskCount, taskManager.getTasks().size());
    }



    @Test
    void markAsInProgress() {
        Task task1 = new Task("Task 1");
        taskManager.getTasks().add(task1);
        boolean result = taskManager.mark(task1.getId(), Task.Status.IN_PROGRESS);
        assertTrue(result);
        assertEquals(Task.Status.IN_PROGRESS,taskManager.getTaskById(task1.getId()).getStatus());
    }

    @Test
    void markAsDone() {
        Task task1 = new Task("Task 1");
        taskManager.getTasks().add(task1);
        boolean result = taskManager.mark(task1.getId(), Task.Status.COMPLETED);
        assertTrue(result);
        assertEquals(Task.Status.COMPLETED,taskManager.getTaskById(task1.getId()).getStatus());
    }

    @Test
    void markAsCancelled() {
        Task task1 = new Task("Task 1");
        taskManager.getTasks().add(task1);
        boolean result = taskManager.mark(task1.getId(), Task.Status.CANCELLED);
        assertTrue(result);
        assertEquals(Task.Status.CANCELLED,taskManager.getTaskById(task1.getId()).getStatus());
    }

    @Test
    void markAsTODO() {
        Task task1 = new Task("Task 1");
        taskManager.getTasks().add(task1);
        boolean result = taskManager.mark(task1.getId(), Task.Status.TODO);
        assertTrue(result);
        assertEquals(Task.Status.TODO,taskManager.getTaskById(task1.getId()).getStatus());
    }

    @Test
    void saveTodosUpdatesTheJSONFile() {
        Task task1 = new Task("Task 1 Saved");
        Task task2 = new Task("Task 2 Saved");
        Task task3 = new Task("Task 3 Saved");
        taskManager.getTasks().add(task1);
        taskManager.getTasks().add(task2);
        taskManager.getTasks().add(task3);

        boolean result = taskManager.save(filePath);
        ArrayList<Task> savedTasks = TaskManager.loadFromJsonFile(filePath);

        assertTrue(result);
        assertEquals(taskManager.getTasks().size(), savedTasks.size());
    }

}