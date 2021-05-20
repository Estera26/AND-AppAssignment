package com.example.and_assignment.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_assignment.data.user.UserStatusLiveData;
import com.example.and_assignment.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public LoginViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();

    }
    public void createUser(boolean isOwner){
        String userId = userRepository.getCurrentUser().getValue().getUid();
        userRepository.createUser(userId,isOwner);
    }
    public UserStatusLiveData getStatus(){
        String userId = userRepository.getCurrentUser().getValue().getUid();
        return userRepository.getStatus(userId);
    }
}
