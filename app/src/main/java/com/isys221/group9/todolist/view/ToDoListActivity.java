package com.isys221.group9.todolist.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.isys221.group9.todolist.controller.ToDoController;
import com.isys221.group9.todolist.model.ToDoItem;
import com.isys221.group9.todolist.R;

import java.util.List;

public class ToDoListActivity extends AppCompatActivity implements ToDoItemFragment.OnToDoItemAddedListener{
    private ToDoAdapter adapter;
    private ToDoController toDoController;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        toDoController = new ToDoController(this);
        fabAdd = findViewById(R.id.fab_add);

        if (toDoController.getToDoList().isEmpty()) {
            insertSamples();
        }

        setupRecyclerView();
        setupFloatingActionButton();
    }

    private void insertSamples() {
        List<ToDoItem> sampleItems = toDoController.loadSampleToDoList();
        for (ToDoItem item : sampleItems) {
            toDoController.addItem(item);
        }
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ToDoAdapter(this, toDoController.getToDoList(),
                this::showItemOptionsDialog
        );

        recyclerView.setAdapter(adapter);
    }

    private void showItemOptionsDialog(ToDoItem item) {
        CustomDialogFragment dialog = new CustomDialogFragment(new CustomDialogFragment.OnDialogButtonClickListener() {
            @Override
            public void onEditClicked() {
                fabAdd.setVisibility(View.GONE);
                ToDoItemFragment fragment = ToDoItemFragment.newInstance(item.getName(), item.getDate());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root_layout, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onDeleteClicked() {
                toDoController.removeItem(item);
                refreshToDoList();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        dialog.show(fragmentManager, "CustomDialog");
    }

    private void setupFloatingActionButton() {
        fabAdd.setOnClickListener(v -> {

            fabAdd.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root_layout, new ToDoItemFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshToDoList();
    }

    private void refreshToDoList() {
        List<ToDoItem> updatedList = toDoController.getToDoList();
        adapter.setToDoList(updatedList);
    }

    @Override
    public void onToDoItemAdded(String name, String date) {
        ToDoItem newItem = new ToDoItem(name, date);
        toDoController.addItem(newItem);
        adapter.notifyItemInserted(toDoController.getToDoList().size() - 1);

        getSupportFragmentManager().popBackStack();
        fabAdd.setVisibility(View.VISIBLE);
    }

    @Override
    public void onToDoItemUpdated(String oldName, String oldDate, String newName, String newDate) {
        ToDoItem item = toDoController.findItem(oldName, oldDate);
        if (item != null) {
            item.setName(newName);
            item.setDate(newDate);
            toDoController.updateItem(item);
            adapter.notifyDataSetChanged();
        }
        getSupportFragmentManager().popBackStack();
        fabAdd.setVisibility(View.VISIBLE);
    }
}
