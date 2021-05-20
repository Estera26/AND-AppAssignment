package com.example.and_assignment.repositories;

import com.example.and_assignment.data.day.DayLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class DayRepository {
    private static DayRepository instance;
    private DatabaseReference myRef;
     private DayLiveData day;


    public static synchronized DayRepository getInstance() {
        if (instance == null)
            instance = new DayRepository();
        return instance;
    }

    public DayRepository() {}


    public void  loadDay(String name) {
        myRef = FirebaseDatabase.getInstance().getReference().child("days").child(name);
        day = new DayLiveData(myRef);
    }

    public DayLiveData getDay()
    {
        return day;
    }



}

