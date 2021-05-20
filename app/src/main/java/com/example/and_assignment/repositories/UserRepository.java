package com.example.and_assignment.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.and_assignment.data.user.UserLiveData;
import com.example.and_assignment.data.user.UserStatusLiveData;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {
    private final UserLiveData currentUser;
    private final Application app;
    private static UserRepository instance;
    private DatabaseReference myRef;

    private UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
    }

    public static synchronized UserRepository getInstance(Application app) {
        if(instance == null)
            instance = new UserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }

    public void createUser(String uid, boolean isOwner){
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        myRef.setValue(isOwner);
    }

    public UserStatusLiveData getStatus(String uid){
        myRef = FirebaseDatabase.getInstance().getReference("users");
        return new UserStatusLiveData(myRef, uid);
    }
}