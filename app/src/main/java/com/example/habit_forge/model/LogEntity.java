package com.example.habit_forge.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "logs_table", foreignKeys = @ForeignKey(
        entity = HabitEntity.class,
        parentColumns = "id",
        childColumns = "habitId",
        onDelete = ForeignKey.CASCADE
))
public class LogEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    private int habitId;
    private LocalDateTime dateTime;
    private int quantity;

    public LogEntity(int habitId, LocalDateTime dateTime, int quantity) {
        this.habitId = habitId;
        this.dateTime = dateTime;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getHabitId() {
        return habitId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
