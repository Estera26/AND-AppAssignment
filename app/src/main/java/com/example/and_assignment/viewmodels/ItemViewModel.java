package com.example.and_assignment.viewmodels;

import android.app.Application;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_assignment.data.shopping.ShoppingDatabase;
import com.example.and_assignment.models.Item;
import com.example.and_assignment.repositories.ItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> listOfItems;
    private ShoppingDatabase shoppingDatabase;
    private ItemRepository itemRepository;


    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = ItemRepository.getInstance(application);
    }

    public MutableLiveData<List<Item>> getItemsListObserver() {
        return listOfItems;
    }

    public LiveData<List<Item>> getAllItemsList(int categoryID) {
       return itemRepository.getAllItemsList(categoryID);
    }

    public void insertItems(Item item) {
        itemRepository.insertItems(item);
    }

    public void updateItems(Item item) {
        itemRepository.updateItems(item);
    }

    public void deleteItems(Item item) {
        itemRepository.deleteItems(item);
    }
}
