package com.example.habit_forge.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habit_forge.R;
import com.example.habit_forge.model.HabitItem;

import java.util.List;

public class ListHabitAdapter extends RecyclerView.Adapter<ListHabitAdapter.ViewHolder> {

    private final List<HabitItem> habitList;
    private final OnButtonClickListener buttonClickListener;
    private final OnItemClickListener itemClickListener;

    public interface OnButtonClickListener {
        void onButtonClick(int habitId);
    }

    public interface OnItemClickListener {
        void onItemClick(int habitId);
    }

    public ListHabitAdapter(List<HabitItem> itemList, OnButtonClickListener listener, OnItemClickListener itemClickListener) {
        this.habitList = itemList;
        this.buttonClickListener = listener;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HabitItem currentItem = habitList.get(position);
        holder.nameTextView.setText(currentItem.getName());
        holder.quantityCount.setText(String.valueOf(currentItem.getQuantity()));

        holder.button.setOnClickListener(v -> {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClick(currentItem.getId());
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(currentItem.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public void updateHabits(List<HabitItem> newHabitList) {
        this.habitList.clear();
        this.habitList.addAll(newHabitList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, quantityCount;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.habit_item_textView);
            quantityCount = itemView.findViewById(R.id.quantity_textview);
            button = itemView.findViewById(R.id.button);
        }
    }
}
