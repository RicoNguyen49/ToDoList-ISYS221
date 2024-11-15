package com.isys221.group9.todolist.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.isys221.group9.todolist.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ToDoItemFragment extends Fragment {

    private EditText editTextName;
    private EditText editTextDate;
    private String existingName;
    private String existingDate;

    private final Calendar calendar = Calendar.getInstance();
    private OnToDoItemAddedListener itemAddedListener;

    public interface OnToDoItemAddedListener {
        void onToDoItemAdded(String name, String date);
        void onToDoItemUpdated(String oldName, String oldDate, String newName, String newDate);
    }

    public static ToDoItemFragment newInstance(String name, String date) {
        ToDoItemFragment fragment = new ToDoItemFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("date", date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnToDoItemAddedListener) {
            itemAddedListener = (OnToDoItemAddedListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            existingName = getArguments().getString("name");
            existingDate = getArguments().getString("date");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_item, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextDate = view.findViewById(R.id.editTextDate);
        Button buttonSave = view.findViewById(R.id.buttonSave);

        if (existingName != null) editTextName.setText(existingName);
        if (existingDate != null) editTextDate.setText(existingDate);

        editTextDate.setOnClickListener(v -> showDatePicker());

        // Set up save button to send data back and close the fragment
        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String date = editTextDate.getText().toString();

            if (!name.isEmpty() && !date.isEmpty() && itemAddedListener != null) {
                if (existingName != null && existingDate != null) {
                    // Update the existing item
                    itemAddedListener.onToDoItemUpdated(existingName, existingDate, name, date);
                } else {
                    // Add new item
                    itemAddedListener.onToDoItemAdded(name, date);
                }
            }
        });

        return view;
    }

    private void showDatePicker() {
        new DatePickerDialog(requireContext(), dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private final DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        editTextDate.setText(sdf.format(calendar.getTime()));
    };
}