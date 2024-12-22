package com.example.habit_forge.view.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.habit_forge.data.repository.HabitRepository;
import com.example.habit_forge.data.repository.RGPDRepository;
import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.utils.enumeration.NavigationEvent;

import java.util.List;

public class MainPageViewModel extends AndroidViewModel {
    private final HabitRepository habitRepository;
    private final RGPDRepository rgpdRepository;
    private final LiveData<List<HabitEntity>> allHabits;
    private MutableLiveData<NavigationEvent> navigationEvent = new MutableLiveData<>();

    public MainPageViewModel(@NonNull Application application) {
        super(application);
        habitRepository = new HabitRepository(application);
        this.rgpdRepository = new RGPDRepository(application);
        allHabits = habitRepository.getLiveHabitEntityList();
    }

    public LiveData<List<HabitEntity>> getAllHabits() {
        return allHabits;
    }

    public LiveData<NavigationEvent> getNavigationEvent() {
        return navigationEvent;
    }

    public void onNavigateToHabitCreationActivity() {
        navigationEvent.setValue(new NavigationEvent(NavigationEvent.EventType.Creation, -1));
    }

    public void onNavigateToEditCreationActivity(int habitId) {
        navigationEvent.setValue(new NavigationEvent(NavigationEvent.EventType.Edit, habitId));
    }

    public void onNavigateToHabitInteractionActivity(int habitId) {
        navigationEvent.setValue(new NavigationEvent(NavigationEvent.EventType.Interaction, habitId));
    }

    public void onNavigateToHabitInfosActivity() {
        navigationEvent.setValue(new NavigationEvent(NavigationEvent.EventType.Infos, -1));
    }

    public void onNaviagteToRGPDActivity() {
        navigationEvent.setValue(new NavigationEvent(NavigationEvent.EventType.RGPD, -1));
    }

    public void onResume() {
        if (rgpdRepository.getDaysBeforeDeletion() == -1) {
            navigationEvent.setValue(new NavigationEvent(NavigationEvent.EventType.RGPD, -1));
        }
    }
}
