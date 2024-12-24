package com.example.habit_forge.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.habit_forge.model.IdQuantity;
import com.example.habit_forge.model.LogEntity;

import java.util.List;

@Dao
public interface LogDAO {
    @Insert
    public void insertLog(LogEntity log);

    @Query("SELECT * FROM logs_table WHERE habitId = :habitId")
    public List<LogEntity> getLogsForHabit(int habitId);

    @Query("SELECT * FROM logs_table WHERE habitId = :habitId ORDER BY dateTime ASC")
    public List<LogEntity> getLogsByHabitId(int habitId);

    @Query("SELECT habitId, SUM(quantity) AS totalQuantity FROM logs_table WHERE dateTime >= :startDate GROUP BY habitId ORDER BY habitId ASC")
    LiveData<List<IdQuantity>> getQuantitiesByStartDate(String startDate);

    @Query("SELECT COALESCE(SUM(quantity), 0) AS totalQuantity\n FROM logs_table WHERE dateTime >= :startDate AND habitId = :habitId")
    int getQuantityByStartDate(int habitId, String startDate);
}
