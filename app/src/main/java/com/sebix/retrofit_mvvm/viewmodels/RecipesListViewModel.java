package com.sebix.retrofit_mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.repositories.RecipeRepository;
import com.sebix.retrofit_mvvm.requests.RecipeApiClient;

import java.util.List;

public class RecipesListViewModel extends ViewModel {
    private RecipeRepository mRecipeRepository;

    public RecipesListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getmRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber){
        mRecipeRepository.searchRecipesApi(query, pageNumber);
    }
}
