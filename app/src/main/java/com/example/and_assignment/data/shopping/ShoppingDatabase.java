package com.example.and_assignment.data.shopping;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.and_assignment.models.Category;
import com.example.and_assignment.models.Item;


@Database(entities = {Category.class, Item.class}, version = 1)
public abstract class ShoppingDatabase extends RoomDatabase {
    public static ShoppingDatabase INSTANCE;
    public abstract  ShoppingListDAO shoppingListDAO();

    public static ShoppingDatabase getInstance(Context context)
    {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ShoppingDatabase.class,"ShoppingDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }


}
