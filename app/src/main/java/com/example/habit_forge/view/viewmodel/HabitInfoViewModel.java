package com.example.habit_forge.view.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.habit_forge.data.repository.HabitRepository;
import com.example.habit_forge.data.repository.LogsRepository;
import com.example.habit_forge.model.HabitEntity;
import com.example.habit_forge.model.HabitInfo;
import com.example.habit_forge.model.LogEntity;
import com.example.habit_forge.model.Objective;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabitInfoViewModel extends AndroidViewModel {
    private final HabitRepository habitRepository;
    private final LogsRepository logsRepository;

    public HabitInfoViewModel(@NonNull Application application) {
        super(application);
        habitRepository = new HabitRepository(application);
        logsRepository = new LogsRepository(application);
    }


    public LiveData<List<HabitInfo>> getListHabitInfo() {
        return Transformations.map(habitRepository.getLiveHabitEntityList(), habitEntities -> {
            List<HabitInfo> habitInfoList = new ArrayList<>();
            if (habitEntities != null) {
                for (HabitEntity habitEntity : habitEntities) {
                    habitInfoList.add(new HabitInfo(
                            habitEntity.getName(),
                            getDays(habitEntity.getId(), habitEntity.getObjective())
                    ));
                }
            }
            return habitInfoList;
        });
    }

    public int getDays(int id, Objective objective) {
        List<LogEntity> logEntities = logsRepository.getLogsByHabitId(id);
        LocalDateTime iterationDay = habitRepository.getHabitEntityInfo(id).getCreationDate().toLocalDate().atStartOfDay();
        int i = 0;
        int daysStreak = 0;
        while (iterationDay.isBefore(LocalDateTime.now().toLocalDate().atStartOfDay())) {
            int currentDayQuantity = 0;
            while (true) {
                if (i == logEntities.size()) {
                    break;
                }
                if (logEntities.get(i).getDateTime().isAfter(iterationDay.plusDays(1))) {
                    break;
                }
                currentDayQuantity += logEntities.get(i).getQuantity();
                i++;
            }
            switch (objective.getSign()) {
                case EQUAL:
                    if (currentDayQuantity == objective.getRecurrence()) {
                        daysStreak++;
                    } else {
                        daysStreak = 0;
                    }
                    break;
                case LESS:
                    if (currentDayQuantity < objective.getRecurrence()) {
                        daysStreak++;
                    } else {
                        daysStreak = 0;
                    }
                    break;
                case MORE:
                    if (currentDayQuantity > objective.getRecurrence()) {
                        daysStreak++;
                    } else {
                        daysStreak = 0;
                    }
                    break;
            }
            iterationDay = iterationDay.plusDays(1);
        }
        return daysStreak;
    }
}
