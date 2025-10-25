package com.blaqbox.taskmanager;


import com.blaqbox.taskmanager.Commands.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(
        name = "task-cli", mixinStandardHelpOptions = true,
        version = "1.0", description = "A simple command-line task manager",
        subcommands = {
                AddCommand.class,
                ListCommand.class,
                UpdateCommand.class,
                DeleteCommand.class,
                MarkCommand.class,
                HelpCommand.class
        }
)
public class TaskCli implements Runnable {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new TaskCli()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

//        Default action if no command is given
        System.out.println("Use 'task-cli help' to see available commands");
    }
}
