package com.blaqbox.taskmanager;

import com.blaqbox.taskmanager.Entities.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        String filename = "./array.json";
        File file = new File(filename);

        if (!file.exists()){
            System.out.println("This file does not exist.");

            try {
                Thread.sleep(2000);
                System.out.println("Creating new file");
                Thread.sleep(1000);
                file.createNewFile();
//                Make it an empty json Array

            } catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("File Found @ " + file.getAbsolutePath());
        }

       try {
           FileWriter writer = new FileWriter(file);

           ArrayList<Task> tasks = new ArrayList<>();
           tasks.add(new Task("Something to do"));
           tasks.add(new Task("Another thing to do"));
           tasks.add(new Task("This one is already done", Task.Status.COMPLETED));
           writer.write(gson.toJson(tasks));

           writer.close();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

//       READ TASKS
        ArrayList<Task> fileTasks = new ArrayList<>();
        Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
        FileReader reader = new FileReader(filename);
        fileTasks = gson.fromJson(reader, listType);
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Task t: fileTasks){
           System.out.println(t.getDescription());
       }

    }
}
