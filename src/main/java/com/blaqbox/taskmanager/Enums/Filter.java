package com.blaqbox.taskmanager.Enums;

import com.google.gson.annotations.SerializedName;

public enum Filter {
    ALL,
    @SerializedName("todo")
    TODO,
    @SerializedName("in_progress")
    IN_PROGRESS,
    @SerializedName("completed")
    COMPLETED,
    @SerializedName("cancelled")
    CANCELLED
}
