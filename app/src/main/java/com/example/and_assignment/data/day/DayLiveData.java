package com.example.and_assignment.data.day;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.and_assignment.models.Day;
import com.example.and_assignment.models.DaysList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class DayLiveData extends LiveData<Day> {


    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Day day = snapshot.getValue(Day.class);
            setValue(day);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {}

    };

    DatabaseReference ref ;
    public DayLiveData(DatabaseReference ref)
    {
        this.ref=ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        ref.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        ref.removeEventListener(listener);
    }
}
