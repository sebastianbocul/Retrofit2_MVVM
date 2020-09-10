package com.sebix.retrofit_mvvm.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.requests.RecipeApiClient;

import java.util.List;

public class RecipeRepository {
    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository(); }
        return instance;
    }

    public RecipeRepository(MutableLiveData<List<Recipe>> mRecipes) {
        mRecipeApiClient = RecipeApiClient.getInstance();
    }

    public RecipeRepository() {
        mRecipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeApiClient.getRecipes();
    }
}
