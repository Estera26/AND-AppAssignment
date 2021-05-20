package com.example.and_assignment.data.shopping;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.and_assignment.models.Category;
import com.example.and_assignment.models.Item;

import java.util.List;

@Dao
public interface ShoppingListDAO {

    @Query("Select * from Category")
    LiveData<List<Category>> getAllCategoriesList();

    @Insert
    void insertCategory(Category categories);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Query("Select * from Item where categoryId= :categoryId ")
    LiveData<List<Item>> getAllItemList (int categoryId);

    @Insert
    void insertItems(Item item);

    @Update
    void updateItems(Item item);

    @Delete
    void deleteItems (Item item);
}
