package com.example.and_assignment.views.challenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_assignment.R;
import com.example.and_assignment.adapters.ChallengeAdapter;
import com.example.and_assignment.models.Day;
import com.example.and_assignment.viewmodels.ChallengeViewModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChallengePageFragment extends Fragment implements ChallengeAdapter.OnListItemClickListener  {
    private View view;
    private RecyclerView recyclerView;
    private ChallengeViewModel challengeViewModel;

    private Query query;
    private FirebaseRecyclerOptions<Day> options;
    private String[] dayLetters;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_challanges_page, container, false);
        challengeViewModel = new ViewModelProvider(this).get(ChallengeViewModel.class);
        loadValues();
        prepareRecyclerView();
        return view;
    }



    public ChallengePageFragment() {
    }

    private void prepareRecyclerView()
    {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ChallengeAdapter adapter = new ChallengeAdapter(options, this);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void loadValues()
    {
        dayLetters =new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
         query = FirebaseDatabase.getInstance("https://and-assignment-default-rtdb.firebaseio.com/")
                .getReference()
                .child("days")
                .limitToLast(50);

        options = new FirebaseRecyclerOptions.Builder<Day>()
                        .setQuery(query, Day.class)
                        .build();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
          String name = "day" + dayLetters[clickedItemIndex];
          challengeViewModel.loadDay(name);
        Navigation.findNavController(view).navigate(R.id.action_challengePageFragment_to_dayFragment2);
    }
}
