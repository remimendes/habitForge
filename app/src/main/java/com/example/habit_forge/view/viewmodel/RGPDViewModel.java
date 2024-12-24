package com.example.habit_forge.view.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.habit_forge.data.repository.RGPDRepository;

import java.util.Locale;

public class RGPDViewModel extends AndroidViewModel {
    private final RGPDRepository RGPDRepository;
    private final MutableLiveData<String> RGPDTRetentionDaysText;

    public RGPDViewModel(@NonNull Application application) {
        super(application);
        this.RGPDRepository = new RGPDRepository(application);
        this.RGPDTRetentionDaysText = new MutableLiveData<>();
        this.RGPDTRetentionDaysText.setValue(getDaysBeforeDeletionText());
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
        RGPDTRetentionDaysText.setValue(getDaysBeforeDeletionText());
    }

    public String getDaysBeforeDeletionText() {
        return String.format(Locale.ROOT, "days before deletetion is set to %d days", RGPDRepository.getDaysBeforeDeletion());
    }

    public LiveData<String> getDaysBeforeDeletionTextLiveData() {
        return RGPDTRetentionDaysText;
    }
}
