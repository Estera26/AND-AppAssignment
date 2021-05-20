package com.example.and_assignment.views.challenge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.and_assignment.R;
import com.example.and_assignment.viewmodels.DayViewModel;


public class VideoFragment extends Fragment {

    private DayViewModel dayViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        dayViewModel = new ViewModelProvider(this).get(DayViewModel.class);
        WebView videoholder = view.findViewById(R.id.videoholder);
        dayViewModel.getDay().observe(getViewLifecycleOwner(), day -> {
            videoholder.loadUrl(day.getVideo());
            });
        return view;

    }


}