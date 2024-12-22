package com.example.habit_forge.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.habit_forge.model.Objective;

import java.time.LocalDateTime;

@Entity(
        tableName = "habits_table",
        indices = {@Index(value = "name", unique = true)} // Index unique sur "name"
)
public class HabitEntity {

    @PrimaryKey(autoGenerate = true)
    private int id; // Clé primaire auto-générée

    @NonNull
    private String name; // Colonne avec contrainte UNIQUE

    private String description;

    @Embedded
    private Objective objective;

    private LocalDateTime creationDate;

    public HabitEntity(@NonNull String name, String description, Objective objective, LocalDateTime creationDate) {
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.creationDate = creationDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
