package com.example.and_assignment.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_assignment.models.Day;
import com.example.and_assignment.models.DaysList;
import com.example.and_assignment.repositories.DayRepository;
import com.example.and_assignment.repositories.UserRepository;

import java.util.List;

public class ChallengeViewModel extends AndroidViewModel {
    private DayRepository dayRepository;
    private UserRepository userRepository;

    public ChallengeViewModel(Application app) {
        super(app);
        dayRepository = DayRepository.getInstance();
        userRepository = UserRepository.getInstance(app);
    }

    public void loadDay(String name) {
        dayRepository.loadDay(name);
    }

}
