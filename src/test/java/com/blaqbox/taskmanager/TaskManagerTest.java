package com.blaqbox.taskmanager;

import com.blaqbox.taskmanager.Entities.Task;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager taskManager;
    private String filePath = "tasks.json";

    @BeforeEach
    void setUp() {
        taskManager = TaskManager.getInstance();
    }

    @AfterEach
    void tearDown() {
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

//    @Test
//    void addTask() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void mark() {
//    }
}