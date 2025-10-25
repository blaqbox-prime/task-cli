package com.blaqbox.taskmanager.Commands;

import com.blaqbox.taskmanager.Entities.Task;
import com.blaqbox.taskmanager.Enums.Filter;
import com.blaqbox.taskmanager.TaskManager;
import picocli.CommandLine.*;

@Command(name = "list", description = "Lists all todo items")
public class ListCommand implements Runnable{
    @Parameters(index = "0", description = "Filter todo list by status", defaultValue = "ALL")
    Filter filter = Filter.ALL;


    @Override
    public void run() {
        TaskManager taskManager = TaskManager.getInstance();
        if (filter == Filter.ALL){
            taskManager.getTasksByStatus(null).forEach(System.out::println);
        } else {
            taskManager.getTasksByStatus(filter).forEach(System.out::println);
        }

    }
}
