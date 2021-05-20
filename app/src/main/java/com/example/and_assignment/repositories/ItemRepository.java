package com.example.and_assignment.repositories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_assignment.data.shopping.ShoppingDatabase;
import com.example.and_assignment.models.Item;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemRepository {
    private static ItemRepository instance;
    private ShoppingDatabase shoppingDatabase;
    private ExecutorService executorService;


    public static synchronized ItemRepository getInstance(Application application)
    {
        if (instance == null)
            instance = new ItemRepository(application);
        return instance;
    }


    public ItemRepository(Application application) {
        shoppingDatabase = ShoppingDatabase.getInstance(application.getApplicationContext());
        executorService= Executors.newFixedThreadPool(2);
    }

    public LiveData<List<Item>> getAllItemsList(int categoryID) {

        return shoppingDatabase.shoppingListDAO().getAllItemList(categoryID);
    }

    public void insertItems(Item item) {
        executorService.execute(()->shoppingDatabase.shoppingListDAO().insertItems(item));
    }

    public void updateItems(Item item) {
        executorService.execute(()->shoppingDatabase.shoppingListDAO().updateItems(item));
    }

    public void deleteItems(Item item) {
        executorService.execute(()->shoppingDatabase.shoppingListDAO().deleteItems(item));
    }



}
