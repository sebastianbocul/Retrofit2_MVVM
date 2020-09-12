package com.sebix.retrofit_mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.repositories.RecipeRepository;

public class RecipeViewModel extends ViewModel {
    private RecipeRepository mRecipeRepository;
    private String mRecipeId;
    private boolean mDidRetrieveRecipe;


    public RecipeViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
        mDidRetrieveRecipe=false;
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
    public MutableLiveData<Boolean> isRecipeRequestTimedout() {
        return mRecipeRepository.isRecipeRequestTimedout();
    }
    public boolean isDidRetrieveRecipe() {
        return mDidRetrieveRecipe;
    }

    public void setDidRetrieveRecipe(boolean mDidRetrieveRecipe) {
        this.mDidRetrieveRecipe = mDidRetrieveRecipe;
    }

}
