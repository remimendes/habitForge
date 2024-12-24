package com.example.habit_forge.view.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.habit_forge.data.repository.HabitRepository;
import com.example.habit_forge.data.repository.LogsRepository;
import com.example.habit_forge.data.repository.RGPDRepository;
import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.model.HabitItem;
import com.example.habit_forge.model.IdQuantity;
import com.example.habit_forge.utils.enumeration.NavigationEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainPageViewModel extends AndroidViewModel {
    private final HabitRepository habitRepository;
    private final RGPDRepository rgpdRepository;
    private final LogsRepository logsRepository;
    private final LiveData<List<HabitEntity>> allHabits;
    private final LiveData<List<IdQuantity>> allQuantities;
    private MutableLiveData<NavigationEvent> navigationEvent = new MutableLiveData<>();
    private final MediatorLiveData<List<HabitItem>> habitItemsLiveData = new MediatorLiveData<>();

    public MainPageViewModel(@NonNull Application application) {
        super(application);
        habitRepository = new HabitRepository(application);
        this.rgpdRepository = new RGPDRepository(application);
        this.logsRepository = new LogsRepository(application);
        allHabits = habitRepository.getLiveHabitEntityList();
        allQuantities = logsRepository.getQuantityByStartDate(String.valueOf(LocalDateTime.now().toLocalDate().atStartOfDay()));
    }

    public LiveData<List<HabitItem>> getHabitItemsLiveData() {
        return Transformations.map(allHabits, habitEntities -> {
            // Vérifier si la liste est nulle
            if (habitEntities == null) {
                return null;
            }
            // Mapper chaque HabitEntity à HabitItem
            return habitEntities.stream()
                    .map(habitEntity -> new HabitItem(
                            habitEntity.getId(),
                            logsRepository.getQuantityByStartDate(habitEntity.getId(),String.valueOf(LocalDateTime.now().toLocalDate().atStartOfDay())),
                            habitEntity.getName()
                    ))
                    .collect(Collectors.toList());
        });
    }

    public LiveData<List<IdQuantity>> getAllQuantities() {
        return allQuantities;
    }
/*public void updateHabitItemsLiveData() {
        allHabits.setValue(allHabits.getValue());
    }*/

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
    public void refreshData() {
        habitRepository.refreshData();
    }
    public void onResume() {
        if (rgpdRepository.getDaysBeforeDeletion() == -1) {
            navigationEvent.setValue(new NavigationEvent(NavigationEvent.EventType.RGPD, -1));
        }
    }
}
