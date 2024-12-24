package com.example.habit_forge.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.habit_forge.data.dao.LogDAO;
import com.example.habit_forge.data.db.HabitForgeRoomDatabase;
import com.example.habit_forge.model.IdQuantity;
import com.example.habit_forge.model.LogEntity;

import java.util.List;

public class LogsRepository {
    private final LogDAO logDAO;

    public LogsRepository(Application application) {
        HabitForgeRoomDatabase db = HabitForgeRoomDatabase.getDatabaseFromJava(application);
        logDAO = db.logDAO();
    }

    public void insertLog(LogEntity log) {
        logDAO.insertLog(log);
    }

    public List<LogEntity> getLogsForHabit(int habitId) {
        return logDAO.getLogsForHabit(habitId);
    }

    public List<LogEntity> getLogsByHabitId(int habitId) {
        return logDAO.getLogsByHabitId(habitId);
    }

    public LiveData<List<IdQuantity>> getQuantityByStartDate(String startDate) {
        return logDAO.getQuantitiesByStartDate(startDate);
    }

    public int getQuantityByStartDate(int habitId,String startDate) {
        return logDAO.getQuantityByStartDate(habitId,startDate);
    }

    public LiveData<Integer> getQuantityLiveDataByStartDate(int habitId, String startDate) {
        return logDAO.getQuantityLiveDataByStartDate(habitId, startDate);
    }
}
