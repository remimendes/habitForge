package com.example.habit_forge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.habit_forge.R;
import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.model.Objective;
import com.example.habit_forge.utils.factory.HabitViewModelFactory;
import com.example.habit_forge.view.viewmodel.HabitCreationEditViewModel;
import com.example.habit_forge.view.viewmodel.MainPageViewModel;

public class HabitCreationEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_habit_creation_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView title = findViewById(R.id.title_tv);
        EditText nameEdit = findViewById(R.id.name_edit);
        EditText objectiveNumberEdit = findViewById(R.id.objective_number_edit);
        Spinner spinner = findViewById(R.id.objective_sign_spinner);
        EditText descriptionEdit = findViewById(R.id.description_edit);
        Button sendInfoButton = findViewById(R.id.sendInfoButton);
        Button deleteButton = findViewById(R.id.delete_button);
        Intent intent = getIntent();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        HabitViewModelFactory factory = new HabitViewModelFactory(getApplication(), intent.getIntExtra("habitId", -1));
        HabitCreationEditViewModel viewModel = new ViewModelProvider(this, factory).get(HabitCreationEditViewModel.class);

        sendInfoButton.setText(viewModel.getButtonText());
        title.setText(viewModel.getTitle());
        nameEdit.setText(viewModel.getNameEditText());
        objectiveNumberEdit.setText(viewModel.getObjectiveNumber());
        spinner.setSelection(viewModel.getSpinnerSelection());
        descriptionEdit.setText(viewModel.getDescription());

        sendInfoButton.setOnClickListener(v -> {
            viewModel.buttonPressed(nameEdit.getText().toString(), descriptionEdit.getText().toString(), objectiveNumberEdit.getText().toString(), (String) spinner.getSelectedItem(), getApplicationContext());
            ;
            finish();
        });

        deleteButton.setOnClickListener(v -> {
            viewModel.deleteButtonPressed(getApplicationContext());
            finish();
        });

        viewModel.getIsButtonVisible().observe(this, isVisible -> {
            if (isVisible != null) {
                deleteButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            }
        });

    }

}