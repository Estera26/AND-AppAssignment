package com.example.and_assignment.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.and_assignment.data.day.DayLiveData;
import com.example.and_assignment.repositories.DayRepository;

public class DayViewModel extends AndroidViewModel {
    private DayRepository dayRepository;

    public DayViewModel(@NonNull Application application) {
        super(application);
        dayRepository= DayRepository.getInstance();
    }

    public DayLiveData getDay()
    {
        return dayRepository.getDay();
    }

}
