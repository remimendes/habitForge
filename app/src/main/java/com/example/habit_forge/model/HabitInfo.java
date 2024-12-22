package com.example.habit_forge.model;

public class HabitInfo {
    private final String name;
    private final int days;

    public HabitInfo(String name, int days) {
        this.name = name;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }
}