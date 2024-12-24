package com.example.habit_forge.view.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.habit_forge.data.repository.HabitRepository;
import com.example.habit_forge.data.repository.LogsRepository;
import com.example.habit_forge.model.LogEntity;

import java.time.LocalDateTime;

public class HabitInteractionViewModel extends AndroidViewModel {
    private final LogsRepository logsRepository;
    private final HabitRepository habitRepository;
    private final int habitId;

    public HabitInteractionViewModel(@NonNull Application application, int habitId) {
        super(application);
        logsRepository = new LogsRepository(application);
        this.habitRepository = new HabitRepository(application);
        this.habitId = habitId;
    }

    public void addLog(int quantity) {
        try {
            logsRepository.insertLog(new LogEntity(habitId, LocalDateTime.now(), quantity));
        } catch (Exception e) {
            // Message utilisateur plus clair
            Toast.makeText(getApplication(), "An error occurred while adding a log.", Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Journalisation pour le développement
        }
    }

    public String getTitle() {
        return habitRepository.getHabitName(habitId);
    }

    public void minusButtonPressed(String quantity) {
        try {
            int quantityInt = validateAndParseQuantity(quantity);
            addLog(-quantityInt); // On passe ici la valeur négative
        } catch (NumberFormatException e) {
            Toast.makeText(getApplication(), "Invalid input: Please enter a valid number.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Journalisation
        }
    }

    public void plusButtonPressed(String quantity) {
        try {
            int quantityInt = validateAndParseQuantity(quantity);
            addLog(quantityInt); // On ajoute directement la quantité
        } catch (NumberFormatException e) {
            Toast.makeText(getApplication(), "Invalid input: Please enter a valid number.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Journalisation
        }
    }

    public LiveData<Integer> getQuantity() {
        return logsRepository.getQuantityLiveDataByStartDate(habitId, String.valueOf(LocalDateTime.now().toLocalDate().atStartOfDay()));
    }

    // Méthode pour valider et convertir la quantité
    private int validateAndParseQuantity(String quantity) throws NumberFormatException {
        if (quantity == null || quantity.trim().isEmpty()) {
            throw new NumberFormatException("Quantity is empty or null.");
        }
        return Integer.parseInt(quantity.trim()); // Conversion sécurisée
    }
}