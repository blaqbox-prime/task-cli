package com.blaqbox.taskmanager.Commands;

import com.blaqbox.taskmanager.Entities.Task;
import com.blaqbox.taskmanager.TaskManager;
import picocli.CommandLine.*;

@Command(name = "mark", description = "Mark A task as complete, in_progress or cancelled")
public class MarkCommand implements Runnable{
    @Parameters(index = "0", description = "ID of the task")
    private int id;

    @Option(names = {"-s", "--status"}, description = "todo, in_progress, completed, cancelled")
    private Task.Status status;

    @Override
    public void run() {
        TaskManager taskManager = TaskManager.getInstance();
        boolean isMarked = taskManager.mark(id, status);
        if (isMarked){
            System.out.println("Task successfully marked as " + status.toString());
        }
    }

}
