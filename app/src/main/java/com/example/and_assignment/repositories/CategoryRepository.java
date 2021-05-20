package com.example.and_assignment.repositories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_assignment.data.shopping.ShoppingDatabase;
import com.example.and_assignment.models.Category;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryRepository {
    private static CategoryRepository instance;
    private MutableLiveData<List<Category>> categoryList;
    private ShoppingDatabase shoppingDatabase;
    private ExecutorService executorService;

    public static synchronized CategoryRepository getInstance(Application application)
    {
        if (instance == null)
            instance = new CategoryRepository(application);
        return instance;
    }

    public CategoryRepository (Application application)
    {
        shoppingDatabase = ShoppingDatabase.getInstance(application.getApplicationContext());
        executorService= Executors.newFixedThreadPool(2);
    }



    public LiveData<List<Category>> getCategoryList()
    {
        return shoppingDatabase.shoppingListDAO().getAllCategoriesList();

    }

    public void insertCategory(String cname)
    {
        Category category = new Category();
        category.categoryName = cname;
        executorService.execute(()-> shoppingDatabase.shoppingListDAO().insertCategory(category));

    }

    public void updateCategory(Category category)
    {
        executorService.execute(()->shoppingDatabase.shoppingListDAO().updateCategory(category));
    }

    public void deleteCategory(Category category)
    {
        executorService.execute(()->shoppingDatabase.shoppingListDAO().deleteCategory(category));
    }
}
