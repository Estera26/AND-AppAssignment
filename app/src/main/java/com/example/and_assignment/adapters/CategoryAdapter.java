package com.example.and_assignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_assignment.R;
import com.example.and_assignment.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private List<Category> categoryList;
    private HandleCategoryClick clickListener;

    public CategoryAdapter(Context context, HandleCategoryClick clickListener) {
        this.context = context;
        this.clickListener=clickListener;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_design_shopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(this.categoryList.get(position).categoryName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(categoryList.get(position));
            }
        });

        holder.editCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(categoryList.get(position));
            }
        });

        holder.removeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(categoryList.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        if (categoryList == null || categoryList.size() == 0)
            return 0;
        else
            return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView removeCategory;
        ImageView editCategory;

        public ViewHolder(@NonNull View view) {
            super(view);
            categoryName = view.findViewById(R.id.name_field);
            removeCategory = view.findViewById(R.id.remove_field);
            editCategory = view.findViewById(R.id.edit_field);
        }
    }


    public interface HandleCategoryClick {
        void itemClick(Category category);
        void removeItem(Category category);
        void editItem(Category category);
    }
}
