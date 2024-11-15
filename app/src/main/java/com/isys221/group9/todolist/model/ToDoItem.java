package com.isys221.group9.todolist.model;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import java.io.Serializable;

public class ToDoItem implements Serializable {
    private final String id;
    private String name;
    private String date;
    private boolean completed; // Add a completed field

    public ToDoItem(String name, String date) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.date = date;
        this.completed = false; // Default to not completed
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String serialize() {
        return id + "|" + name + "|" + date;
    }

    public static ToDoItem deserialize(String data) {
        try {
            String[] parts = data.split("\\|", -1);
            if (parts.length == 3) {
                return new ToDoItem(parts[1], parts[2]);
            }
        } catch (Exception e) {
            Log.e("ToDoItem", "Failed to deserialize ToDoItem: " + data, e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id='" + id + '\'' +
                ", title='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String formattedDate(Context context) {
        try {
            java.text.DateFormat isoFormat = new java.text.SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
            Date parsedDate = isoFormat.parse(date);

            java.text.DateFormat displayFormat = DateFormat.getMediumDateFormat(context);
            return displayFormat.format(parsedDate);
        } catch (java.text.ParseException e) {
            Log.e("ToDoItem", "Error parsing date: " + date, e);
            return date;
        }
    }
}
