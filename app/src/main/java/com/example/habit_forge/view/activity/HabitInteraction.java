package com.example.habit_forge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.habit_forge.R;
import com.example.habit_forge.view.viewmodel.HabitInteractionViewModel;

public class HabitInteraction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_habit_interaction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button plusButton = findViewById(R.id.add_button);
        Button minusButton = findViewById(R.id.subtract_button);
        TextView habitName = findViewById(R.id.habit_name_textName);
        TextView quantity = findViewById(R.id.quantity_textview);
        EditText recurrence = findViewById(R.id.recurrence_editTextNumberDecimal);
        Intent intent = getIntent();

        HabitInteractionViewModel viewModel = new HabitInteractionViewModel(getApplication(), intent.getIntExtra("habitId", -1));

        habitName.setText(viewModel.getTitle());
        viewModel.getQuantity().observe(this, quantityValue -> {
            if (quantityValue != null) {
                quantity.setText(String.valueOf(quantityValue));
            } else {
                quantity.setText("0");
            }
        });
        plusButton.setOnClickListener(v -> viewModel.plusButtonPressed(recurrence.getText().toString()));
        minusButton.setOnClickListener(v -> viewModel.minusButtonPressed(recurrence.getText().toString()));

    }
}