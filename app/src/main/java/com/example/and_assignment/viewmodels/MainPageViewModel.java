package com.example.and_assignment.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_assignment.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class MainPageViewModel extends AndroidViewModel {
    private final UserRepository userRepository;


    public MainPageViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);

    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
