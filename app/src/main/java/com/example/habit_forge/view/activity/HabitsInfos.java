package com.example.habit_forge.view.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habit_forge.R;
import com.example.habit_forge.view.adapter.ListHabitAdapter;
import com.example.habit_forge.view.adapter.ListHabitInfoAdapter;
import com.example.habit_forge.view.viewmodel.HabitInfoViewModel;

import java.util.ArrayList;

public class HabitsInfos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_habits_infos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.list_habits_infos_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListHabitInfoAdapter adapter = new ListHabitInfoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        HabitInfoViewModel viewModel = new ViewModelProvider(this).get(HabitInfoViewModel.class);
        viewModel.getListHabitInfo().observe(this, adapter::updateHabits);
    }
}