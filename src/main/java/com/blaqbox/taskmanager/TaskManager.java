package com.blaqbox.taskmanager;

import com.blaqbox.taskmanager.Entities.Task;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static final Gson gson = new Gson();
    private List<Task> tasks;
    private static final String filePath = "tasks.json";
    private static TaskManager instance = null;


    public static TaskManager getInstance() {

        if (instance != null){
            return instance;
        }

//        Try and load tasks from JSON object to ArrayList<>
        ArrayList<Task> existingTasks = loadFromJsonFile(filePath);

        instance = new TaskManager();
        instance.setTasks(existingTasks);

        return instance;
    }

    private void setTasks(List<Task> tasks) {
        instance.tasks = tasks;
    }

    static ArrayList<Task> loadFromJsonFile(String filePath) {
        File file = new File(filePath);
        try {
            FileReader reader = new FileReader(file);

            Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
            return gson.fromJson(reader, listType);

        }catch (FileNotFoundException e){
            try {
                System.out.println("Persistence file not found...");
                Thread.sleep(500);
                System.out.println("Attempting to create new file: tasks.json....");
                Thread.sleep(500);

//                Create new file
                boolean isCreated = file.createNewFile();
                if (isCreated){
                    System.out.println("File storage created, You may start creating your tasks using task-cli");
                } else {
                    System.out.println("Failed to create file storage, Any additions or changes to your tasks list will not be saved");
                }

                try (FileWriter writer = new FileWriter(file)){
                    writer.write(gson.toJson(new ArrayList<Task>()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                return new ArrayList<>();

            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean addTask(String description){
        if(!description.isEmpty() & !description.isBlank()){
            tasks.add(new Task(description));
            return true;
        }
        System.out.println("Description cannot be empty or Blank (only white space)");
        return false;
    }

    public boolean update(int id, String description) {
        if(tasks.isEmpty()){
            return false;
        }

        boolean isFound = false;
        for (Task task: tasks){
            if (task.getId() == id & (!description.isEmpty() & !description.isBlank()) ){
                isFound = true;
                task.setDescription(description);
            }
        }

        return isFound;
    }

    public boolean delete(int id) {
        return id >= 0 && tasks.removeIf(task -> task.getId() == id);
    }

    public boolean mark(int id, Task.Status status) {
        boolean isMarked = false;
        for (Task task: tasks){
            if(task.getId() == id){
                task.setStatus(status);
                isMarked = true;
            }
        }
        return isMarked;
    }

    public boolean save(String filepath) {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.write(gson.toJson(instance.getTasks()));
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Task getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst().orElse(null);
    }
}
