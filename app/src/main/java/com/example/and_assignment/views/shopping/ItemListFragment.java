package com.example.and_assignment.views.shopping;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and_assignment.R;
import com.example.and_assignment.adapters.ItemAdapter;
import com.example.and_assignment.models.Item;
import com.example.and_assignment.viewmodels.ItemViewModel;

import java.util.List;


public class ItemListFragment extends Fragment implements ItemAdapter.HandleItemClick {

    private View view;
    private ItemAdapter itemAdapter;
    private int category_id;
    private ItemViewModel itemViewModel;
    private RecyclerView itemRecyclerview;
    private Item itemToUpdate = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_list, container, false);
        category_id = ItemListFragmentArgs.fromBundle(getArguments()).getCategoryId();
        final EditText addItem = view.findViewById(R.id.add_item);
        ImageView addButton = view.findViewById(R.id.add_to_cart);

        addButton.setOnClickListener(v -> {
            String itemName = addItem.getText().toString();
            if (TextUtils.isEmpty(itemName)) {
                Toast.makeText(ItemListFragment.this.getContext(), "Enter Item", Toast.LENGTH_SHORT).show();
                return;
            }
            if (itemToUpdate == null)
                saveNewItem(itemName);
            else updateItem(itemName);
        });

        initItemViewModel();
        initItemRecyclerview();
        return view;
    }


    private void initItemViewModel() {
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getAllItemsList(category_id).observe(getViewLifecycleOwner(), items -> {

            if (items == null) {
                itemRecyclerview.setVisibility(View.GONE);
                view.findViewById(R.id.no_result).setVisibility(View.VISIBLE);
            } else {
                itemAdapter.setCategoryList(items);
                view.findViewById(R.id.no_result).setVisibility(View.GONE);
                itemRecyclerview.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initItemRecyclerview() {
        itemRecyclerview = view.findViewById(R.id.recyclerView_item);
        itemRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
        itemAdapter = new ItemAdapter(this.getContext(), this);
        itemRecyclerview.setAdapter(itemAdapter);
    }

    private void saveNewItem(String itemName) {
        Item item = new Item();
        item.itemName = itemName;
        item.categoryId = category_id;
        itemViewModel.insertItems(item);
        ((EditText) view.findViewById(R.id.add_item)).setText("");
    }


    @Override
    public void itemClick(Item item) {
        if (item.finished) {
            item.finished = false;
        } else {
            item.finished = true;
        }
        itemViewModel.updateItems(item);
    }

    @Override
    public void removeItem(Item item) {
        itemViewModel.deleteItems(item);
    }

    @Override
    public void editItem(Item item) {
        this.itemToUpdate = item;
        ((EditText) view.findViewById(R.id.add_item)).setText(item.itemName);
    }

    private void updateItem(String newName) {
        itemToUpdate.itemName = newName;
        itemViewModel.updateItems(itemToUpdate);
        ((EditText) view.findViewById(R.id.add_item)).setText("");
        itemToUpdate = null;
    }


}

