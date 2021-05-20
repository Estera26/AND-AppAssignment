package com.example.and_assignment.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "itemName")
    public String itemName;

    @ColumnInfo(name = "categoryId")
    public int categoryId;

    @ColumnInfo(name = "finished")
    public boolean finished;
}
