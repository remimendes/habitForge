package com.example.habit_forge.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.habit_forge.model.LogEntity;

import java.util.List;

@Dao
public interface LogDAO {
    @Insert
    public void insertLog(LogEntity log);
}
