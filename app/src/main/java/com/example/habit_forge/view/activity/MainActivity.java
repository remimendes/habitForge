package com.example.habit_forge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habit_forge.R;
import com.example.habit_forge.utils.enumeration.NavigationEvent;
import com.example.habit_forge.view.adapter.ListHabitAdapter;
import com.example.habit_forge.view.viewmodel.MainPageViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MainPageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.list_habits_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListHabitAdapter adapter = new ListHabitAdapter(new ArrayList<>(), new ListHabitAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int habitId) {
                viewModel.onNavigateToEditCreationActivity(habitId);
            }
        }, new ListHabitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int habitId) {
                viewModel.onNavigateToHabitInteractionActivity(habitId);
            }
        });
        recyclerView.setAdapter(adapter);

        viewModel.getHabitItemsLiveData().observe(this, adapter::updateHabits);

        viewModel.getAllQuantities().observe(this, idQuantities -> {
            viewModel.refreshData();
        });

        Button addHabitBtn = findViewById(R.id.add_habit_button);
        addHabitBtn.setOnClickListener(v -> viewModel.onNavigateToHabitCreationActivity());

        Button habitsInfosBtn = findViewById(R.id.habits_infos_button);
        habitsInfosBtn.setOnClickListener(v -> viewModel.onNavigateToHabitInfosActivity());

        Button RGPDBtn = findViewById(R.id.RGPD_button);
        RGPDBtn.setOnClickListener(v -> viewModel.onNaviagteToRGPDActivity());

        viewModel.getNavigationEvent().observe(this, new Observer<NavigationEvent>() {
            @Override
            public void onChanged(NavigationEvent event) {
                if (event == null) return;
                Intent intent;
                switch (event.getEventType()) {
                    case Creation:
                        intent = new Intent(MainActivity.this, HabitCreationEdit.class);
                        startActivity(intent);
                        break;
                    case Edit :
                        intent = new Intent(MainActivity.this, HabitCreationEdit.class);
                        intent.putExtra("habitId", event.getId());
                        startActivity(intent);
                        break;
                    case Interaction:
                        intent = new Intent(MainActivity.this, HabitInteraction.class);
                        intent.putExtra("habitId", event.getId());
                        startActivity(intent);
                        break;
                    case Infos:
                        intent = new Intent(MainActivity.this, HabitsInfos.class);
                        startActivity(intent);
                        break;
                    case RGPD:
                        intent = new Intent(MainActivity.this, RGPD.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}