package com.blaqbox.taskmanager.Commands;

import com.blaqbox.taskmanager.TaskManager;
import picocli.CommandLine.*;

@Command(name = "add", description = "Adds a new task.")
public class AddCommand implements Runnable{
    @Parameters(index = "0", description = "The task description")
    private String description;

    @Override
    public void run() {
        TaskManager taskManager = TaskManager.getInstance();
        boolean isAdded = taskManager.addTask(description);
        if (isAdded){
            System.out.println("Added Successfully!\n");
        }
    }
}
