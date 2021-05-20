package com.example.and_assignment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.and_assignment.R;
import com.example.and_assignment.models.Day;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ChallengeAdapter extends FirebaseRecyclerAdapter<Day, ChallengeAdapter.MyViewHolder> {

    final private OnListItemClickListener listener;
    public ChallengeAdapter(FirebaseRecyclerOptions<Day> options, OnListItemClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.recycler_design_challenge, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Day model) {
        holder.dayName.setText(model.getName());
        Glide.with(holder.imageView.getContext()).load(model.getPicture()).into(holder.imageView);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView dayName;
        public ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.nametext);
            imageView = itemView.findViewById(R.id.img1);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            listener.onListItemClick(getAbsoluteAdapterPosition());
        }
    }
    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}


