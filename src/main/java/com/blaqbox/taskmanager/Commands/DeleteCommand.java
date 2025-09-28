package com.blaqbox.taskmanager.Commands;

import com.blaqbox.taskmanager.TaskManager;
import picocli.CommandLine.*;

@Command(name = "delete", description = "Delete a task by its ID")
public class DeleteCommand implements Runnable {
    @Parameters(index = "0", description = "Task id")
    private int id;

    @Override
    public void run() {
        TaskManager taskManager = TaskManager.getInstance();
        boolean isDeleted = taskManager.delete(id);
        if (isDeleted){
            System.out.println("Task Deleted");
        }
    }
}
