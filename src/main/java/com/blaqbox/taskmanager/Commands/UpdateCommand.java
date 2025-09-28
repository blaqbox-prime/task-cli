package com.blaqbox.taskmanager.Commands;

import com.blaqbox.taskmanager.TaskManager;
import picocli.CommandLine.*;

@Command(name = "update", description = "Updates the description of a task")
public class UpdateCommand implements Runnable{
    @Parameters(index = "0", description = "task id")
    private int id;
    @Parameters(index = "1", description = "description")
    private String description;

    @Override
    public void run() {
        TaskManager taskManager = TaskManager.getInstance();

        if (description.isBlank() || description.isEmpty()){
            System.out.println("ID and Description cannot be empty");
        }

        boolean isUpdated = taskManager.update(id, description);
        if (isUpdated){
            System.out.println("Task Updated successfully");
        }
    }
}
