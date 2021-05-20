package com.example.and_assignment.views.mainapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.and_assignment.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainPageFragment extends Fragment {

    private View view;

    private Button buttonStart;
    private Button buttonShopping;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_page, container, false);
        prepareUI();
        prepareOnClickEvents();
        return view;
    }

    private void prepareUI() {
        buttonStart = view.findViewById(R.id.button_startChallenge);
        buttonShopping = view.findViewById(R.id.button_shopping);

    }

    private void prepareOnClickEvents(){
        buttonStart.setOnClickListener(v -> { Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_challengePageFragment); });
        buttonShopping.setOnClickListener(v-> Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_shoppingListFragment));
    }
}
