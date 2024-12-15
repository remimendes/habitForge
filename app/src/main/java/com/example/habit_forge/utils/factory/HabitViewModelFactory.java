package com.example.habit_forge.utils.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.habit_forge.view.viewmodel.HabitCreationEditViewModel;

public class HabitViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final int habitId;

    public HabitViewModelFactory(Application application, int habitId) {
        this.application = application;
        this.habitId = habitId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HabitCreationEditViewModel.class)) {
            return (T) new HabitCreationEditViewModel(application, habitId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

