package com.example.habit_forge.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habit_forge.R;
import com.example.habit_forge.model.HabitInfo;

import java.util.List;

public class ListHabitInfoAdapter extends RecyclerView.Adapter<ListHabitInfoAdapter.HabitViewHolder> {

    private final List<HabitInfo> habitList;

    public ListHabitInfoAdapter(List<HabitInfo> habitList) {
        this.habitList = habitList;
    }


    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.habit_info_item, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        // Liaison des données à la vue
        HabitInfo habit = habitList.get(position);
        holder.bind(habit);
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public void updateHabits(List<HabitInfo> habitInfos) {
        habitList.clear();
        habitList.addAll(habitInfos);
        notifyDataSetChanged();
    }

    static class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView habitName;
        TextView daysCount;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.habit_name_textview);
            daysCount = itemView.findViewById(R.id.days_count_textview);
        }

        public void bind(HabitInfo habit) {
            habitName.setText(habit.getName());
            daysCount.setText(habit.getDays() + " days");
        }
    }
}
