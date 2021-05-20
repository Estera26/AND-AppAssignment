package com.example.and_assignment.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_assignment.data.shopping.ShoppingDatabase;
import com.example.and_assignment.models.Category;
import com.example.and_assignment.repositories.CategoryRepository;
import com.google.android.exoplayer2.C;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {


    private ShoppingDatabase shoppingDatabase;
    private CategoryRepository categoryRepository;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = CategoryRepository.getInstance(application);
    }


    public LiveData<List<Category>> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    public void insertCategory(String cname) {
        categoryRepository.insertCategory(cname);
    }

    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteCategory(category);

    }
}
