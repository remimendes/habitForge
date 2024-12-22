package com.example.habit_forge.data.db;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import androidx.room.Database;
import androidx.room.Room;

import com.example.habit_forge.data.dao.HabitDAO;
import com.example.habit_forge.data.dao.LogDAO;
import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.model.LogEntity;
import com.example.habit_forge.utils.converter.DateTimeConverter;
import com.example.habit_forge.utils.converter.ObjectiveConverter;

@Database(entities = {HabitEntity.class, LogEntity.class}, version = 1)
@TypeConverters({ObjectiveConverter.class, DateTimeConverter.class})
public abstract class HabitForgeRoomDatabase extends androidx.room.RoomDatabase {
    public abstract HabitDAO habitDAO();
    public abstract LogDAO logDAO();

    private static volatile HabitForgeRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static HabitForgeRoomDatabase getDatabaseFromJava(final Context context) {
        if (INSTANCE == null) {
            synchronized (HabitForgeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    HabitForgeRoomDatabase.class,
                                    "habitForge_room.db"
                            )
                            .addCallback(roomDatabasePopulateJava)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback roomDatabasePopulateJava = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
