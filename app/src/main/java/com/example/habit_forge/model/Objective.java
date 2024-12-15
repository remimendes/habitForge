package com.example.habit_forge.model;

import com.example.habit_forge.utils.enumeration.ObjectiveSign;

public class Objective {
    private int recurrence;
    private ObjectiveSign sign;

    public Objective(int recurrence, ObjectiveSign sign) {
        this.recurrence = recurrence;
        this.sign = sign;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public ObjectiveSign getSign() {
        return sign;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }

    public void setSign(ObjectiveSign sign) {
        this.sign = sign;
    }
}
