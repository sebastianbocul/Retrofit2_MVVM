package com.sebix.retrofit_mvvm.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sebix.retrofit_mvvm.models.Recipe;

import java.util.List;

public class RecipeRepository {
    private static RecipeRepository instance;
    private MutableLiveData<List<Recipe>> mRecipes;

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public RecipeRepository(MutableLiveData<List<Recipe>> mRecipes) {
        this.mRecipes = mRecipes;
    }

    public RecipeRepository() {
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipes;
    }
}
