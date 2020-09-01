package com.sebix.retrofit_mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.repositories.RecipeRepository;

import java.util.List;

public class RecipesListViewModel extends ViewModel {
    private RecipeRepository mRecipeRepository;

    public RecipesListViewModel() {
        mRecipeRepository= RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getmRecipes() {
        return mRecipeRepository.getRecipes();
    }
}
