package com.example.habit_forge.model;

public class IdQuantity {
    int habitId, totalQuantity;

    public IdQuantity(int habitId, int totalQuantity) {
        this.habitId = habitId;
        this.totalQuantity = totalQuantity;
    }

    public int getHabitId() {
        return habitId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
