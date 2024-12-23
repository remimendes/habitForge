package com.example.habit_forge.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.habit_forge.model.HabitEntity;

import java.util.List;

@Dao
public interface HabitDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertHabit(HabitEntity habit);

    @Update
    void updateHabit(HabitEntity habit);

    @Delete
    void deleteHabit(HabitEntity habit);

    @Query("SELECT * FROM habits_table")
    LiveData<List<HabitEntity>> getAllHabits();

    @Query("SELECT * FROM habits_table WHERE id = :id")
    HabitEntity getHabitById(int id);

    @Query("SELECT name FROM habits_table WHERE id = :id")
    String getHabitName(int id);


}
