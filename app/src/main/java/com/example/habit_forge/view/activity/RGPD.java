package com.example.habit_forge.view.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.habit_forge.R;
import com.example.habit_forge.view.viewmodel.RGPDViewModel;

public class RGPD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rgpd);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RGPDViewModel viewModel = new ViewModelProvider(this).get(RGPDViewModel.class);
        findViewById(R.id.send_rgpd_button).setOnClickListener(v -> viewModel.buttonPressed(((EditText) findViewById(R.id.rgpd_days_editTextNumberDecimal)).getText().toString(), this));

    }
}