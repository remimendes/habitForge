package com.example.habit_forge.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.data.db.HabitForgeRoomDatabase;
import com.example.habit_forge.data.dao.HabitDAO;

import java.util.List;

public class HabitRepository {
    private final HabitDAO habitDAO;
    private final LiveData<List<HabitEntity>> liveHabitEntityList;

    public HabitRepository(Application application) {
        HabitForgeRoomDatabase db = HabitForgeRoomDatabase.getDatabaseFromJava(application);
        this.habitDAO = db.habitDAO();
        this.liveHabitEntityList = habitDAO.getAllHabits();
    }

    public LiveData<List<HabitEntity>> getLiveHabitEntityList() {
        return liveHabitEntityList;
    }

    public HabitEntity getHabitEntityInfo(int id) {
        return habitDAO.getHabitById(id);
    }

    public String getHabitName(int id) {
        return habitDAO.getHabitName(id);
    }
    
    public void insertHabit(HabitEntity habitEntity) {
        habitDAO.insertHabit(habitEntity);
    }

    public void updateHabit(HabitEntity habitEntity) {
        habitDAO.updateHabit(habitEntity);
    }

    public void deleteHabit(HabitEntity habit) {
        habitDAO.deleteHabit(habit);
    }
}