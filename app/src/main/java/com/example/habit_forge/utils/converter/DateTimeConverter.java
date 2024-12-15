package com.example.habit_forge.utils.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.time.LocalDateTime;

public class DateTimeConverter {
    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime dateTime) {
        return new Gson().toJson(dateTime);
    }

    @TypeConverter
    public static LocalDateTime toLocalDateTime(String dateTimeString) {
        return new Gson().fromJson(dateTimeString, LocalDateTime.class);
    }
}
