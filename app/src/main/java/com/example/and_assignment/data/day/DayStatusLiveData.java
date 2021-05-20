package com.example.and_assignment.data.day;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DayStatusLiveData extends LiveData<Boolean> {
    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            setValue(snapshot.hasChild(day));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    DatabaseReference databaseReference;
    String day;

    public DayStatusLiveData(DatabaseReference databaseReference, String day) {
        this.databaseReference = databaseReference;
        this.day = day;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
