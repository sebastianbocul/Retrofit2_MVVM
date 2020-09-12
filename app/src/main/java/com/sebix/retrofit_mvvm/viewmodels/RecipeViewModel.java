package com.sebix.retrofit_mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.repositories.RecipeRepository;

public class RecipeViewModel extends ViewModel {
    private RecipeRepository mRecipeRepository;
    private String mRecipeId;

    public RecipeViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }
    public LiveData<Recipe> getRecipe(){
        return mRecipeRepository.getRecipe();
    }
    public void searchRecipeById(String recipeId){
        mRecipeRepository.searchRecipeById(recipeId);
        mRecipeId=recipeId;
    }

    public String getmRecipeId() {
        return mRecipeId;
    }
}
