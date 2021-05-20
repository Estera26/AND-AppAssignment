package com.example.and_assignment.views.shopping;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and_assignment.R;
import com.example.and_assignment.adapters.CategoryAdapter;
import com.example.and_assignment.models.Category;
import com.example.and_assignment.viewmodels.CategoryViewModel;

import java.util.List;


public class ShoppingListFragment extends Fragment implements CategoryAdapter.HandleCategoryClick {

    private View view;
    private CategoryViewModel shoppingViewModel;
    private TextView noResult;
    private RecyclerView recyclerViewShopping;
    private CategoryAdapter categoryAdapter;
    private Category editCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        noResult = view.findViewById(R.id.noResult);
        recyclerViewShopping = view.findViewById(R.id.shoppingRecyclerView);
        ImageView addCategory = view.findViewById(R.id.addCategory);
        addCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showAddCategoryDialog(false);
            }
        });

        initViewModel();
        initRecyclerview();
        shoppingViewModel.getCategoryList();
        return view;
    }

    private void showAddCategoryDialog(boolean canEdit) {
        AlertDialog dialog = new AlertDialog.Builder(this.getContext()).create();
        View dialogView = getLayoutInflater().inflate(R.layout.add_category_layout, null);
        EditText insertCategory = dialogView.findViewById(R.id.insertCategory);
        TextView createAction = dialogView.findViewById(R.id.create_action);
        TextView cancelAction = dialogView.findViewById(R.id.cancel_action);


        if(canEdit)
        {
            createAction.setText("Update");
            insertCategory.setText(editCategory.categoryName);
        }


        createAction.setOnClickListener(v -> {
            String name = insertCategory.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(ShoppingListFragment.this.getContext(), "Enter category name", Toast.LENGTH_SHORT).show();
                return;
            }
            if(canEdit == true)
            {
                editCategory.categoryName = name;
                shoppingViewModel.updateCategory(editCategory);
            }else{
                shoppingViewModel.insertCategory(name);
            }
            dialog.dismiss();
        });

        cancelAction.setOnClickListener(v -> dialog.dismiss());
        dialog.setView(dialogView);
        dialog.show();

    }

    private void initViewModel() {
        shoppingViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        shoppingViewModel.getCategoryList().observe(getViewLifecycleOwner(), categories -> {
            if (categories == null) {
                noResult.setVisibility(view.VISIBLE);
                recyclerViewShopping.setVisibility(View.GONE);
            } else {
                categoryAdapter.setCategoryList(categories);
                noResult.setVisibility(view.GONE);
                recyclerViewShopping.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initRecyclerview() {
        recyclerViewShopping.setLayoutManager(new LinearLayoutManager(this.getContext()));
        categoryAdapter = new CategoryAdapter(this.getContext(), this);
        recyclerViewShopping.setAdapter(categoryAdapter);
    }

    @Override
    public void itemClick(Category category) {
        ShoppingListFragmentDirections.ActionShoppingListFragmentToItemListFragment action = ShoppingListFragmentDirections.actionShoppingListFragmentToItemListFragment(category.id);
        Navigation.findNavController(view).navigate(action);

    }

    @Override
    public void removeItem(Category category) {
        shoppingViewModel.deleteCategory(category);
    }

    @Override
    public void editItem(Category category) {
        this.editCategory = category;
      showAddCategoryDialog(true);
    }
}