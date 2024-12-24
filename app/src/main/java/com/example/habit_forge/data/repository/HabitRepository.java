package com.example.habit_forge.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.data.db.HabitForgeRoomDatabase;
import com.example.habit_forge.data.dao.HabitDAO;

import java.util.List;

public class HabitRepository {
    private final HabitDAO habitDAO;
    private final MediatorLiveData<List<HabitEntity>> liveHabitEntityList;

    public HabitRepository(Application application) {
        // Initialisation de la base de données et du DAO
        HabitForgeRoomDatabase db = HabitForgeRoomDatabase.getDatabaseFromJava(application);
        this.habitDAO = db.habitDAO();

        // Initialise MediatorLiveData
        this.liveHabitEntityList = new MediatorLiveData<>();

        // Ajout de la source de Room
        LiveData<List<HabitEntity>> daoLiveData = habitDAO.getAllHabits();
        liveHabitEntityList.addSource(daoLiveData, liveHabitEntityList::setValue);
    }

    // Retourner MediatorLiveData pour que les observateurs puissent s'abonner
    public LiveData<List<HabitEntity>> getLiveHabitEntityList() {
        return liveHabitEntityList;
    }

    // Méthode pour forcer une mise à jour
    public void refreshData() {
        if (liveHabitEntityList.getValue() != null) {
            liveHabitEntityList.setValue(liveHabitEntityList.getValue());
        }
    }

    // Récupérer une seule entité Habit par ID
    public HabitEntity getHabitEntityInfo(int id) {
        return habitDAO.getHabitById(id);
    }

    // Récupérer le nom d'une habitude par ID
    public String getHabitName(int id) {
        return habitDAO.getHabitName(id);
    }

    // Insérer une habitude
    public void insertHabit(HabitEntity habitEntity) {
        HabitForgeRoomDatabase.databaseWriteExecutor.execute(() -> {
            habitDAO.insertHabit(habitEntity);
        });
    }

    // Mettre à jour une habitude
    public void updateHabit(HabitEntity habitEntity) {
        HabitForgeRoomDatabase.databaseWriteExecutor.execute(() -> {
            habitDAO.updateHabit(habitEntity);
        });
    }

    // Supprimer une habitude
    public void deleteHabit(HabitEntity habit) {
        HabitForgeRoomDatabase.databaseWriteExecutor.execute(() -> {
            habitDAO.deleteHabit(habit);
        });
    }
}
