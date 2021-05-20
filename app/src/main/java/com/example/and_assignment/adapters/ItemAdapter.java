package com.example.and_assignment.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_assignment.R;
import com.example.and_assignment.models.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private Context context;
    private List<Item> itemsList;
    private HandleItemClick clickListener;

    public ItemAdapter(Context context, HandleItemClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setCategoryList(List<Item> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_design_shopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(this.itemsList.get(position).itemName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(itemsList.get(position));
            }
        });

        holder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(itemsList.get(position));
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(itemsList.get(position));
            }
        });

        if(this.itemsList.get(position).finished) {
            holder.itemName.setPaintFlags(holder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.itemName.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount() {
        if(itemsList == null || itemsList.size() == 0)
            return 0;
        else
            return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageView removeItem;
        ImageView editItem;

        public ViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.name_field);
            removeItem = view.findViewById(R.id.remove_field);
            editItem = view.findViewById(R.id.edit_field);

        }
    }

    public interface  HandleItemClick {
        void itemClick(Item item);
        void removeItem(Item item);
        void editItem(Item item);
    }
}
