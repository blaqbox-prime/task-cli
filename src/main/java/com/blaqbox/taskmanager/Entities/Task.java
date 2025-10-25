package com.blaqbox.taskmanager.Entities;

import com.google.gson.annotations.SerializedName;

public class Task {

    static private int latestTaskID = 0;

    private int id;
    private String description;
    private Status status;

    public Task(String description, Status status) {
        this.id = ++latestTaskID;
        this.description = description;
        this.status = status;
    }

    public Task(String description) {
        this.id = ++latestTaskID;
        this.description = description;
        this.status = Status.TODO;
    }

    public static int getLatestTaskID() {
        return latestTaskID;
    }

    public static void setLatestTaskID(int latestTaskID) {
        Task.latestTaskID = latestTaskID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  id +
                " | " + description +
                " | " + status;
    }

    public enum Status {
        @SerializedName("todo")
        TODO,
        @SerializedName("in_progress")
        IN_PROGRESS,
        @SerializedName("completed")
        COMPLETED,
        @SerializedName("cancelled")
        CANCELLED
    }
}


