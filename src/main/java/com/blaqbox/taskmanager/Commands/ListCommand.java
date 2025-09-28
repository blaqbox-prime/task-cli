package com.blaqbox.taskmanager.Commands;

import com.blaqbox.taskmanager.Entities.Task;
import com.blaqbox.taskmanager.Enums.Filter;
import com.blaqbox.taskmanager.TaskManager;
import picocli.CommandLine.*;

@Command(name = "list", description = "Lists all todo items")
public class ListCommand implements Runnable{
    @Parameters(index = "0", description = "Filter todo list by status")
    Filter filter = Filter.ALL;


    @Override
    public void run() {
        TaskManager taskManager = TaskManager.getInstance();
        for (Task task: taskManager.getTasks()){
            System.out.println(task);
        }
    }
}
