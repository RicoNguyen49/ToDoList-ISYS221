package com.isys221.group9.todolist.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isys221.group9.todolist.model.ToDoItem;
import com.isys221.group9.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<ToDoItem> toDoList;
    private final Context context;
    private final OnItemLongClickListener longClickListener;

    public ToDoAdapter(ToDoListActivity context, List<ToDoItem> toDoList, OnItemLongClickListener longClickListener) {
        this.context = context;
        this.toDoList = toDoList != null ? toDoList : new ArrayList<>();
        this.longClickListener = longClickListener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(ToDoItem item);
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView dateTextView;
        private final CheckBox checkBoxComplete;

        public ToDoViewHolder(@NonNull View itemView, final OnItemLongClickListener longClickListener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            checkBoxComplete = itemView.findViewById(R.id.checkBoxComplete);

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                    longClickListener.onItemLongClick((ToDoItem) v.getTag());
                    return true;
                }
                return false;
            });

            checkBoxComplete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ToDoItem item = toDoList.get(position);
                    item.setCompleted(checkBoxComplete.isChecked());
                    notifyItemChanged(position);
                }
            });
        }

        public void bind(ToDoItem item) {
            nameTextView.setText(item.getName());

            String formattedDate = item.formattedDate(context);
            dateTextView.setText(formattedDate);
            checkBoxComplete.setChecked(item.isCompleted());
            itemView.setTag(item);
        }
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        return new ToDoViewHolder(view, longClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDoItem item = toDoList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public void setToDoList(List<ToDoItem> newToDoList) {
        this.toDoList = newToDoList != null ? newToDoList : new ArrayList<>();
        notifyDataSetChanged();
    }
}
