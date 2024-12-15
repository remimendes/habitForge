package com.example.habit_forge.view.viewmodel;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.habit_forge.data.repository.HabitRepository;
import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.model.Objective;
import com.example.habit_forge.utils.enumeration.ObjectiveSign;

public class HabitCreationEditViewModel extends AndroidViewModel {
    private HabitEntity currentHabitEntity;
    private HabitRepository habitRepository;

    public HabitCreationEditViewModel(@NonNull Application application, int habitId) {
        super(application);
        habitRepository = new HabitRepository(application);
        currentHabitEntity = habitRepository.getHabitEntityInfo(habitId);
    }

    public String getButtonText() {
        if (currentHabitEntity == null) {
            return "Create";
        }
        return "Edit";
    }

    public void buttonPressed(String habitName, String description, String recurrence, String sign, Context context) {
        Objective objective;
        try {
            objective = new Objective(Integer.parseInt(recurrence), ObjectiveSign.valueOf(sign));
            if (currentHabitEntity == null) {
                addHabit(new HabitEntity(habitName, description, objective), context);
            }else {
                currentHabitEntity.setName(habitName);
                currentHabitEntity.setDescription(description);
                currentHabitEntity.setObjective(objective);
                editHabit(currentHabitEntity, context);
            }
        } catch (Exception e) {
            Toast.makeText(context, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addHabit(HabitEntity newhabit, Context context) {
        try {
            habitRepository.insertHabit(newhabit);
            Toast.makeText(context, "Habit added successfully!", Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(context, "Failed to add habit: Conflict detected!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void editHabit(HabitEntity newhabit, Context context) {
        try {
            habitRepository.updateHabit(newhabit);
            Toast.makeText(context, "Habit edited successfully!", Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(context, "Failed to add habit: Conflict detected!", Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(context, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String getTitle() {
        if (currentHabitEntity == null) {
            return "Creation of a new habit";
        }
        return "edit a habit";
    }

    public String getNameEditText() {
        if (currentHabitEntity == null) {
            return "";
        }
        return currentHabitEntity.getName();
    }

    public String getObjectiveNumber() {
        if (currentHabitEntity == null) {
            return "";
        }
        return String.valueOf(currentHabitEntity.getObjective().getRecurrence());
    }

    public int getSpinnerSelection() {
        if (currentHabitEntity == null) {
            return 0;
        }
        return currentHabitEntity.getObjective().getSign().ordinal();
    }

    public String getDescription() {
        if (currentHabitEntity == null) {
            return "";
        }
        return currentHabitEntity.getDescription();
    }
}