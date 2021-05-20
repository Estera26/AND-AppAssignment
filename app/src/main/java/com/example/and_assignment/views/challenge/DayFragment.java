package com.example.and_assignment.views.challenge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.and_assignment.R;
import com.example.and_assignment.viewmodels.DayViewModel;


public class DayFragment  extends Fragment
{
    private DayViewModel dayViewModel;


    public DayFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        dayViewModel = new ViewModelProvider(this).get(DayViewModel.class);
        ImageView imageholder = view.findViewById(R.id.imageholder);
        TextView descriptionholder = view.findViewById(R.id.descriptionholder);
        Button buttonGo = view.findViewById(R.id.btnGo);
        TextView description2 = view.findViewById(R.id.description2holder);




        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_dayFragment2_to_videoFragment);
            }
        });


        dayViewModel.getDay().observe(getViewLifecycleOwner(), day -> {
            descriptionholder.setText(day.getDescription());
            description2.setText(day.getDescription2());
            Glide.with(getContext()).load(day.getPicture()).into(imageholder);
        });
        return view;
    }
}

