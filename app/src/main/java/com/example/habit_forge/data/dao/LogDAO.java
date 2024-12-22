package com.example.habit_forge.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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

}
