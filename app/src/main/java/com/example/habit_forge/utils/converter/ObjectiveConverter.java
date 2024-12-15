package com.example.habit_forge.utils.converter;

import androidx.room.TypeConverter;

import com.example.habit_forge.model.Objective;
import com.google.gson.Gson;

public class ObjectiveConverter {
    @TypeConverter
    public static String fromObjective(Objective objective) {
        return new Gson().toJson(objective);
    }

    @TypeConverter
    public static Objective toObjective(String objectiveString) {
        return new Gson().fromJson(objectiveString, Objective.class);
    }
}
