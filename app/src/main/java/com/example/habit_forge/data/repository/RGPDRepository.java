package com.example.habit_forge.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class RGPDRepository {
    private SharedPreferences sharedPreferences;

    public RGPDRepository(Context context) {
        sharedPreferences = context.getSharedPreferences("UserRGPDPreferences", Context.MODE_PRIVATE);
    }

    public int getDaysBeforeDeletion() {
        return sharedPreferences.getInt("daysBeforeDeletion", -1);
    }

    public void saveDaysBeforeDeletion(int days) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("daysBeforeDeletion", days);
        editor.apply();
    }
}
