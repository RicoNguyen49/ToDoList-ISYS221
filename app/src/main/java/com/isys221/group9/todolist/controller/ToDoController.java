package com.isys221.group9.todolist.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.isys221.group9.todolist.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoController {
    private static final String PREFS_NAME = "com.isys221.group9.todoappprefs";
    private static final String TODO_KEY = "ToDoList";
    private final SharedPreferences sharedPreferences;
    private final List<ToDoItem> toDoList;

    public ToDoController(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        toDoList = loadToDoList();
    }

    private List<ToDoItem> loadToDoList() {
        List<ToDoItem> list = new ArrayList<>();
        String serializedData = sharedPreferences.getString(TODO_KEY, null);

        if (serializedData != null && !serializedData.isEmpty()) {
            String[] items = serializedData.split(";");

            for (String itemData : items) {
                try {
                    ToDoItem item = ToDoItem.deserialize(itemData);
                    if (item != null) {
                        list.add(item);
                    }
                } catch (Exception e) {
                    Log.e("ToDoController", "Failed to deserialize ToDoItem: " + itemData, e);
                }
            }
        }

        return list;
    }

    public void saveToDoList() {
        StringBuilder serializedData = new StringBuilder();

        for (ToDoItem item : toDoList) {
            serializedData.append(item.serialize()).append(";");
        }

        sharedPreferences.edit().putString(TODO_KEY, serializedData.toString()).apply();
    }

    public void addItem(ToDoItem item) {
        toDoList.add(item);
        saveToDoList();
    }

    public void removeItem(ToDoItem item) {
        toDoList.remove(item);
        saveToDoList();
    }

    public List<ToDoItem> getToDoList() {
        return toDoList;
    }

    public void updateItem(ToDoItem updatedItem) {
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).getId().equals(updatedItem.getId())) {
                toDoList.set(i, updatedItem);
                break;
            }
        }
        saveToDoList();
    }

    public List<ToDoItem> loadSampleToDoList() {
        List<ToDoItem> sampleList = new ArrayList<>();

        sampleList.add(new ToDoItem("Visit the park", "03-12-2025"));
        sampleList.add(new ToDoItem("Organize files", "02-15-2025"));
        sampleList.add(new ToDoItem("Learn coding", "12-23-2024"));
        sampleList.add(new ToDoItem("Try a new recipe", "01-09-2025"));
        sampleList.add(new ToDoItem("Prepare meal", "05-03-2025"));
        sampleList.add(new ToDoItem("Start new project", "10-11-2025"));
        sampleList.add(new ToDoItem("Host dinner", "06-28-2025"));
        sampleList.add(new ToDoItem("Complete homework", "02-06-2025"));
        sampleList.add(new ToDoItem("Complete homework", "11-30-2024"));
        sampleList.add(new ToDoItem("Make doctor's appointment", "12-03-2024"));
        sampleList.add(new ToDoItem("Visit the park", "03-12-2025"));
        sampleList.add(new ToDoItem("Organize files", "02-15-2025"));
        sampleList.add(new ToDoItem("Learn coding", "12-23-2024"));
        sampleList.add(new ToDoItem("Try a new recipe", "01-09-2025"));
        sampleList.add(new ToDoItem("Prepare meal", "05-03-2025"));
        sampleList.add(new ToDoItem("Start new project", "10-11-2025"));
        sampleList.add(new ToDoItem("Host dinner", "06-28-2025"));
        sampleList.add(new ToDoItem("Complete homework", "02-06-2025"));
        sampleList.add(new ToDoItem("Complete homework", "11-30-2024"));
        sampleList.add(new ToDoItem("Make doctor's appointment", "12-03-2024"));
        sampleList.add(new ToDoItem("Check emails", "04-03-2025"));
        sampleList.add(new ToDoItem("Buy gifts", "10-18-2025"));
        sampleList.add(new ToDoItem("Call mom", "09-28-2025"));
        sampleList.add(new ToDoItem("Organize files", "12-08-2024"));
        sampleList.add(new ToDoItem("Review budget", "03-25-2025"));
        sampleList.add(new ToDoItem("Prepare presentation", "10-12-2025"));
        sampleList.add(new ToDoItem("Prepare meal", "01-09-2025"));
        sampleList.add(new ToDoItem("Visit the park", "06-16-2025"));
        sampleList.add(new ToDoItem("Backup files", "09-13-2025"));
        sampleList.add(new ToDoItem("Bake a cake", "12-19-2024"));
        sampleList.add(new ToDoItem("Complete puzzle", "07-30-2025"));
        sampleList.add(new ToDoItem("Refurbish furniture", "02-08-2025"));
        sampleList.add(new ToDoItem("Plan family reunion", "08-26-2025"));
        sampleList.add(new ToDoItem("Renew passport", "06-26-2025"));
        sampleList.add(new ToDoItem("Buy groceries", "08-31-2025"));
        sampleList.add(new ToDoItem("Visit grandparents", "11-25-2024"));
        sampleList.add(new ToDoItem("Clean the house", "03-03-2025"));
        sampleList.add(new ToDoItem("Complete puzzle", "09-06-2025"));
        sampleList.add(new ToDoItem("Meet financial advisor", "02-12-2025"));
        sampleList.add(new ToDoItem("Walk 10,000 steps", "12-30-2024"));
        sampleList.add(new ToDoItem("Wash car", "12-07-2024"));
        sampleList.add(new ToDoItem("Meet financial advisor", "01-05-2025"));
        sampleList.add(new ToDoItem("Declutter desktop", "11-01-2025"));
        sampleList.add(new ToDoItem("Meet financial advisor", "10-19-2025"));
        sampleList.add(new ToDoItem("Read newspaper", "03-14-2025"));
        sampleList.add(new ToDoItem("Study programming", "01-07-2025"));
        sampleList.add(new ToDoItem("Prepare meal", "01-15-2025"));
        sampleList.add(new ToDoItem("Buy groceries", "01-25-2025"));
        sampleList.add(new ToDoItem("Volunteer", "07-15-2025"));
        sampleList.add(new ToDoItem("Plan birthday party", "07-27-2025"));
        sampleList.add(new ToDoItem("Update software", "02-26-2025"));
        sampleList.add(new ToDoItem("Organize closet", "04-23-2025"));
        sampleList.add(new ToDoItem("Renew passport", "03-01-2025"));
        sampleList.add(new ToDoItem("Explore nature", "05-08-2025"));
        sampleList.add(new ToDoItem("Start a blog", "05-18-2025"));
        sampleList.add(new ToDoItem("Write journal", "12-28-2024"));
        sampleList.add(new ToDoItem("Do meditation", "12-07-2024"));
        sampleList.add(new ToDoItem("Visit friend", "06-16-2025"));
        sampleList.add(new ToDoItem("Bake a cake", "03-19-2025"));
        sampleList.add(new ToDoItem("Watch a movie", "02-20-2025"));
        sampleList.add(new ToDoItem("Go to art gallery", "04-03-2025"));
        sampleList.add(new ToDoItem("Go hiking", "07-17-2025"));
        sampleList.add(new ToDoItem("Start a blog", "01-19-2025"));
        sampleList.add(new ToDoItem("Visit friend", "01-08-2025"));
        sampleList.add(new ToDoItem("Call mom", "04-21-2025"));
        sampleList.add(new ToDoItem("Write journal", "09-22-2025"));
        sampleList.add(new ToDoItem("Clean the house", "05-05-2025"));
        sampleList.add(new ToDoItem("Play chess", "03-15-2025"));
        sampleList.add(new ToDoItem("Take dog for walk", "01-30-2025"));
        sampleList.add(new ToDoItem("Meet financial advisor", "05-26-2025"));
        sampleList.add(new ToDoItem("Workout session", "03-18-2025"));
        sampleList.add(new ToDoItem("Read newspaper", "06-28-2025"));
        sampleList.add(new ToDoItem("Shop online", "03-01-2025"));
        sampleList.add(new ToDoItem("Go for a run", "01-23-2025"));
        sampleList.add(new ToDoItem("Refurbish furniture", "11-15-2025"));
        sampleList.add(new ToDoItem("Research stocks", "01-05-2025"));
        sampleList.add(new ToDoItem("Take dog for walk", "04-18-2025"));
        sampleList.add(new ToDoItem("Shop for clothes", "01-22-2025"));
        sampleList.add(new ToDoItem("Plan road trip", "01-08-2025"));
        sampleList.add(new ToDoItem("Cook dinner", "08-10-2025"));
        sampleList.add(new ToDoItem("Read newspaper", "07-03-2025"));
        sampleList.add(new ToDoItem("Visit museum", "11-18-2024"));
        sampleList.add(new ToDoItem("Refinance mortgage", "09-05-2025"));
        sampleList.add(new ToDoItem("Check emails", "08-13-2025"));
        sampleList.add(new ToDoItem("Practice guitar", "12-10-2024"));
        sampleList.add(new ToDoItem("Visit dentist", "02-24-2025"));
        sampleList.add(new ToDoItem("Shop for clothes", "05-27-2025"));
        sampleList.add(new ToDoItem("Volunteer", "03-25-2025"));
        sampleList.add(new ToDoItem("Go fishing", "03-15-2025"));
        sampleList.add(new ToDoItem("Plan vacation", "11-04-2025"));
        sampleList.add(new ToDoItem("Call mom", "05-11-2025"));
        sampleList.add(new ToDoItem("Renew library books", "12-16-2024"));
        sampleList.add(new ToDoItem("Visit dentist", "02-24-2025"));
        sampleList.add(new ToDoItem("Schedule oil change", "03-25-2025"));
        sampleList.add(new ToDoItem("Read financial report", "03-28-2025"));
        sampleList.add(new ToDoItem("Take dog for walk", "03-09-2025"));
        sampleList.add(new ToDoItem("Learn coding", "02-27-2025"));
        sampleList.add(new ToDoItem("Try a new recipe", "02-20-2025"));
        sampleList.add(new ToDoItem("Make doctor's appointment", "12-19-2024"));
        sampleList.add(new ToDoItem("Update software", "04-03-2025"));
        sampleList.add(new ToDoItem("Complete homework", "07-13-2025"));
        sampleList.add(new ToDoItem("Buy groceries", "08-20-2025"));
        sampleList.add(new ToDoItem("Update software", "03-21-2025"));
        sampleList.add(new ToDoItem("Complete homework", "03-09-2025"));
        sampleList.add(new ToDoItem("Water plants", "07-22-2025"));
        sampleList.add(new ToDoItem("Prepare meal", "02-18-2025"));
        sampleList.add(new ToDoItem("Refinance mortgage", "10-19-2025"));
        sampleList.add(new ToDoItem("Plan vacation", "10-21-2025"));
        sampleList.add(new ToDoItem("Start new project", "11-24-2024"));
        sampleList.add(new ToDoItem("Clean fridge", "06-09-2025"));

        return sampleList;
    }

    public ToDoItem findItem(String name, String date) {
        for (ToDoItem item : toDoList) {
            if (item.getName().equals(name) && item.getDate().equals(date)) {
                return item;
            }
        }
        return null;
    }
}
