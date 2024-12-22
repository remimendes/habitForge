package com.example.habit_forge.view.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.habit_forge.data.repository.RGPDRepository;

public class RGPDViewModel extends AndroidViewModel {
    private final RGPDRepository RGPDRepository;

    public RGPDViewModel(@NonNull Application application) {
        super(application);
        this.RGPDRepository = new RGPDRepository(application);
    }


    public void buttonPressed(String days, Context context) {
        int daysInt;
        try {
            daysInt = Integer.parseInt(days);
            if (daysInt < 1) {
                throw new NumberFormatException("Days cannot be under 1");
            }
            RGPDRepository.saveDaysBeforeDeletion(daysInt);
        }catch (Exception e) {
            Toast.makeText(context, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
