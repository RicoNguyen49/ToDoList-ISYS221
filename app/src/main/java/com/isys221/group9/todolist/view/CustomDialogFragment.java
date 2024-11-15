package com.isys221.group9.todolist.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.widget.Button;

import com.isys221.group9.todolist.R;

public class CustomDialogFragment extends DialogFragment {

    private final OnDialogButtonClickListener listener;

    public interface OnDialogButtonClickListener {
        void onEditClicked();
        void onDeleteClicked();
    }

    public CustomDialogFragment(OnDialogButtonClickListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_buttons, container, false);

        Button editButton = view.findViewById(R.id.button_edit);
        Button deleteButton = view.findViewById(R.id.button_delete);
        Button cancelButton = view.findViewById(R.id.button_cancel);

        editButton.setOnClickListener(v -> {
            if (listener != null) listener.onEditClicked();
            dismiss();
        });

        deleteButton.setOnClickListener(v -> {
            if (listener != null) listener.onDeleteClicked();
            dismiss();
        });

        cancelButton.setOnClickListener(v -> dismiss());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
